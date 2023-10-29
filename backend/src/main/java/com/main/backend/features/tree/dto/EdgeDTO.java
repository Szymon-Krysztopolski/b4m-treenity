package com.main.backend.features.tree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class EdgeDTO {
    private String id;
    private String source;
    private String target;
    private String label;
}
