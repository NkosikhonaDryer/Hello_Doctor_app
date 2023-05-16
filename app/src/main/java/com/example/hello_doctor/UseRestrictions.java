package com.example.hello_doctor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UseRestrictions {
    String firstname, lastname, email,IdNo,Time,date,natureOfAppoint;

    public  UseRestrictions(){
        firstname = lastname = email = IdNo = Time = date = natureOfAppoint = "";
    }

    public boolean isValidFnam(String firstname) {
        return !firstname.isEmpty() && firstname.length() != 0;
    }

    public boolean isValidLnam(String firstname) {
        return !firstname.isEmpty() && firstname.length() != 0;
    }
    public boolean isValidIdNo(String idNo) {

        boolean is = true;
        return !idNo.isEmpty() && idNo.length() != 0;
    }

    public boolean isValidEMail(String Email) {
        if(Email.isEmpty() || Email.length() == 0)
        {
            return Email.contains("@") && (Email.endsWith(".co.za") || Email.endsWith(".com"));
        }
        return true;
    }

    public boolean isValidTime(String time) {
        return !Time.isEmpty() && Time.length() != 0;
    }
    public boolean isValidDate(String Date) {
        return !date.isEmpty() && date.length() != 0;
    }
    public boolean isValidNOAP(String NOA) {
        return !NOA.isEmpty() && NOA.length() != 0;
    }


}
