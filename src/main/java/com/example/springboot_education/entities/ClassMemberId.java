package com.example.springboot_education.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClassMemberId implements Serializable {
   private Integer classId;
   private Integer studentId;

}
