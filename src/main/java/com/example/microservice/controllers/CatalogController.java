package com.example.microservice.controllers;

import com.example.microservice.DTO.CatalogDTO;
import com.example.microservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CatalogDTO> getCatalog(@PathVariable String courseId) {
        return ResponseEntity.ok(catalogService.getCatalogByCourseId(courseId));
    }
}
