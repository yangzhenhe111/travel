package com.cy.travels.service;

import com.cy.travels.model.dto.UserDTO;
import com.cy.travels.model.entity.User;

import java.util.List;

public interface UserService {

    UserDTO findUser(UserDTO userDTO);

    int register(UserDTO userDTO);

    UserDTO editSaveUserInfo(UserDTO userDTO);

    UserDTO updatePwd(UserDTO userDTO);

    UserDTO updateHeadImg(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

    List<User> getLoginUserList();
}
