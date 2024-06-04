package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRatingDto {
    private double rate;
    private int count;
}
