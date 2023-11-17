package com.main.backend.features.tree.api;

import com.main.backend.features.token.api.TokenService;
import com.main.backend.features.tree.domain.Node;
import com.main.backend.features.tree.exception.TreeException;
import com.main.backend.features.tree.entity.NodeEntity;
import com.main.backend.features.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TreeService {
    private final TreeRepository repository;
    private final TokenService tokenService;

    @Autowired
    public TreeService(TreeRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public List<Node> getNodeList(String sessionToken) throws Exception {
        UserEntity user = tokenService.checkTokenAndGetUser(sessionToken);
        List<NodeEntity> nodeEntityList = repository.findAllByOwner(user);

        return nodeEntityList.stream().map(Node::from).toList();
    }

    public String addNode(String sessionToken, String parentId, String label, Integer stepValue) throws Exception {
        UserEntity user = tokenService.checkTokenAndGetUser(sessionToken);
        String newNodeId = String.valueOf(UUID.randomUUID());

        NodeEntity parent = (parentId != null && repository.existsByIdAndOwner(parentId, user)
                ? repository.getReferenceByIdAndOwner(parentId, user)
                : null
        );

        if (parent == null) {
            stepValue = null;
            log.debug("Root cannot have a stepValue.");
        } else if (stepValue == null) {
            throw new TreeException("The new node should have a value if it is not the root!");
        }
        if (label == null || label.isBlank()) throw new TreeException("The new node should be properly labeled!");

        repository.saveAndFlush(NodeEntity.builder()
                .id(newNodeId)
                .owner(user)
                .label(label)
                .parentNode(parent)
                .stepValue(stepValue)
                .build()
        );

        return String.format("Node {%s} added successfully", newNodeId);
    }

    public String updateNode(String sessionToken, String id, String parentId, String label, Integer stepValue) throws Exception {
        UserEntity user = tokenService.checkTokenAndGetUser(sessionToken);
        if (id == null || id.isBlank() || !repository.existsByIdAndOwner(id, user)) {
            log.error("Node {} not found!", id);
            throw new TreeException("No node has been selected! Select proper value!");
        }

        NodeEntity nodeToChange = repository.getReferenceByIdAndOwner(id, user);
        if (parentId != null && repository.existsByIdAndOwner(parentId, user)) {
            NodeEntity potentialParent = repository.getReferenceByIdAndOwner(parentId, user);
            if (potentialParent.isYou(nodeToChange)) {
                log.error("Incorrect node selected as parent");
                throw new TreeException("You cannot set you as your parent!");
            } else if (potentialParent.isYourParent(nodeToChange)) {
                log.debug("Changing the order of nodes");
                potentialParent.setParentNode(nodeToChange.getParentNode());
                potentialParent.setStepValue(nodeToChange.getStepValue());
                repository.save(potentialParent);
            }
            nodeToChange.setParentNode(potentialParent);
        } else {
            log.debug("Parent not found, creating a new root");
            nodeToChange.setParentNode(null);
        }
        if (label != null && !label.isBlank()) nodeToChange.setLabel(label);
        if (stepValue != null && nodeToChange.isNotRoot()) nodeToChange.setStepValue(stepValue);

        repository.saveAndFlush(nodeToChange);
        return String.format("Node {%s} updated successfully", id);
    }

    public String deleteNode(String sessionToken, String id) throws Exception {
        UserEntity user = tokenService.checkTokenAndGetUser(sessionToken);

        repository.deleteByIdAndOwner(id, user);
        return String.format("Node {%s} deleted successfully", id);
    }
}
