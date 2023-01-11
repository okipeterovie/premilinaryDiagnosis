package com.example.preliminaryDiagnosis.service;

import com.example.preliminaryDiagnosis.dto.*;

import java.util.List;

public interface PreliminaryDiagnosisService {

    List<SymptomDto> getSymptoms();

    List<DiagnosisResponseDto> getDiagnosis(DiagnosisRequestDto diagnosisRequestDto);

    SaveDiagnosisResponseDto saveDiagnosis(SaveDiagnosisRequestDto saveDiagnosisRequestDto);

    DiagnosisResponseDto getDatabaseDiagnosis(String identifier);
}
