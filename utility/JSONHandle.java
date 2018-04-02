package utility;

import com.google.gson.Gson;
import entityGet.StudentData;
import entityGet.StudentGet;
import entityRespone.Respone;

import java.util.ArrayList;

public class JSONHandle {
    public static ArrayList parseJson(String repons){

//        ArrayList<StudentTest> listStudent = new ArrayList<>();
//        StudentTest st1 = new StudentTest("1", "A1000", "Hoang Thanh Son", "0965080258", "son@123","google.com");
//        StudentTest st2 = new StudentTest("2", "A1200", "Dao Tung", "0965080212", "son@123","google.com");
//        StudentTest st3 = new StudentTest("3", "A1300", "Cris Hung", "0988123123", "son@123","google.com");
//        StudentTest st4 = new StudentTest("4", "A1400", "Nguyen Thai", "0987651212", "son@123","google.com");
//        StudentTest st5 = new StudentTest("5", "A1500", "Trinh Van Loc", "098913311", "son@123","google.com");
//        StudentTest st6 = new StudentTest("6", "A1600", "Phan van Nam", "098913311", "son@123","google.com");
//        StudentTest st7 = new StudentTest("7", "A1700", "Ha huu tho", "098913311", "son@123","google.com");
//        StudentTest st8 = new StudentTest("8", "A1800", "Nguyen thi Trang", "098913311", "son@123","google.com");
//        StudentTest st9 = new StudentTest("9", "A1900", "Trinh thi huyen", "098913311", "son@123","google.com");
//        StudentTest st10 = new StudentTest("10", "A2000", "Le Van Tho", "098913311", "son@123","google.com");
//        listStudent.add(st1);
//        listStudent.add(st2);
//        listStudent.add(st3);
//        listStudent.add(st4);
//        listStudent.add(st5);
//        listStudent.add(st6);
//        listStudent.add(st7);
//        listStudent.add(st8);
//        listStudent.add(st9);
//        listStudent.add(st10);


        Gson gson = new Gson();
        StudentGet students = gson.fromJson(repons, StudentGet.class);
        ArrayList<StudentData> listStudent = null;
        if(students != null){
            listStudent = students.getData();
        }
        return listStudent;
    }

    public static Respone parseJsonRepone(String repons){
        Gson gson = new Gson();
        Respone data = gson.fromJson(repons, Respone.class);
        return data;
    }
}
