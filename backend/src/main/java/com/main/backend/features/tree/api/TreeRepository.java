package com.main.backend.features.tree.api;

import com.main.backend.features.tree.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<NodeEntity, String> {
}
