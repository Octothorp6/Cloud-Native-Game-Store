package com.trilogyed.adminapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {

    public static void main (String [] args){

        PasswordEncoder enc = new BCryptPasswordEncoder();

        //Add security roles...set password..print said password to note for DB insertion
        String adminPass = "admin";
        String adminEnc = enc.encode(adminPass);
        System.out.println("admin password: = "+adminEnc);

        String managerPass = "manager";
        String managerEnc = enc.encode(managerPass);
        System.out.println("manager password:  = "+managerEnc);

        String teamLeadPass = "teamlead";
        String teamLeadEnc = enc.encode(teamLeadPass);
        System.out.println("team-lead password:  = "+teamLeadEnc);

        String employeePass = "employee";
        String employeeEnc = enc.encode(employeePass);
        System.out.println("manager password:  = "+employeeEnc);

    }
}
