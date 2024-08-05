package com.example.rayan_task.controller;

import com.example.rayan_task.service.ActionService;
import com.example.rayan_task.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: ASOU SAFARI
 * Date:8/5/24
 * Time:1:12 AM
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/actions")
public class ActionController {

    private final ActionService actionService;
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity doAction(@RequestParam String actionName,
            @RequestHeader(name = "Token") String token) {
        if (loginService.validTokenString(token)){
            actionService.performAction(actionName);
            return new ResponseEntity("Action Done", HttpStatus.OK);
        }
        return new ResponseEntity("Action field",HttpStatus.EXPECTATION_FAILED);
    }
}

