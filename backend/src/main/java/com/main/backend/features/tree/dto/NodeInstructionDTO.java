package com.main.backend.features.tree.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object that comes from outside to {@link com.main.backend.features.tree.api.TreeController}.
 * It contains data necessary to work with CRUD methods.
 */
@Data
@NoArgsConstructor
public class NodeInstructionDTO {
    private String parentId;
    private String label;
    private Integer stepValue;
}
