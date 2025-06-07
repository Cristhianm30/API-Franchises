package co.com.franchises.model.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {

    private String id;
    private String name;
    private Integer stock;

}
