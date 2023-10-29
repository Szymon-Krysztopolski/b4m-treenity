package com.main.backend.features.tree.api;

import com.main.backend.features.tree.domain.Node;
import com.main.backend.features.tree.dto.TreePartsDTO;
import com.main.backend.features.tree.entity.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {
    private final NodeRepository repository;

    @Autowired
    public NodeService(NodeRepository repository) {
        this.repository = repository;
    }

    public TreePartsDTO getTree() {
        List<NodeEntity> nodes = repository.findAll();
        TreePartsDTO tree = new TreePartsDTO();

        nodes.forEach(nodeEntity -> {
            TreePartsDTO treeToAdd = Node.from(nodeEntity).toDTO();
            tree.getNodes().addAll(treeToAdd.getNodes());
            tree.getEdges().addAll(treeToAdd.getEdges());
        });

        return tree;
    }
}
