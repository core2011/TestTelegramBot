package com.mybottest.controller;

import com.mybottest.model.User;
import com.mybottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public User saveUserIfNoExist(Long chatId, String userName, String firstName) {
        User user = new User();
        user.setChatId(chatId);
        user.setUserName(userName);
        user.setFirstName(firstName);
        var optionalUser = userService.findByChatId(chatId);
        if (optionalUser.isEmpty()) {
            return userService.save(user);
        } else {
            return user;
        }
    }
}
