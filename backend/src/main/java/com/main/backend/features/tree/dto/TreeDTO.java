package com.main.backend.features.tree.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeDTO {
    private String message;
    private List<NodeDTO> nodes = new ArrayList<>();
    private List<EdgeDTO> edges = new ArrayList<>();
}
