package com.example.preliminaryDiagnosis.dto;

import com.example.preliminaryDiagnosis.apiMedic.dto.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisRequestDto {
    Integer[] symptomsId;
    Gender gender;
    String yearOfBirth;
}
