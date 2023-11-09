package com.main.backend.features.tree.api;

import com.main.backend.features.tree.dto.NodeInstructionDTO;
import com.main.backend.features.tree.domain.TreeException;
import com.main.backend.features.tree.dto.TreeDTO;
import com.main.backend.features.tree.dto.TreeDTOFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller providing simple rest api.
 * This layer uses DTO objects, so that the following layers (e.g. service) do not need to know about the existence of DTO objects.
 */
@RestController
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
            log.info("Downloading the tree");
            return ResponseEntity.ok(TreeDTOFactory.createTree(service.getNodeList()));
        } catch (Exception ex) {
            log.error("Error with getTree()!", ex);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/nodes")
    public ResponseEntity<String> addNode(@RequestBody NodeInstructionDTO instruction) {
        HttpStatus status;
        String response;

        try {
            log.info("Adding new node to parent: {}", instruction.getParentId());
            status = HttpStatus.OK;
            response = service.addNode(instruction.getParentId(), instruction.getLabel(), instruction.getStepValue());
            log.info("Node added successfully");
        } catch (TreeException ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error(response, ex);
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error during adding node!";
            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/nodes/{id}")
    public ResponseEntity<String> updateNode(@PathVariable String id, @RequestBody NodeInstructionDTO instruction) {
        HttpStatus status;
        String response;

        try {
            log.info("Updating node: {}", id);
            status = HttpStatus.OK;
            response = service.updateNode(id, instruction.getParentId(), instruction.getLabel(), instruction.getStepValue());
            log.info("Node updated successfully");
        } catch (TreeException ex) {
            status = HttpStatus.BAD_REQUEST;
            response = ex.getMessage();
            log.error(response, ex);
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error when updating node!";
            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/nodes/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable String id) {
        HttpStatus status;
        String response;

        try {
            log.info("Deleting node: {}", id);
            status = HttpStatus.OK;
            response = service.deleteNode(id);
            log.info("Node {} deleted successfully", id);
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error when deleting node!";
            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }
}
