package com.main.backend.features.nodes.dto;

import com.main.backend.features.nodes.domain.Node;

import java.util.ArrayList;
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
