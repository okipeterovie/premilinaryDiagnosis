package com.example.preliminaryDiagnosis.apiMedic.mapper;

import com.example.preliminaryDiagnosis.apiMedic.dto.ApiMedicSymptom;
import com.example.preliminaryDiagnosis.dto.SymptomDto;

public class SymptomMapper {

    public static SymptomDto mapToDomain(final ApiMedicSymptom model) {
        return SymptomDto.builder()
                .id(model.getID())
                .name(model.getName() != null ? model.getName() : "")
                .build();
    }
}
