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

    public String addNode(String parentId, String label, Integer stepValue) {
        String newNodeId = String.valueOf(UUID.randomUUID());

        repository.saveAndFlush(NodeEntity.builder()
                .id(newNodeId)
                .label(label)
                .parentNode(repository.getReferenceById(parentId))
                .stepValue(stepValue)
                .build());

        return String.format("Node %s added successfully", newNodeId);
    }

    public String updateNode(String id, String parentId, String label, Integer stepValue) {
        NodeEntity nodeToChange = repository.getReferenceById(id);
        if (label != null) nodeToChange.setLabel(label);
        if (stepValue != null) nodeToChange.setStepValue(stepValue);
        if (parentId != null) nodeToChange.setParentNode(repository.getReferenceById(parentId));

        repository.saveAndFlush(nodeToChange);
        return "Node updated successfully";
    }

    public String deleteNode(String id) {
        repository.deleteById(id);
        return "Node deleted successfully";
    }
}
