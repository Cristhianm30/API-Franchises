package co.com.franchises.mongo.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "products")
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private Integer stock;

}
