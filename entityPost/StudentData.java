package entityPost;

public class StudentData {
    private StudentAttributes attributes;
    private String type;

    public StudentData() {
    }

    public StudentData(StudentAttributes attributes, String type) {
        this.attributes = attributes;
        this.type = type;
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
}
