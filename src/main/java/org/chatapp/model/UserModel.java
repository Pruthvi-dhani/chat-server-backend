package org.chatapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "display_name",length = 256, nullable = false)
    private String displayName;

    @Column(length = 256)
    private String email;

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
