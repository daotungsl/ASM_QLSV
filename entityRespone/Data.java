package entityRespone;


public class Data {

    private String type;
    private String id;
    private Attributes attributes;

    public Data() {
    }

    public Data(String type, String id, Attributes attributes) {

        this.type = type;
        this.id = id;
        this.attributes = attributes;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
