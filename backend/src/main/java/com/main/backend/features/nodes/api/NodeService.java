package com.main.backend.features.nodes.api;

import com.main.backend.features.nodes.domain.Node;
import com.main.backend.features.nodes.dto.TreePartsDTO;
import com.main.backend.features.nodes.entity.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {
    private final NodeRepository repository;

    @Autowired
    public NodeService(NodeRepository repository) {
        this.repository = repository;
    }

    public List<TreePartsDTO> getNodes() {
        List<NodeEntity> nodes = repository.findAll();
        List<TreePartsDTO> treePartsDTOList = new ArrayList<>();

        nodes.forEach(nodeEntity ->
                treePartsDTOList.addAll(Node.from(nodeEntity).toDTO())
        );
        return treePartsDTOList;
    }
}
