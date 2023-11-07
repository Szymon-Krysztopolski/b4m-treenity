package com.main.backend.features.user.entity;

import com.main.backend.features.tree.entity.NodeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

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

    @OneToMany(mappedBy = "owner")
    private List<NodeEntity> nodes;
}
