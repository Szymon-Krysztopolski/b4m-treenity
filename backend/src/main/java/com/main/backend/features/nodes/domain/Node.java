package com.main.backend.features.nodes.domain;

import com.main.backend.features.nodes.dto.EdgeDTO;
import com.main.backend.features.nodes.dto.NodeDTO;
import com.main.backend.features.nodes.dto.TreePartsDTO;
import com.main.backend.features.nodes.entity.NodeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    public List<TreePartsDTO> toDTO() {
        List<TreePartsDTO> result = new ArrayList<>();
        result.add(NodeDTO.builder()
                .id(id)
                .data(NodeDTO.Data.builder().label(String.valueOf(stepValue)).build())
            .build());

        result.addAll(
                childNodes.stream().map(childNode -> EdgeDTO.builder()
                    .id(String.format("edge---%s::%s", id, childNode.getId()))
                    .source(id)
                    .target(childNode.getId())
                    .build()
                ).toList()
        );

        return result;
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
