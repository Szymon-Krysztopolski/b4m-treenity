package com.main.backend.features.tree.dto;

import lombok.Data;

@Data
public class TreePartDTO {
    private NodeDTO node;
    private EdgeDTO parentEdge;
}
