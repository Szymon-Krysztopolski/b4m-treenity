package com.main.backend.features.tree.dto;

import com.main.backend.features.tree.dto.internal.EdgeDTO;
import com.main.backend.features.tree.dto.internal.NodeDTO;
import com.main.backend.features.tree.dto.internal.TreeElementDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TreeDTO {
    private String message;
    private List<NodeDTO> nodes = new ArrayList<>();
    private List<EdgeDTO> edges = new ArrayList<>();

    public boolean checkIfNodeExists(String id) {
        return idChecker(id, nodes);
    }

    public boolean checkIfEdgeExists(String id) {
        return idChecker(id, edges);
    }

    private boolean idChecker(String id, List<? extends TreeElementDTO> treePartList) {
        for (TreeElementDTO treeElementDTO : treePartList) {
            if (treeElementDTO.getId().equals(id))
                return true;
        }

        return false;
    }
}
