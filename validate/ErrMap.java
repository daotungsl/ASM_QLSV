package validate;

import java.util.HashMap;

public class ErrMap {

    public static HashMap<String,ErrEntity> errMap = new HashMap<>();

    static {
        ErrEntity rollNumber = new ErrEntity("Please enter RollNumber","Only include characters : A-Z, a-z, 0-9.","Please enter at least 6 characters.", "RollNumber existed, Please enter RollNumber other.");
        ErrEntity name = new ErrEntity("Please enter Name","Only include characters : A-Z, a-z, white space character.", "Please enter at least 5 characters.","");
        ErrEntity phone = new ErrEntity("Please enter Phone","Only include characters : 0-9.","Please enter at least 10 digits.", "");
        ErrEntity email = new ErrEntity("Please enter Email","Enter a valid Email as : asd12@gmail.com ...." ,"","");
        ErrEntity avatar = new ErrEntity("Please enter Avatar","" ,"", "");
        errMap.put("rollNumber", rollNumber);
        errMap.put("name", name);
        errMap.put("phone", phone);
        errMap.put("email", email);
        errMap.put("avatar", avatar);
    }

}
