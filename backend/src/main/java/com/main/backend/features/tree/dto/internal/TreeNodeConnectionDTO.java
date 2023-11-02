package com.main.backend.features.tree.dto.internal;

import lombok.Data;

@Data
public class TreeNodeConnectionDTO {
    private NodeDTO node;
    private EdgeDTO parentEdge;

    public boolean hasNode() {
        return node != null;
    }

    public boolean hasParentEdge() {
        return parentEdge != null;
    }
}
