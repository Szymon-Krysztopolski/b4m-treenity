package com.main.backend.features.tree.api;

import com.main.backend.features.tree.domain.Node;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreePartDTO;
import com.main.backend.features.tree.entity.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TreeService {
    private final TreeRepository repository;

    @Autowired
    public TreeService(TreeRepository repository) {
        this.repository = repository;
    }

    public TreeDTO getTree() {
        List<NodeEntity> nodes = repository.findAll();
        TreeDTO tree = new TreeDTO();

        nodes.forEach(nodeEntity -> {
            TreePartDTO partToAdd = Node.from(nodeEntity).toTreePartDTO();

            if (partToAdd.hasNode()) tree.getNodes().add(partToAdd.getNode());
            if (partToAdd.hasParentEdge()) tree.getEdges().add(partToAdd.getParentEdge());
        });

        return tree;
    }

    public String addNode(String parentNodeId, String label, Integer stepValue) {
        repository.saveAndFlush(NodeEntity.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .label(label)
                .parentNode(repository.getReferenceById(parentNodeId))
                .stepValue(stepValue)
                .build());

        return "OK";
    }

    public String updateNode(String id) { // TODO
        return null;
    }

    public String deleteNode(String id) { // TODO
        return null;
    }
}
