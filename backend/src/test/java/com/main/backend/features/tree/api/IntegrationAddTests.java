package com.main.backend.features.tree.api;

import com.main.backend.features.tree.api.utils.IntegrationTestsUtils;
import com.main.backend.features.tree.domain.TreeException;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreeDTOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IntegrationAddTests {
    @Autowired
    private TreeService service;
    private final IntegrationTestsUtils utils = new IntegrationTestsUtils();
    private int initNumberOfNodes, initNumberOfEdges;

    @BeforeEach
    void setUp() {
        final TreeDTO tree = getTreeDTO();
        initNumberOfNodes = tree.getNodes().size();
        initNumberOfEdges = tree.getEdges().size();
    }

    @Test
    void addNewRootSuccessfully() throws TreeException {
        // when
        final String response = service.addNode(null, "example", null);
        final String newNodeId = utils.getIdFromResponse(response);

        // then
        checkTreeSize(initNumberOfNodes + 1, initNumberOfEdges);
        assertTrue(utils.checkIfNodeExists(getTreeDTO(), newNodeId, "input", "example"));
    }

    @Test
    void addTwoNodesSuccessfully() throws TreeException {
        // when
        final String newNodeId1 = utils.getIdFromResponse(service.addNode("root", "example-1", 1));
        final String newNodeId2 = utils.getIdFromResponse(service.addNode(newNodeId1, "example-2", 2));

        // then
        final TreeDTO newTree = getTreeDTO();
        checkTreeSize(initNumberOfNodes + 2, initNumberOfEdges + 2);

        // and then
        assertTrue(utils.checkIfNodeExists(newTree, newNodeId2, "output", "example-2 | value = 3"));
        assertTrue(utils.checkIfEdgeExists(newTree, newNodeId1, newNodeId2, 2));
    }

    @Test
    void whenNodeIsAddedWithoutLabelThenException() {
        // when
        TreeException exception = assertThrows(TreeException.class, () ->
                utils.getIdFromResponse(service.addNode(null, null, null))
        );

        // then
        assertEquals("The new node should be properly labeled!", exception.getMessage());
    }

    @Test
    void whenNodeIsAddedWithoutStepValueThenException() {
        // when
        TreeException exception = assertThrows(TreeException.class, () ->
                utils.getIdFromResponse(service.addNode("root", "example", null))
        );

        // then
        assertEquals("The new node should have a value if it is not the root!", exception.getMessage());
    }


    private void checkTreeSize(int expectedNumberOfNodes, int expectedNumberOfEdges) {
        final TreeDTO tree = getTreeDTO();
        assertEquals(expectedNumberOfNodes, tree.getNodes().size());
        assertEquals(expectedNumberOfEdges, tree.getEdges().size());
    }

    private TreeDTO getTreeDTO() {
        return TreeDTOFactory.createTree(service.getNodeList());
    }
}