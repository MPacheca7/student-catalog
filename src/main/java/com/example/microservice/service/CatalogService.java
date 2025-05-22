package com.example.microservice.service;

import com.example.microservice.DTO.CatalogDTO;
import com.example.microservice.DTO.GradeDTO;
import com.example.microservice.DTO.StudentGradeDTO;
import com.example.microservice.clients.CatalogFeignClient;
import com.example.microservice.clients.StudentFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CatalogService {

   /* @Autowired
    private RestTemplate restTemplate; */

    @Autowired
    private StudentFeignClient studentFeignClient;

    @Autowired
    private CatalogFeignClient catalogFeignClient;

    public CatalogDTO getCatalogByCourseId(String courseId){
        CatalogDTO course = catalogFeignClient.getCourse(courseId);
        GradeDTO[] gradeDTOs = catalogFeignClient.getGrades(courseId);

        List<StudentGradeDTO> studentGrades = Arrays.stream(gradeDTOs).map(grade -> {
            StudentGradeDTO student = studentFeignClient.getStudent(String.valueOf(grade.getStudentId()));
            return new StudentGradeDTO(student.getName(), student.getAge(), grade.getQualification());
        }).toList();

        return new CatalogDTO(course.getName(), studentGrades);
    }
}
