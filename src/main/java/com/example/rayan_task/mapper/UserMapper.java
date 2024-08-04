package com.example.rayan_task.mapper;

import com.example.rayan_task.entity.User;
import com.example.rayan_task.model.UserDTO;
import org.mapstruct.Mapper;

/**
 * Author: ASOU SAFARI
 * Date:8/3/24
 * Time:9:44 PM
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
   UserDTO userToUserDTO(User user);
   UserDTO userDTOToUser(UserDTO userDTO);
}
