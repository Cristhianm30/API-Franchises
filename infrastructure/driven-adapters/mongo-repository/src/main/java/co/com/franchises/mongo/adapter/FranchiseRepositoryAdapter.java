package co.com.franchises.mongo.adapter;

import co.com.franchises.model.franchise.Franchise;
import co.com.franchises.model.franchise.gateways.FranchiseRepository;
import co.com.franchises.mongo.document.FranchiseDocument;
import co.com.franchises.mongo.helper.AdapterOperations;
import co.com.franchises.mongo.repository.FranchiseMongoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class FranchiseRepositoryAdapter extends AdapterOperations<Franchise, FranchiseDocument, String, FranchiseMongoRepository>
        implements FranchiseRepository {

    public FranchiseRepositoryAdapter(FranchiseMongoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Franchise.class));
    }


    @Override
    public Mono<Franchise> findByName(String name) {
        return repository.findByName(name)
                .map(this::toEntity);
    }
}
