package co.com.franchises.mongo.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "branches")
public class BranchDocument {

    @Id
    private String id;
    private String name;
    private List<ProductDocument> products;

}
