package com.main.backend.features.tree.api;

import com.main.backend.features.tree.domain.Node;
import com.main.backend.features.tree.domain.TreeException;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreePartDTO;
import com.main.backend.features.tree.entity.NodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
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

    public String addNode(String parentId, String label, Integer stepValue) throws TreeException {
        String newNodeId = String.valueOf(UUID.randomUUID());

        NodeEntity parent = (repository.existsById(parentId) ? repository.getReferenceById(parentId) : null);
        if (parent == null) {
            stepValue = null;
            log.debug("Root cannot have a stepValue.");
        } else {
            if (stepValue == null) {
                throw new TreeException("The new node should have a value if it is not the root!");
            }
        }
        if (label == null || label.isBlank()) throw new TreeException("The new node should be properly labeled!");

        repository.saveAndFlush(NodeEntity.builder()
                .id(newNodeId)
                .label(label)
                .parentNode(parent)
                .stepValue(stepValue)
                .build()
        );

        return String.format("Node %s added successfully", newNodeId);
    }

    public String updateNode(String id, String parentId, String label, Integer stepValue) throws TreeException {
        if (id == null || id.isBlank() || !repository.existsById(id)) {
            log.error("Node {} not found!", id);
            throw new TreeException("No node has been selected! Select proper value!");
        }

        NodeEntity nodeToChange = repository.getReferenceById(id);
        if (repository.existsById(parentId)) {
            NodeEntity potentialParent = repository.getReferenceById(parentId);
            if (potentialParent.isYourParentOrYou(nodeToChange)) {
                log.error("Incorrect node selected as parent");
                throw new TreeException("You cannot set you, or your child as your parent!");
            }
            nodeToChange.setParentNode(potentialParent);
        } else {
            log.debug("Parent not found, creating a new root");
            nodeToChange.setParentNode(null);
        }
        if (label != null && !label.isBlank()) nodeToChange.setLabel(label);
        if (stepValue != null && !nodeToChange.isRoot()) nodeToChange.setStepValue(stepValue);

        repository.saveAndFlush(nodeToChange);
        return "Node updated successfully";
    }

    public String deleteNode(String id) {
        repository.deleteById(id);
        return "Node deleted successfully";
    }
}
