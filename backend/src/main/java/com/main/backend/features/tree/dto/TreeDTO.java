package com.main.backend.features.tree.dto;

import com.main.backend.features.tree.dto.internal.EdgeDTO;
import com.main.backend.features.tree.dto.internal.NodeDTO;
import com.main.backend.features.tree.dto.internal.TreeElementDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Main object of tree structure that is sent to the frontend.
 * It contains {@link NodeDTO} and {@link EdgeDTO} lists which represent entire tree structure.
 * Available for non-dto classes from {@link TreeDTOFactory}. The components of this class are not visible outside the DTO package.
 */
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TreeDTO {
    private List<NodeDTO> nodes = new ArrayList<>();
    private List<EdgeDTO> edges = new ArrayList<>();

    public boolean checkIfNodeExists(String id, String type, String data) {
        return nodes.contains(new NodeDTO(id, type, new NodeDTO.Data(data)));
    }

    public boolean checkIfEdgeExists(String id, String source, String destination, int label) {
        return edges.contains(new EdgeDTO(id, source, destination, String.valueOf(label)));
    }

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
