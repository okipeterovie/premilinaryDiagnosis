package com.example.preliminaryDiagnosis.apiMedic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiMedicDiagnosis {
        @JsonProperty
        ApiMedicDiagnosisIssue Issue;
        @JsonProperty
        List<ApiMedicDiagnosisSpecialisation> Specialisation;

        @Data
        public static class ApiMedicDiagnosisIssue {
                @JsonProperty
                Long ID;
                @JsonProperty
                String Name;
                @JsonProperty
                Long Accuracy;
                @JsonProperty
                String Icd;
                @JsonProperty
                String IcdName;
                @JsonProperty
                String Meteorism;
                @JsonProperty
                Long Ranking;
        }

        @Data
        public static class ApiMedicDiagnosisSpecialisation {
                @JsonProperty
                Long ID;
                @JsonProperty
                String Name;
                @JsonProperty
                Long SpecialistID;
        }
}
