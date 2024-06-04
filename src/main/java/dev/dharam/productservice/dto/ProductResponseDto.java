package dev.dharam.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDto {
    private Long productId;
    private String title;
    private double price;
    private String description;
    private String categoryName;
    private String ImageURL;
    private ProductRatingDto rating;
    private Instant createdAt;
    private Instant updatedAt;


}
