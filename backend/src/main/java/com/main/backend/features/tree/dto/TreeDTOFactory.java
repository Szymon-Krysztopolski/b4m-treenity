package com.main.backend.features.tree.dto;

import com.main.backend.features.tree.domain.Node;

public class TreeDTOFactory {
    public static TreePartDTO createTreePart(Node node) {
        TreePartDTO treePart = new TreePartDTO();

        treePart.setNode(
                NodeDTO.builder()
                        .id(node.getId())
                        .data(new NodeDTO.Data((
                                node.hasParent()
                                        ? String.valueOf(node.getPathValue())
                                        : node.getId()
                        )))
                        .position(new NodeDTO.Position(50, 50))
                        .build()
        );

        if(node.hasParent()) {
            final Node parent = node.getParentNode();
            treePart.setParentEdge(
                    EdgeDTO.builder()
                            .id(String.format("edge---%s::%s", parent.getId(), node.getId()))
                            .source(parent.getId())
                            .target(node.getId())
                            .label(String.valueOf(node.getStepValue()))
                            .build()
            );
        }

        return treePart;
    }
}
