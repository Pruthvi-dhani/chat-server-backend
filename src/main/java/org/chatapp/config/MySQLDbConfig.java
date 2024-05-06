package org.chatapp.config;

import org.chatapp.dao.GroupRepository;
import org.chatapp.dao.MessageRepository;
import org.chatapp.dao.UserRepository;
import org.chatapp.model.ConversationModel;
import org.chatapp.model.GroupModel;
import org.chatapp.model.MessageModel;
import org.chatapp.model.UserModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MySQLDbConfig {
    @Bean("sessionFactory")
    public SessionFactory getSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, System.getenv("DB_URL"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, System.getenv("DB_USERNAME"))
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, System.getenv("DB_PASSWORD"))
                .addAnnotatedClass(UserModel.class)
                .addAnnotatedClass(UserRepository.class)
                .addAnnotatedClass(GroupModel.class)
                .addAnnotatedClass(GroupRepository.class)
                .addAnnotatedClass(ConversationModel.class)
                .addAnnotatedClass(MessageModel.class)
                .addAnnotatedClass(MessageRepository.class)
                .buildSessionFactory();
    }
}
