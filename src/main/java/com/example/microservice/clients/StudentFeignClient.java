package com.example.microservice.clients;

import com.example.microservice.DTO.StudentGradeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "student")
public interface StudentFeignClient {
    @GetMapping("/api/student/{studentId}")
    StudentGradeDTO getStudent(@PathVariable("studentId") String studentId);
}
