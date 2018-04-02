package validate;

import javafx.scene.control.Label;

public class Validate {

    public boolean validateRollNumber(String txt, Label errRollNumber){
        boolean valid = true;
        String regex = "[A-Za-z0-9]+";

        if(txt == null || txt.isEmpty()){
            errRollNumber.setText(ErrMap.errMap.get("rollNumber").getErrEmpty());
            valid = false;
        }else if(txt.matches(regex) == false){
            errRollNumber.setText(ErrMap.errMap.get("rollNumber").getErrCharacter());
            valid = false;
        }else if(txt.matches(regex) == true){

            errRollNumber.setText("");
            valid = true;
        }
        return valid;
    }

    public boolean validateName(String txt,  Label errName){
        boolean valid = true;
        String regex = "[\\p{L}\\s]+";
        String regex2 = "[\\s]+";

        if(txt == null || txt.isEmpty() || txt.matches(regex2) == true){
            errName.setText(ErrMap.errMap.get("name").getErrEmpty());
            valid = false;
        }else if(txt.matches(regex) == false){
            errName.setText(ErrMap.errMap.get("name").getErrCharacter());
            valid = false;
        }else if(txt.matches(regex) == true){
            if(txt.length() >= 5) {
                errName.setText("");
                valid = true;
            }else {
                errName.setText(ErrMap.errMap.get("name").getErrLength());
                valid = false;
            }
        }
        return valid;
    }

    public boolean validatePhone(String txt, Label errPhone){
        boolean valid = true;
        String regex = "[0-9\\s]+";
        String regex2 = "[\\s]+";

        if(txt == null || txt.isEmpty() || txt.matches(regex2) == true){
            errPhone.setText(ErrMap.errMap.get("phone").getErrEmpty());
            valid = false;
        }else if(txt.matches(regex) == false){
            errPhone.setText(ErrMap.errMap.get("phone").getErrCharacter());
            valid = false;
        }else if(txt.matches(regex) == true){
            if(txt.length() >= 10) {
                errPhone.setText("");
                valid = true;
            }else {
                errPhone.setText(ErrMap.errMap.get("phone").getErrLength());
                valid = false;
            }
        }
        return valid;
    }

    public boolean validateEmail(String txt, Label errEmail){
        boolean valid = true;
        String regex = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

        if(txt == null || txt.isEmpty()){
            errEmail.setText(ErrMap.errMap.get("email").getErrEmpty());
            valid = false;
        }else if(txt.matches(regex) == false){
            errEmail.setText(ErrMap.errMap.get("email").getErrCharacter());
            valid = false;
        }else if(txt.matches(regex) == true){
            errEmail.setText("");
            valid = true;
        }
        return valid;
    }
}
