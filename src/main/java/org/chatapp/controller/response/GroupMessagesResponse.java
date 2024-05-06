package org.chatapp.controller.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GroupMessagesResponse {
    @Data
    public static class MessageResponse {
        private int id;

        private String content;

        private String postDate;
    }

    private int groupId;

    private int lastSeenId;

    private List<MessageResponse> messages = new ArrayList<>();

}
