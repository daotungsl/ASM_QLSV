package entityPost;

public class StudentAttributes {
    private int type;
    private String rollNumber;
    private String name;
    private String email;
    private String phone;
    private String avatar;

    public StudentAttributes() {
    }

    public StudentAttributes(int type, String rollNumber, String name, String email, String phone, String avatar) {
        this.type = type;
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
