package com.main.backend.features.nodes.api;

import com.main.backend.features.nodes.dto.TreePartsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NodeController {
    private final NodeService service;

    @Autowired
    public NodeController(NodeService service) {
        this.service = service;
    }

    @GetMapping("/tree")
    public ResponseEntity<TreePartsDTO> getTree() { // todo return HttpResponse
        //return ResponseEntity.ok(service.getTree());
        return ResponseEntity.status(HttpStatus.OK).body(service.getTree());
    }
}
