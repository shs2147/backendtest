package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PinCodeDetails {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("PostOffice")

    private List<PostOffice> PostOffice ;
}
