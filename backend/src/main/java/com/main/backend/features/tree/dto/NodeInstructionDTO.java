package com.main.backend.features.tree.dto;

import lombok.Getter;

/**
 * Object that comes from outside to {@link com.main.backend.features.tree.api.TreeController}.
 * It contains data necessary to work with CRUD methods.
 */
@Getter
public class NodeInstructionDTO {
    private String sessionToken;
    private String parentId;
    private String label;
    private Integer stepValue;
}
