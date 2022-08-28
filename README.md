# SimpleConfig

Super simple config implementation based at FasterXML/jackson:

```java
    @JsonProperty
    private int firstParam = 2048;

    @JsonProperty
    private List<MyObject> myObjects = new ArrayList<>();

    @JsonProperty
    private MyObject2 myObject;

    public LauncherConfig() {
        super(Paths.get(System.getProperty("user.dir") + "/myconfig.json"));
    }

    public LauncherConfig() {
        // Empty builder for Jackson serializer
    }
```