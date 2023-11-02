package com.main.backend.features.tree.dto;

import com.main.backend.features.tree.domain.Node;

import java.util.List;

public class TreeDTOFactory {
    public static TreeDTO createTree(String message, List<Node> nodes) {
        TreeDTO tree = new TreeDTO();

        tree.setMessage(message);
        nodes.forEach(node -> {
            TreePartDTO treePart = createTreePart(node);
            if (treePart.getNode() != null) tree.getNodes().add(treePart.getNode());
            if (treePart.getParentEdge() != null) tree.getEdges().add(treePart.getParentEdge());
        });

        return tree;
    }

    private static TreePartDTO createTreePart(Node node) {
        TreePartDTO treePart = new TreePartDTO();

        treePart.setNode(
                NodeDTO.builder()
                        .id(node.getId())
                        .type(getType(node))
                        .data(new NodeDTO.Data((
                                node.hasParent()
                                        ? node.getLabel() + " | value = " + node.getPathValue()
                                        : node.getLabel()
                        )))
                        .build()
        );

        if (node.hasParent()) {
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

    private static String getType(Node node) {
        String type = null;

        if (!node.hasParent() && node.hasAnyChild()) type = "input";
        if (node.hasParent() && !node.hasAnyChild()) type = "output";

        return type;
    }
}
