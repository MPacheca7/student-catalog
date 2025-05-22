package com.example.microservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDTO {

    private String name;
    private List<StudentGradeDTO> studentGrades;
}
