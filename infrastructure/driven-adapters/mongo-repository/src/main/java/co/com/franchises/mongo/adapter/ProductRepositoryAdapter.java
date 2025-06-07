package co.com.franchises.mongo.adapter;

import co.com.franchises.model.product.Product;
import co.com.franchises.model.product.gateways.ProductRepository;
import co.com.franchises.mongo.document.ProductDocument;
import co.com.franchises.mongo.helper.AdapterOperations;
import co.com.franchises.mongo.repository.ProductMongoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductDocument, String, ProductMongoRepository>
        implements ProductRepository {

    public ProductRepositoryAdapter(ProductMongoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

}
