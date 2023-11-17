package com.main.backend.features.tree.api;

import com.main.backend.features.tree.entity.NodeEntity;
import com.main.backend.features.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<NodeEntity, String> {
    List<NodeEntity> findAllByOwner(UserEntity owner);

    boolean existsByIdAndOwner(String id, UserEntity owner);

    NodeEntity getReferenceByIdAndOwner(String id, UserEntity owner);

    void deleteByIdAndOwner(String id, UserEntity owner);
}
