package com.example.rayan_task.config;

import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:8/4/24
 * Time:11:25 AM
 */

public class Token {
    public static String generatetokenString(){
        return UUID.randomUUID().toString().toLowerCase();
    }

}
