package org.chatapp.controller;

import jakarta.persistence.NoResultException;
import org.chatapp.controller.response.GroupMessagesResponse;
import org.chatapp.dao.GroupRepository;
import org.chatapp.dao.MessageRepository;
import org.chatapp.dao.UserRepository;
import org.chatapp.model.GroupModel;
import org.chatapp.model.MessageModel;
import org.chatapp.model.UserModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ChatMessagesApiController {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping("ping")
    public String healthCheckApi() {
        return "Pong100";
    }

    @GetMapping("api/v1/user")
    public UserModel getSingleUser(@RequestParam(name = "userId") Integer id) {
        return sessionFactory.fromTransaction(session -> UserRepository.findUserById(session, id));
    }

    @GetMapping("api/v1/messages")
    public GroupMessagesResponse getMessagesOfGroup(@RequestParam(name = "groupId") Integer groupId,
                                                    @RequestParam(name = "lastSeenId") Integer lastSeenId) {
        List<MessageModel> messages;
        try {
            messages = sessionFactory.fromTransaction(
                    session -> MessageRepository.fetchMessagesFromGroup(session, groupId, lastSeenId));
        } catch (NoResultException nre) {
            messages = new ArrayList<>();
        }
        GroupMessagesResponse messagesResponse = new GroupMessagesResponse();
        messagesResponse.setGroupId(groupId);
        int updatedLastSeenId = Integer.MAX_VALUE;
        for(var message: messages) {
            updatedLastSeenId = Math.min(updatedLastSeenId, message.getId());
            var messageResp = new GroupMessagesResponse.MessageResponse();
            messageResp.setId(message.getId());
            messageResp.setContent(message.getMessageContent());
            messageResp.setPostDate(message.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            messagesResponse.getMessages().add(messageResp);
        }
        messagesResponse.setLastSeenId(updatedLastSeenId);
        return messagesResponse;
    }
}
