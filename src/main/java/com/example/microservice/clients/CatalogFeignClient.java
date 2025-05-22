package com.example.microservice.clients;

import com.example.microservice.DTO.CatalogDTO;
import com.example.microservice.DTO.GradeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "grade")
public interface CatalogFeignClient {
    @GetMapping("/api/course/{courseId}")
    CatalogDTO getCourse(@PathVariable("courseId") String courseId);

    @GetMapping("/api/course/grade/{courseId}")
    GradeDTO[] getGrades(@PathVariable("courseId") String courseId);
}
