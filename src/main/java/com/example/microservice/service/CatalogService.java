package com.example.microservice.service;

import com.example.microservice.DTO.CatalogDTO;
import com.example.microservice.DTO.GradeDTO;
import com.example.microservice.DTO.StudentGradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private RestTemplate restTemplate;

    public CatalogDTO getCatalogByCourseId(String courseId){
        CatalogDTO course = restTemplate.getForObject("http://localhost:8082/api/course/" + courseId, CatalogDTO.class);
        GradeDTO[] gradeDTO = restTemplate.getForObject("http://localhost:8082/api/course/grade/" + courseId, GradeDTO[].class);


        List<StudentGradeDTO> studentGrades = Arrays.stream(gradeDTO).map(grade -> {
            StudentGradeDTO student = restTemplate.getForObject(
                    "http://localhost:8081/api/students/" + courseId,
                    StudentGradeDTO.class
            );

            return new StudentGradeDTO(student.getStudentName(), student.getAge(), grade.getQualification());
        }).toList();

        return new CatalogDTO(course.getCourseName(), studentGrades);
    }
}
