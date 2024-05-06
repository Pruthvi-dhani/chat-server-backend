package org.chatapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.chatapp.enums.GroupTypeEnum;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "group_chats")
public class GroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "group_type", columnDefinition = "ENUM('private_conv', 'group_conv') NOT NULL DEFAULT 'private_conv'"
    )
    @Enumerated(EnumType.STRING)
    private GroupTypeEnum groupType;

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

    @OneToMany(mappedBy = "groupModel", fetch = FetchType.EAGER)
    private List<MessageModel> messages = new ArrayList<>();
}
