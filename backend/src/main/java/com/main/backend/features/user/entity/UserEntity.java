package com.main.backend.features.user.entity;

import com.main.backend.features.tree.entity.NodeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String username;
    private String passwordHash;

    @Column(unique = true)
    private String email;
    private Boolean isActive;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<NodeEntity> nodes;

    @PrePersist
    public void prePersist() {
        isActive = false;
    }
}
