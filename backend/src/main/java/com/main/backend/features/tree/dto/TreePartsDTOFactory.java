package com.main.backend.features.tree.dto;

import com.main.backend.features.tree.domain.Node;

import java.util.List;

public class TreePartsDTOFactory {
    public static TreePartsDTO create(Node node) {
        TreePartsDTO tree = new TreePartsDTO();

        tree.setNodes(List.of(NodeDTO.builder()
                .id(node.getId())
                .data(NodeDTO.Data.builder().label(String.valueOf(node.getStepValue())).build())
                .build()));

        tree.setEdges(
                node.getChildNodes().stream().map(childNode -> EdgeDTO.builder()
                        .id(String.format("edge---%s::%s", node.getId(), childNode.getId()))
                        .source(node.getId())
                        .target(childNode.getId())
                        .build()
                ).toList()
        );

        return tree;
    }
}
