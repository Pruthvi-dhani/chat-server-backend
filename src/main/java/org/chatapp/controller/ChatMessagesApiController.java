package org.chatapp.controller;

import org.chatapp.dao.UserRepository;
import org.chatapp.model.UserModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatMessagesApiController {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("api/v1/user")
    public UserModel getSingleUser(@RequestParam(name = "userId") Integer id) {
        return sessionFactory.fromTransaction(session -> UserRepository.findUserById(session, id));
    }
}
