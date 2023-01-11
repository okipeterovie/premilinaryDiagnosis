package com.example.preliminaryDiagnosis.apiMedic.dto;

public enum Gender {
    male("male"),
    female("female");

    final private String value;

    Gender(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
