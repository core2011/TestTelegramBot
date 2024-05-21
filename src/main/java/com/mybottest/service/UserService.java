package com.mybottest.service;

import com.mybottest.model.User;
import com.mybottest.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepo userRepo;

    public Optional<User> findByChatId(Long chatId){
        return userRepo.findByChatId(chatId);
    }

    public User save(User user){
        return userRepo.save(user);
    }
}
