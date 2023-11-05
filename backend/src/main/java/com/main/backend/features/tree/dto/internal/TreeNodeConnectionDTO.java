package com.main.backend.features.tree.dto.internal;

import lombok.Data;

/**
 * Class, which aggregates basic tree elements. This type of object can be used because each tree node has at most one parent.
 * This object contains one {@link NodeDTO} and connection to its parent represented by {@link EdgeDTO}.
 * It is used when creating a {@link com.main.backend.features.tree.dto.TreeDTO}.
 * In case of root parent edge doesn't exist (value is null).
 */
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
