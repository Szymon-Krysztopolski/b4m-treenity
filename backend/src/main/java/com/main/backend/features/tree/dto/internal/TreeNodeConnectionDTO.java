package com.main.backend.features.tree.dto.internal;

import lombok.Data;

/**
 * Class, which aggregates basic tree elements.
 * It is used when creating a {@link com.main.backend.features.tree.dto.TreeDTO}.
 * Tree is created from smaller parts, in this case from {@link NodeDTO} and connection to its parent represented by {@link EdgeDTO} object.
 * In case of root edge doesn't exist (value is null).
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
