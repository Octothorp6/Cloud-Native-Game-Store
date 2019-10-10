package com.trilogyed.adminapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {

    public static void main (String [] args){

        PasswordEncoder encode = new BCryptPasswordEncoder();

        //Add security roles...set password..print said password to note for DB insertion
    }
}
