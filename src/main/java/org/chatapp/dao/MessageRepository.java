package org.chatapp.dao;

import org.chatapp.model.MessageModel;
import org.hibernate.Session;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.processing.CheckHQL;

import java.util.List;

@CheckHQL
@NamedQuery(name = "fetchMessagesSingleGroup",
            query = "from MessageModel m where m.groupModel.id = :group_id" +
                    " and m.isDeleted = false and m.id < :message_id" +
                    " order by m.id" +
                    " limit 20")
public class MessageRepository {
    public static List<MessageModel> fetchMessagesFromGroup(Session session, int groupId, int messageId) {
        return session.createNamedQuery("fetchMessagesSingleGroup", MessageModel.class)
                .setParameter("group_id", groupId)
                .setParameter("message_id", messageId)
                .getResultList();
    }
}
