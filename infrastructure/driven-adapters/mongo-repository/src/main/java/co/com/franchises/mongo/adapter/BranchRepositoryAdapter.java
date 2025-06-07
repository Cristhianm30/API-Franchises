package co.com.franchises.mongo.adapter;

import co.com.franchises.model.branch.Branch;
import co.com.franchises.model.branch.gateways.BranchRepository;
import co.com.franchises.mongo.document.BranchDocument;
import co.com.franchises.mongo.helper.AdapterOperations;
import co.com.franchises.mongo.repository.BranchMongoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepositoryAdapter extends AdapterOperations<Branch, BranchDocument, String, BranchMongoRepository>
        implements BranchRepository {

    public BranchRepositoryAdapter(BranchMongoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Branch.class));
    }

}
