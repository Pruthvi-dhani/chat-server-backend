package org.chatapp.dao;

import org.chatapp.model.UserModel;
import org.hibernate.Session;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.processing.CheckHQL;
import org.springframework.stereotype.Repository;

@CheckHQL
@NamedQuery(name="findUserById", query="from UserModel where id = :id")
public class UserRepository {
    public static UserModel findUserById(Session session, int id) {
        return session.createNamedQuery("findUserById", UserModel.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
