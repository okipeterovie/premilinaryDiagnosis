package com.example.preliminaryDiagnosis.service;

import com.example.preliminaryDiagnosis.apiMedic.service.ApiMedicService;
import com.example.preliminaryDiagnosis.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreliminaryDiagnosisServiceImpl implements PreliminaryDiagnosisService {

    private final ApiMedicService apiMedicService;

    HashMap<String, DiagnosisResponseDto> inMemory = new HashMap<>();


    @Override
    public List<SymptomDto> getSymptoms() {
        return apiMedicService.getSymptoms();
    }

    @Override
    public List<DiagnosisResponseDto> getDiagnosis(DiagnosisRequestDto diagnosisRequestDto) {
        return apiMedicService.getDiagnosis(diagnosisRequestDto);
    }

    @Override
    public SaveDiagnosisResponseDto saveDiagnosis(SaveDiagnosisRequestDto saveDiagnosisRequestDto) {
        inMemory.put(saveDiagnosisRequestDto.getIdentifier(), saveDiagnosisRequestDto.getDiagnosis());
        log.info(String.valueOf(inMemory));
        return SaveDiagnosisResponseDto.builder().identifier(saveDiagnosisRequestDto.getIdentifier()).build();
    }

    @Override
    public DiagnosisResponseDto getDatabaseDiagnosis(String identifier) {
        return inMemory.get(identifier);
    }

}
