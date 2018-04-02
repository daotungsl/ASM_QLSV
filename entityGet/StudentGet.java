package entityGet;


import java.util.ArrayList;

public class StudentGet {
    private ArrayList<StudentData> data;
    private StudentMeta meta;

    public StudentGet() {
    }

    public StudentGet(ArrayList<StudentData> data, StudentMeta meta) {
        this.data = data;
        this.meta = meta;
    }

    public ArrayList<StudentData> getData() {
        return data;
    }

    public void setData(ArrayList<StudentData> data) {
        this.data = data;
    }

    public StudentMeta getMeta() {
        return meta;
    }

    public void setMeta(StudentMeta meta) {
        this.meta = meta;
    }
}
