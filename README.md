# SimpleConfig

Super simple config implementation based at FasterXML/jackson:

```java
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.simplecode.config.SimpleConfig;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConfigExample extends SimpleConfig {

    @JsonProperty
    private List<MyObject> exampleProperty = new ArrayList<>();

    @JsonProperty
    private MyObject exampleProperty2;

    public MyConfig() {
        super(Paths.get(System.getProperty("user.dir") + "/myconfig.json"));
    }

    public List<MyObject> getExampleProperty() {
        return exampleProperty;
    }

    public MyObject getExampleProperty2() {
        return exampleProperty2;
    }

    public void setExampleProperty(List<MyObject> exampleProperty) {
        this.exampleProperty = exampleProperty;
    }

    public void setExampleProperty2(MyObject exampleProperty2) {
        this.exampleProperty2 = exampleProperty2;
    }
}
```

```java
public class MyObject {

    private String myString;
    private int myInt;

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }
}
```