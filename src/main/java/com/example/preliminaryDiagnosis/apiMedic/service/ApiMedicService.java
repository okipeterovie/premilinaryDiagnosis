package com.example.preliminaryDiagnosis.apiMedic.service;

import com.example.preliminaryDiagnosis.apiMedic.dto.ApiMedicDiagnosis;
import com.example.preliminaryDiagnosis.apiMedic.dto.ApiMedicSymptom;
import com.example.preliminaryDiagnosis.apiMedic.dto.Gender;
import com.example.preliminaryDiagnosis.apiMedic.mapper.DiagnosisMapper;
import com.example.preliminaryDiagnosis.apiMedic.mapper.SymptomMapper;
import com.example.preliminaryDiagnosis.dto.DiagnosisRequestDto;
import com.example.preliminaryDiagnosis.dto.DiagnosisResponseDto;
import com.example.preliminaryDiagnosis.dto.SymptomDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiMedicService {

    private final RestTemplate restTemplate;

    @Value("${vendors.apiMedic.base-url}")
    String baseUrl;

    @Value("${vendors.apiMedic.auth-token}")
    String authToken;

    @Value("${vendors.apiMedic.format}")
    String format;

    @Value("${vendors.apiMedic.language}")
    String language;

    private static final String GET_SYMPTOMS_PATH = "%s/symptoms";

    private static final String GET_DIAGNOSIS_PATH = "%s/diagnosis";

    public List<SymptomDto> getSymptoms() {
        final String requestUrl = String.format(GET_SYMPTOMS_PATH, baseUrl);

        try {
            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromHttpUrl(requestUrl)
                            .queryParam("token", authToken)
                            .queryParam("format", format)
                            .queryParam("language", language);

            final ApiMedicSymptom[] exchange =
                    restTemplate.getForObject(
                            builder.toUriString(),
                            ApiMedicSymptom[].class
                    );

            List<SymptomDto> symptomDtoList = new ArrayList<>();

            if(exchange != null){

                log.info(Arrays.toString(exchange));
                symptomDtoList = Stream.of(exchange).map(SymptomMapper::mapToDomain).collect(Collectors.toList());
            }

            return symptomDtoList;
        } catch (final HttpClientErrorException ex) {
            ex.printStackTrace();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            throw new InvalidParameterException(ex.getResponseBodyAsString());
        }
    }

    public List<DiagnosisResponseDto> getDiagnosis(DiagnosisRequestDto diagnosisRequestDto) {
        final String requestUrl = String.format(GET_DIAGNOSIS_PATH, baseUrl);

        Integer[] symptomIds = diagnosisRequestDto.getSymptomsId();
        Gender gender = diagnosisRequestDto.getGender();
        String yearOfBirth = diagnosisRequestDto.getYearOfBirth();

        if(symptomIds.length == 0){
            throw new InvalidParameterException("Symptom Ids: [" + Arrays.toString(symptomIds) + "] supplied is invalid. Symptom Ids cannot be empty");
        }

        if(gender == null){
            throw new InvalidParameterException("Gender: [" + gender + "] supplied is invalid. Gender cannot be empty or null");
        }

        String yearOfBirthRegex = "^[12][0-9]{3}$";
        Matcher matcher = Pattern.compile(yearOfBirthRegex).matcher(yearOfBirth);


        if(!matcher.find()){
            throw new InvalidParameterException("Date of birth: [" + yearOfBirth + "] supplied is invalid.");
        }

        try {
            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromHttpUrl(requestUrl)
                            .queryParam("symptoms", Arrays.toString(symptomIds))
                            .queryParam("gender", gender.getValue())
                            .queryParam("year_of_birth", yearOfBirth)
                            .queryParam("token", authToken)
                            .queryParam("format", format)
                            .queryParam("language", language);
            log.info(builder.toUriString());

            String newRequestUrl = builder.toUriString().replace("%5B", "[");
            newRequestUrl = newRequestUrl.replace("%5D", "]");
            newRequestUrl = newRequestUrl.replace("%20", "");
            log.info(newRequestUrl);

            final ApiMedicDiagnosis[] exchange =
                    restTemplate.getForObject(
                            newRequestUrl,
                            ApiMedicDiagnosis[].class
                    );

            List<DiagnosisResponseDto> diagnosisResponseDtoList = new ArrayList<>();

            if(exchange != null){

                log.info(Arrays.toString(exchange));
                diagnosisResponseDtoList = Stream.of(exchange).map(DiagnosisMapper::mapToDomain).collect(Collectors.toList());
            }

            return diagnosisResponseDtoList;
        } catch (final HttpClientErrorException ex) {
            ex.printStackTrace();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            throw new InvalidParameterException(ex.getResponseBodyAsString());
        }
    }
}
