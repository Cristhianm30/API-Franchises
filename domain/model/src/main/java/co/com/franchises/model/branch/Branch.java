package co.com.franchises.model.branch;

import co.com.franchises.model.product.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Branch {

    private String id;
    private String name;
    private List<Product> products;

}
