package org.chatapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Group;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "conversations")
public class ConversationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupModel group;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean isDeleted;

    @Column(name = "created_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime updatedTime;
}
