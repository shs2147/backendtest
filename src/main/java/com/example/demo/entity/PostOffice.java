package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostOffice {
    @JsonProperty("District")
    private String district;
    @JsonProperty("Country")
    private String country;

}