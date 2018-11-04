package middleware;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public boolean isValidDate (String input){
        try {
           dateFormat.parse(input);
            return true;
        }catch (ParseException e){
            return false;
        }

    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    public boolean isValidPhoneNumber(String phone) {
        String regexStr = "^[0-9]{10}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexStr);
        java.util.regex.Matcher m = p.matcher(phone);
        return m.matches();
    }
    public boolean isValidStudentId(String stdId){
        String regexStr = "G(C|T)[0-9]{5}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexStr);
        java.util.regex.Matcher m = p.matcher(stdId);
        return m.matches();
    }
    public boolean isValidName(String name){
        String regexStr= "^[a-zA-Z ]{1,100}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexStr);
        java.util.regex.Matcher m = p.matcher(name);
        return m.matches();
    }
    public boolean isValidLecturerID(String lecID){
        String regexStre = "[0-9]{8}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexStre);
        java.util.regex.Matcher m = p.matcher(lecID);
        return m.matches();
    }
}
