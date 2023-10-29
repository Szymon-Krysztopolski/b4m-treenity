package com.main.backend.features.tree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class NodeDTO {
    private String id;
    private Data data;
    private Position position;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private String label;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Position {
        private Integer x;
        private Integer y;
    }
}
