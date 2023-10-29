package com.main.backend.features.tree.api;

import com.main.backend.features.tree.domain.Node;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreePartDTO;
import com.main.backend.features.tree.entity.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {
    private final TreeRepository repository;

    @Autowired
    public TreeService(TreeRepository repository) {
        this.repository = repository;
    }

    public TreeDTO getTree() {
        List<NodeEntity> nodes = repository.findAll();
        TreeDTO tree = new TreeDTO();

        nodes.forEach(nodeEntity -> {
            TreePartDTO treeToAdd = Node.from(nodeEntity).toPartDTO();

            tree.getNodes().add(treeToAdd.getNode());
            tree.getEdges().add(treeToAdd.getParentEdge());
        });

        return tree;
    }
}
