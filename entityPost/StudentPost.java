package entityPost;

public class StudentPost {

    private StudentData data;

    public StudentPost() {
    }

    public StudentPost(StudentData data) {
        this.data = data;
    }

    public StudentData getData() {
        return data;
    }

    public void setData(StudentData data) {
        this.data = data;
    }
}
