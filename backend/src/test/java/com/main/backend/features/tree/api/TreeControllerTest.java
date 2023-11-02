package com.main.backend.features.tree.api;

import com.main.backend.features.tree.domain.TreeException;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreeDTOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TreeControllerTest {
    @Autowired
    private TreeService service;
    private int initNumberOfNodes, initNumberOfEdges;

    @BeforeEach
    void setUp() {
        final TreeDTO tree = getTreeDTO();
        initNumberOfNodes = tree.getNodes().size();
        initNumberOfEdges = tree.getEdges().size();
    }

    @Test
    void getTree() {
        // when
        final TreeDTO tree = getTreeDTO();

        // then
        assertEquals(6, tree.getNodes().size());
        assertEquals(5, tree.getEdges().size());
    }

    @Test
    void addNewRootSuccessfully() throws TreeException {
        // when
        service.addNode(null, "example", 1);

        // then
        checkTreeSize(initNumberOfNodes + 1, initNumberOfEdges);
    }

    @Test
    void addNodeSuccessfully() throws TreeException {
        // when
        service.addNode("root", "example", 1);

        // then
        checkTreeSize(initNumberOfNodes + 1, initNumberOfEdges + 1);
    }

    @Test
    void updateNode() {
    }

    @Test
    void deleteLastSingleNode() {
        // when
        assertTrue(getTreeDTO().checkIfNodeExists("last"));
        service.deleteNode("last");

        // then
        checkTreeSize(initNumberOfNodes - 1, initNumberOfEdges - 1);
        assertFalse(getTreeDTO().checkIfNodeExists("last"));
    }

    @Test
    void deleteRoot() {
        // when
        service.deleteNode("root");

        // then
        checkTreeSize(0, 0);
    }

    private void checkTreeSize(int expectedNumberOfNodes, int expectedNumberOfEdges) {
        final TreeDTO tree = getTreeDTO();
        assertEquals(expectedNumberOfNodes, tree.getNodes().size());
        assertEquals(expectedNumberOfEdges, tree.getEdges().size());
    }

    private boolean checkIfNodeExists(String id) {
        return getTreeDTO().checkIfNodeExists(id);
    }

    private boolean checkIfEdgeExists(String id) {
        return getTreeDTO().checkIfEdgeExists(id);
    }

    private TreeDTO getTreeDTO() {
        return TreeDTOFactory.createTree(service.getNodeList());
    }

    private String getIdFromResponse(String input) {
        Matcher matcher = Pattern.compile("\\{([^}]*)\\}").matcher(input);
        return (matcher.find() ? matcher.group(1) : "");
    }
}