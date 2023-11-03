package com.main.backend.features.tree.domain;

import com.main.backend.features.tree.entity.NodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Layer used between Entity and DTO objects.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Node {
    private String id;
    private String label;
    private Integer stepValue;
    private Integer pathValue;
    private NodeEntity nodeEntity;

    public boolean hasParent() {
        return nodeEntity.getParentNode() != null;
    }

    public boolean hasAnyChild() {
        return nodeEntity != null
                && nodeEntity.getChildNodes() != null
                && !nodeEntity.getChildNodes().isEmpty();
    }

    public Node getParentNode() {
        return Node.from(nodeEntity.getParentNode());
    }

    public List<Node> getChildNodes() {
        return nodeEntity.getChildNodes()
                .stream()
                .map(Node::from)
                .collect(Collectors.toList());
    }

    public static Node from(NodeEntity entity) {
        return Node.builder()
                .id(entity.getId())
                .label(entity.getLabel())
                .stepValue(entity.getStepValue())
                .pathValue(entity.getPathValue())
                .nodeEntity(entity).build();
    }
}
