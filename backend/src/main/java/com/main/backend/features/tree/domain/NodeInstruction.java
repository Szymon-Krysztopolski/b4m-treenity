package com.main.backend.features.tree.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NodeInstruction {
    private String parentId;
    private String label;
    private Integer stepValue;
}
