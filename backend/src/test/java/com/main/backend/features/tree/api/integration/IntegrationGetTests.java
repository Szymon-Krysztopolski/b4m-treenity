//package com.main.backend.features.tree.api.integration;
//
//import com.main.backend.features.tree.api.TreeService;
//import com.main.backend.features.tree.api.utils.IntegrationTestsUtils;
//import com.main.backend.features.tree.dto.TreeDTO;
//import com.main.backend.features.tree.dto.TreeDTOFactory;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class IntegrationGetTests {
//    @Autowired
//    private TreeService service;
//    private final IntegrationTestsUtils utils = new IntegrationTestsUtils();
//
//    @Test
//    void checkIfTreeContainsInitialNodesAndEdges() {
//        // given
//        List<String> listOfNodes = new ArrayList<>(List.of("root", "node-1", "node-2", "node-3", "node-4", "last"));
//        List<String> listOfEdges = new ArrayList<>(List.of(
//                "edge---root::node-1",
//                "edge---root::node-2",
//                "edge---node-1::node-3",
//                "edge---node-1::node-4",
//                "edge---node-2::last"
//        ));
//
//        // when
//        final TreeDTO tree = getTreeDTO();
//
//        // then
//        assertEquals(listOfNodes.size(), tree.getNodes().size());
//        assertEquals(listOfEdges.size(), tree.getEdges().size());
//
//        // and then
//        listOfNodes.forEach(node -> assertTrue(utils.checkIfNodeExists(tree, node)));
//        listOfEdges.forEach(edge -> assertTrue(utils.checkIfEdgeExists(tree, edge)));
//    }
//
//    private TreeDTO getTreeDTO() {
//        return TreeDTOFactory.createTree(service.getNodeList());
//    }
//}