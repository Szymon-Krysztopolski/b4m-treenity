package com.main.backend.features.tree.api;

import com.main.backend.features.tree.domain.TreeException;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreeDTOFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IntegrationTests {
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
    void checkIfTreeContainsInitialNodesAndEdges() {
        // given
        List<String> listOfNodes = new ArrayList<>(List.of("root", "node-1", "node-2", "node-3", "node-4", "last"));
        List<String> listOfEdges = new ArrayList<>(List.of(
                "edge---root::node-1",
                "edge---root::node-2",
                "edge---node-1::node-3",
                "edge---node-1::node-4",
                "edge---node-2::last"
        ));

        // when
        final TreeDTO tree = getTreeDTO();

        // then
        assertEquals(listOfNodes.size(), tree.getNodes().size());
        assertEquals(listOfEdges.size(), tree.getEdges().size());

        // and then
        listOfNodes.forEach(node -> assertTrue(utils.checkIfNodeExists(tree, node)));
        listOfEdges.forEach(edge -> assertTrue(utils.checkIfEdgeExists(tree, edge)));
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

    @Test
    void whenNodeUpdatedWithoutNodeIdThenException() {
        // when
        TreeException exception = assertThrows(TreeException.class, () ->
                utils.getIdFromResponse(service.updateNode(null, null, null, null))
        );

        // then
        assertEquals("No node has been selected! Select proper value!", exception.getMessage());
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

    @Test
    void deleteLastSingleNode() {
        // when
        assertTrue(utils.checkIfNodeExists(getTreeDTO(), "last"));
        service.deleteNode("last");

        // then
        checkTreeSize(initNumberOfNodes - 1, initNumberOfEdges - 1);
        assertFalse(utils.checkIfNodeExists(getTreeDTO(), "last"));
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

    private TreeDTO getTreeDTO() {
        return TreeDTOFactory.createTree(service.getNodeList());
    }
}