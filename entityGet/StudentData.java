package entityGet;

public class StudentData {
    private StudentAttributes attributes;
    private String type;
    private String id;

    public StudentData() {
    }

    public StudentData(StudentAttributes attributes, String type, String id) {
        this.attributes = attributes;
        this.type = type;
        this.id = id;
    }

    public StudentAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(StudentAttributes attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
