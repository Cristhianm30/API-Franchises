package co.com.franchises.model.branch;

import co.com.franchises.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Branch {

    private Long id;
    private String name;
    private List<Product> products;

}
