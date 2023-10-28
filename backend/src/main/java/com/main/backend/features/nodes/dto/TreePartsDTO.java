package com.main.backend.features.nodes.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreePartsDTO {
    private List<NodeDTO> nodes = new ArrayList<>();
    private List<EdgeDTO> edges = new ArrayList<>();
}
