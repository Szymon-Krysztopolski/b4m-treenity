package com.main.backend.features.nodes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EdgeDTO implements TreePartsDTO {
    private String id;
    private String source;
    private String target;
}
