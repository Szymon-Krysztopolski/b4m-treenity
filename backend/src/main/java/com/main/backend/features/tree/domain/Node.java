package com.main.backend.features.tree.domain;

import com.main.backend.features.tree.dto.TreeDTOFactory;
import com.main.backend.features.tree.dto.TreePartDTO;
import com.main.backend.features.tree.entity.NodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Node {
    private String id;
    private Integer stepValue;
    private Integer pathValue;
    private NodeEntity nodeEntity;

    public boolean hasParent() {
        return nodeEntity.getParentNode() != null;
    }

    public boolean hasAnyChild() {
        return nodeEntity.getChildNodes() != null;
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

    public TreePartDTO toPartDTO() {
        return TreeDTOFactory.create(this);
    }

    public static Node from(NodeEntity entity) {
        return Node.builder()
                .id(entity.getId())
                .stepValue(entity.getStepValue())
                .pathValue(entity.getPathValue())
                .nodeEntity(entity).build();
    }
}
