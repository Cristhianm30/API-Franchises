package co.com.franchises.mongo.repository;

import co.com.franchises.mongo.document.BranchDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface BranchMongoRepository extends ReactiveMongoRepository<BranchDocument, String>, ReactiveQueryByExampleExecutor<BranchDocument> {
}
