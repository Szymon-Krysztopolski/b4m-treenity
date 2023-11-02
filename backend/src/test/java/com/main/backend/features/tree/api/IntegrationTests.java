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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IntegrationTests {
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
        listOfNodes.forEach(node -> assertTrue(tree.checkIfNodeExists(node)));
        listOfEdges.forEach(edge -> assertTrue(tree.checkIfEdgeExists(edge)));
    }

    @Test
    void addNewRootSuccessfully() throws TreeException {
        // when
        final String response = service.addNode(null, "example", null);
        final String newNodeId = getIdFromResponse(response);

        // then
        checkTreeSize(initNumberOfNodes + 1, initNumberOfEdges);
        assertTrue(getTreeDTO().checkIfNodeExists(newNodeId, "input", "example"));
    }

    @Test
    void addTwoNodesSuccessfully() throws TreeException {
        // when
        final String newNodeId1 = getIdFromResponse(service.addNode("root", "example-1", 1));
        final String newNodeId2 = getIdFromResponse(service.addNode(newNodeId1, "example-2", 2));

        // then
        final TreeDTO newTree = getTreeDTO();
        checkTreeSize(initNumberOfNodes + 2, initNumberOfEdges + 2);

        // and then
        assertTrue(newTree.checkIfNodeExists(newNodeId2, "output", "example-2 | value = 3"));
        assertTrue(newTree.checkIfEdgeExists(getEdgeId(newNodeId1, newNodeId2), newNodeId1, newNodeId2, 2));
    }

    @Test
    void whenNodeIsAddedWithoutLabelThenException() {
        // when
        TreeException exception = assertThrows(TreeException.class, () -> {
            getIdFromResponse(service.addNode(null, null, null));
        });

        // then
        assertEquals("The new node should be properly labeled!", exception.getMessage());
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

    private TreeDTO getTreeDTO() {
        return TreeDTOFactory.createTree(service.getNodeList());
    }

    private String getIdFromResponse(String input) {
        Matcher matcher = Pattern.compile("\\{([^}]*)\\}").matcher(input);
        return (matcher.find() ? matcher.group(1) : "");
    }

    private String getEdgeId(String source, String destination) {
        return String.format("edge---%s::%s", source, destination);
    }
}