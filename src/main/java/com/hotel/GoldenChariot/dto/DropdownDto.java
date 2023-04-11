package com.hotel.GoldenChariot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DropdownDto {
    public Long longValue;
    public String stringValue;
    public String text;

    public DropdownDto(String stringValue, String text){
        this.stringValue = stringValue;
        this.text = text;
    }

    public DropdownDto(Long longValue, String text){
        this.longValue = longValue;
        this.text = text;
    }
}
