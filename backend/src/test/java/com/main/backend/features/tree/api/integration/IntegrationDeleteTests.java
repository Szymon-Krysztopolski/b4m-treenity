//package com.main.backend.features.tree.api.integration;
//
//import com.main.backend.features.tree.api.TreeService;
//import com.main.backend.features.tree.api.utils.IntegrationTestsUtils;
//import com.main.backend.features.tree.dto.TreeDTO;
//import com.main.backend.features.tree.dto.TreeDTOFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class IntegrationDeleteTests {
//    @Autowired
//    private TreeService service;
//    private final IntegrationTestsUtils utils = new IntegrationTestsUtils();
//    private int initNumberOfNodes, initNumberOfEdges;
//
//    @BeforeEach
//    void setUp() {
//        final TreeDTO tree = getTreeDTO();
//        initNumberOfNodes = tree.getNodes().size();
//        initNumberOfEdges = tree.getEdges().size();
//    }
//
//    @Test
//    void deleteLastSingleNode() {
//        // when
//        assertTrue(utils.checkIfNodeExists(getTreeDTO(), "last"));
//        service.deleteNode("last");
//
//        // then
//        checkTreeSize(initNumberOfNodes - 1, initNumberOfEdges - 1);
//        assertFalse(utils.checkIfNodeExists(getTreeDTO(), "last"));
//    }
//
//    @Test
//    void deleteRoot() {
//        // when
//        service.deleteNode("root");
//
//        // then
//        checkTreeSize(0, 0);
//    }
//
//    private void checkTreeSize(int expectedNumberOfNodes, int expectedNumberOfEdges) {
//        final TreeDTO tree = getTreeDTO();
//        assertEquals(expectedNumberOfNodes, tree.getNodes().size());
//        assertEquals(expectedNumberOfEdges, tree.getEdges().size());
//    }
//
//    private TreeDTO getTreeDTO() {
//        return TreeDTOFactory.createTree(service.getNodeList());
//    }
//}