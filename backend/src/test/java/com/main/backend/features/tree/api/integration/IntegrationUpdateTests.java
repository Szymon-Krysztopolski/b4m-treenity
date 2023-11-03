package com.main.backend.features.tree.api.integration;

import com.main.backend.features.tree.api.TreeService;
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
class IntegrationUpdateTests {
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
    void whenSetNewNodeParentToItselfThenException() {
        // when
        TreeException exception = assertThrows(TreeException.class, () ->
                utils.getIdFromResponse(service.updateNode("root", "root", null, null))
        );

        // then
        assertEquals("You cannot set you as your parent!", exception.getMessage());
    }

    @Test
    void updateNodeSuccessful() throws TreeException {
        // given
        final TreeDTO tree = getTreeDTO();
        assertTrue(utils.checkIfNodeExists(tree, "node-3", "output", "node-3 | value = 4"));
        assertFalse(utils.checkIfNodeExists(tree, "node-3", "output", "newNode | value = 5"));
        assertTrue(utils.checkIfEdgeExists(tree, "node-1", "node-3", 3));

        // when
        service.updateNode("node-3", "root", "newNode", 5);

        // then
        final TreeDTO newTree = getTreeDTO();
        checkTreeSize(initNumberOfNodes, initNumberOfEdges);
        assertFalse(utils.checkIfNodeExists(newTree, "node-3", "output", "node-3 | value = 4"));
        assertTrue(utils.checkIfNodeExists(newTree, "node-3", "output", "newNode | value = 5"));
        assertTrue(utils.checkIfEdgeExists(newTree, "root", "node-3", 5));
    }

    @Test
    void swapWithParentSuccessful() throws TreeException {
        // when
        service.updateNode("root", "node-3", null, 1);

        // then
        final TreeDTO tree = getTreeDTO();
        checkTreeSize(initNumberOfNodes, initNumberOfEdges);
        assertTrue(utils.checkIfNodeExists(tree, "node-3", "input", "node-3"));
        assertTrue(utils.checkIfNodeExists(tree, "root", null, "root-1 | value = 1"));
        assertTrue(utils.checkIfEdgeExists(tree, "node-3", "root", 1));
    }

    @Test
    void updateNodeToRootWithoutChangingLabel() throws TreeException {
        // given
        final TreeDTO tree = getTreeDTO();
        assertTrue(utils.checkIfNodeExists(tree, "last", "output", "last | value = 7"));
        assertTrue(utils.checkIfEdgeExists(tree, "node-2", "last", 5));

        // when
        service.updateNode("last", null, null, null);

        // then
        final TreeDTO newTree = getTreeDTO();
        checkTreeSize(initNumberOfNodes, initNumberOfEdges - 1);
        assertFalse(utils.checkIfNodeExists(newTree, "last", "output", "last | value = 7"));
        assertFalse(utils.checkIfEdgeExists(newTree, "node-2", "last", 5));
        assertTrue(utils.checkIfNodeExists(newTree, "last", "input", "last"));
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