package com.main.backend.features.tree.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TreeControllerTest {
    private final TreeService service;

    @Autowired
    TreeControllerTest(TreeService service) {
        this.service = service;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTree() {
    }

    @Test
    void addNode() {
    }

    @Test
    void updateNode() {
    }

    @Test
    void deleteNode() {
    }
}