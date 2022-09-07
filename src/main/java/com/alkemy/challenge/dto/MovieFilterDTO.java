package com.alkemy.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieFilterDTO {

    private String name;

    private Long genre;

    private String order;

    public boolean isASC(){return this.order.compareToIgnoreCase ("ASC")==0;}
}
