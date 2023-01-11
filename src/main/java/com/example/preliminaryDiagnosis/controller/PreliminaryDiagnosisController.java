package com.example.preliminaryDiagnosis.controller;

import com.example.preliminaryDiagnosis.apiMedic.dto.Gender;
import com.example.preliminaryDiagnosis.commons.Response;
import com.example.preliminaryDiagnosis.dto.DiagnosisRequestDto;
import com.example.preliminaryDiagnosis.dto.SaveDiagnosisRequestDto;
import com.example.preliminaryDiagnosis.service.PreliminaryDiagnosisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/preliminary-diagnosis")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PreliminaryDiagnosisController {

    final private PreliminaryDiagnosisService preliminaryDiagnosisService;

    @CrossOrigin
    @GetMapping("/status")
    public ResponseEntity<Response<?>> healthCheck(){
        return new ResponseEntity<>(new Response<>(true, "Welcome to Preliminary Diagnosis Service"), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/symptoms")
    public ResponseEntity<Response<?>> getSymptoms(){
        try {
            return new ResponseEntity<>(new Response<>(true,  preliminaryDiagnosisService.getSymptoms()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response<>(false,  ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/diagnosis")
    public ResponseEntity<Response<?>> getDiagnosis(@RequestBody final DiagnosisRequestDto diagnosisRequestDto){
        try {
            return new ResponseEntity<>(new Response<>(true,  preliminaryDiagnosisService.getDiagnosis(diagnosisRequestDto)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response<>(false,  ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/save-diagnosis")
    public ResponseEntity<Response<?>> saveDiagnosis(@RequestBody final SaveDiagnosisRequestDto saveDiagnosisRequestDto){
        try {
            return new ResponseEntity<>(new Response<>(true,  preliminaryDiagnosisService.saveDiagnosis(saveDiagnosisRequestDto)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response<>(false,  ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/get-diagnosis/{identifier}")
    public ResponseEntity<Response<?>> getDatabaseDiagnosis(@PathVariable final String identifier){
        try {
            return new ResponseEntity<>(new Response<>(true,  preliminaryDiagnosisService.getDatabaseDiagnosis(identifier)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response<>(false,  ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }



}
