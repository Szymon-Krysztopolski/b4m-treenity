package com.main.backend.features.tree.api;

import com.main.backend.features.tree.dto.TreePartsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TreeController {
    private final TreeService service;

    @Autowired
    public TreeController(TreeService service) {
        this.service = service;
    }

    @GetMapping("/tree")
    public ResponseEntity<TreePartsDTO> getTree() {
        //return ResponseEntity.ok(service.getTree());
        return ResponseEntity.status(HttpStatus.OK).body(service.getTree());
    }
}