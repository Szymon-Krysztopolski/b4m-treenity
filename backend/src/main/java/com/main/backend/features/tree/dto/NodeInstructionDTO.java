package com.main.backend.features.tree.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NodeInstructionDTO {
    private String parentId;
    private String label;
    private Integer stepValue;
}