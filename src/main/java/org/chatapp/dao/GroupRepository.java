package org.chatapp.dao;

import org.chatapp.model.GroupModel;
import org.hibernate.Session;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.processing.CheckHQL;

@CheckHQL
@NamedQuery(name = "fetchMessagesWithGroup",
            query = "from GroupModel gm left join fetch gm.messages me where gm.id = :groupId")
public class GroupRepository {
    public static GroupModel fetchGroupWithMessages(Session session, Integer groupId) {
        return session.createNamedQuery("fetchMessagesWithGroup", GroupModel.class)
                .setParameter("groupId", groupId)
                .getSingleResult();
    }
}
