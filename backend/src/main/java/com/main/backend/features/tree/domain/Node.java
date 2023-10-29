package com.main.backend.features.tree.domain;

import com.main.backend.features.tree.dto.TreePartsDTO;
import com.main.backend.features.tree.dto.TreePartsDTOFactory;
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
    private List<Node> childNodes;

    public TreePartsDTO toDTO() {

        return TreePartsDTOFactory.create(this);
    }

    public static Node from(NodeEntity entity) {
        Node.NodeBuilder builder = Node.builder()
                .id(entity.getId())
                .stepValue(entity.getStepValue())
                .pathValue(entity.getPathValue());

        if (entity.getChildNodes() != null) {
            List<Node> childNodes = entity.getChildNodes().stream()
                    .map(Node::from)
                    .collect(Collectors.toList());
            builder.childNodes(childNodes);
        }

        return builder.build();
    }
}
