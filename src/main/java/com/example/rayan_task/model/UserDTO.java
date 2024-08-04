package com.example.rayan_task.model;

import lombok.Builder;
import lombok.Data;

/**
 * Author: ASOU SAFARI
 * Date:8/3/24
 * Time:5:05 PM
 */
@Data
@Builder
public class UserDTO {
    private String username;
    private String password;
}
