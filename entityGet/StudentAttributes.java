package entityGet;

public class StudentAttributes {
    private long createdTimeMLS;
    private String phone;
    private String rollNumber;
    private String name;
    private String avatar;
    private long updatedTimeMLS;
    private String email;
    private int status;

    public StudentAttributes() {
    }

    public StudentAttributes(long createdTimeMLS, String phone, String rollNumber, String name, String avatar, long updatedTimeMLS, String email, int status) {
        this.createdTimeMLS = createdTimeMLS;
        this.phone = phone;
        this.rollNumber = rollNumber;
        this.name = name;
        this.avatar = avatar;
        this.updatedTimeMLS = updatedTimeMLS;
        this.email = email;
        this.status = status;
    }

    public long getCreatedTimeMLS() {
        return createdTimeMLS;
    }

    public void setCreatedTimeMLS(long createdTimeMLS) {
        this.createdTimeMLS = createdTimeMLS;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getUpdatedTimeMLS() {
        return updatedTimeMLS;
    }

    public void setUpdatedTimeMLS(long updatedTimeMLS) {
        this.updatedTimeMLS = updatedTimeMLS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
