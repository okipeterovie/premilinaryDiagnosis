package com.example.preliminaryDiagnosis.apiMedic.mapper;

import com.example.preliminaryDiagnosis.apiMedic.dto.ApiMedicDiagnosis;
import com.example.preliminaryDiagnosis.dto.DiagnosisResponseDto;

public class DiagnosisMapper {

    public static DiagnosisResponseDto mapToDomain(final ApiMedicDiagnosis model) {
        return DiagnosisResponseDto.builder()
                .id(model.getIssue() != null ? model.getIssue().getID() : null)
                .name(model.getIssue() != null ? model.getIssue().getName() : "")
                .ranking(model.getIssue() != null ? model.getIssue().getRanking() : null)
                .build();
    }
}
