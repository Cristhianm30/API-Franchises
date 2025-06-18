package co.com.franchises.mongo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStockDTO {
    private Integer stock;
}