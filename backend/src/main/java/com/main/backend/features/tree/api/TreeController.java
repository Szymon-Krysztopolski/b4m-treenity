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
    public ResponseEntity<String> addNode(@PathVariable String id) {
        // todo
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PutMapping("/nodes/{id}")
    public ResponseEntity<String> updateNode(@PathVariable String id) {
        // todo
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/nodes/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable String id) {
        // todo
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
