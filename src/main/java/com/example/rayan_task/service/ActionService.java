package com.example.rayan_task.service;

import org.springframework.stereotype.Service;

/**
 * Author: ASOU SAFARI
 * Date:8/5/24
 * Time:1:01 AM
 */
@Service
public class ActionService {

    synchronized public void performAction(String action){
        try{
            System.out.println("Action : " + action.toUpperCase() +" running");
            Thread.sleep(10000);
            System.out.println(action.toUpperCase() + " completed");
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
