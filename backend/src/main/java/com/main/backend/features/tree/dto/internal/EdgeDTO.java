package com.main.backend.features.tree.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdgeDTO implements TreeElementDTO {
    private String id;
    private String source;
    private String target;
    private String label;
}
