package com.main.backend.features.tree.api;

import com.main.backend.features.tree.dto.TreeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class TreeController {
    private final TreeService service;

    @Autowired
    public TreeController(TreeService service) {
        this.service = service;
    }

    @GetMapping("/tree")
    public ResponseEntity<TreeDTO> getTree() {
        try {
            log.info("Downloading of the entire tree");
            return ResponseEntity.ok(service.getTree());
        } catch (Exception ex) {
            log.error("Error with getTree()!", ex);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/nodes/{id}")
    public ResponseEntity<String> addNode(@PathVariable String id, @RequestBody Integer stepValue) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        String response = "Error when adding node!";

        try {
            log.info("Adding new node: {} to tree", id);
            status = HttpStatus.OK;
            response = service.addNode(id, stepValue);
        } catch (Exception ex) {
            log.error(response, ex);
        }

        log.info("Node added successful");
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/nodes/{id}")
    public ResponseEntity<String> updateNode(@PathVariable String id) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        String response = "Error when updating node!";

        try {
            log.info("Updating node: {}", id);
            status = HttpStatus.OK;
            response = service.updateNode(id);
        } catch (Exception ex) {
            log.error(response, ex);
        }

        log.info("Node updated successful");
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/nodes/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable String id) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        String response = "Error when deleting node!";

        try {
            log.info("Deleting node: {}", id);
            status = HttpStatus.OK;
            response = service.deleteNode(id);
        } catch (Exception ex) {
            log.error(response, ex);
        }

        log.info("Node deleted successful");
        return ResponseEntity.status(status).body(response);
    }
}
