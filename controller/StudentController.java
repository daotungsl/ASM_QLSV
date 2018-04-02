package controller;


import com.google.gson.Gson;
import entity.Student;
import entityGet.StudentData;
import entityRespone.Respone;
import utility.ConnectionHandle;
import utility.JSONHandle;

import java.util.ArrayList;

public class StudentController {

    public static final String url = "https://too-late-to-apologize.appspot.com/_api/v1/users?page=1&limit=50";
    public static final String url1 = "https://too-late-to-apologize.appspot.com/_api/v1/users";


    public static ArrayList getListStudent(){

        String respone = ConnectionHandle.getContentFromUrl(url);
        ArrayList<StudentData> listStudentData = JSONHandle.parseJson(respone);
        ArrayList<Student> listStudent = new ArrayList<>();

        if(listStudentData != null){
            for (int i = 0; i < listStudentData.size(); i++) {
                Student student = new Student(listStudentData.get(i).getId(),
                        (i+1),
                        listStudentData.get(i).getAttributes().getRollNumber(),
                        listStudentData.get(i).getAttributes().getName(),
                        listStudentData.get(i).getAttributes().getPhone(),
                        listStudentData.get(i).getAttributes().getEmail(),
                        listStudentData.get(i).getAttributes().getAvatar());
                listStudent.add(student);
            }
        }

        return listStudent;
    }

    public static int postStudent(entityPost.StudentPost student){

        Gson gson = new Gson();
        String jsonStudent = gson.toJson(student);

        int respone = ConnectionHandle.postContentToUrl(jsonStudent,url1);

        return respone;
    }

    public static int updateStudent(entityPost.StudentPost student, String id){

        String url2 = "https://too-late-to-apologize.appspot.com/_api/v1/users/" + id;
        Gson gson = new Gson();
        String jsonStudent = gson.toJson(student);

        String repone = ConnectionHandle.putUpdateContenttoUrl(jsonStudent,url2);
        System.out.println(repone);
        Respone data = JSONHandle.parseJsonRepone(repone);
        int status = 0;
        if(data != null){
            status = data.getData().getAttributes().getStatus();
        }

        return status;

    }

    public static int deleteStudent(entityPost.StudentPost student, String id){

        String url2 = "https://too-late-to-apologize.appspot.com/_api/v1/users/" + id;
        Gson gson = new Gson();
        String jsonStudent = gson.toJson(student);

        String repone = ConnectionHandle.deleteContenttoUrl(jsonStudent,url2);
        Respone data = JSONHandle.parseJsonRepone(repone);
        int status = 0;
        if(data != null){
            status = data.getData().getAttributes().getStatus();
        }

        return status;
    }
}
