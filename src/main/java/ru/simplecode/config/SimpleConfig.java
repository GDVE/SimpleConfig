package ru.simplecode.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import ru.simplecode.util.JsonUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@NoArgsConstructor
public abstract class SimpleConfig {

    @JsonIgnore
    private Path path;

    public SimpleConfig(Path path) {
        this.path = path;
    }

    public void writeToDisk() throws IOException {

        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        JsonUtils.getMapperDefault().writerWithDefaultPrettyPrinter().writeValue(path.toFile(), this);
    }

    public void readFromDisk() throws IOException {

        if (!Files.exists(path)) {
            this.writeToDisk();
        }

        SimpleConfig configObject = JsonUtils.getMapperDefault().readValue(path.toFile(), this.getClass());

        Arrays.stream(configObject.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(JsonProperty.class) != null)
                .forEach(field -> {

                    field.setAccessible(true);

                    try {
                        Field configField = this.getClass().getDeclaredField(field.getName());
                        configField.setAccessible(true);
                        configField.set(this, field.get(configObject));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
