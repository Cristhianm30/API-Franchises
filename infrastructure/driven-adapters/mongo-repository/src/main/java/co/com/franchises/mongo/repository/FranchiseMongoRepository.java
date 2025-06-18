package co.com.franchises.mongo.repository;

import co.com.franchises.mongo.document.FranchiseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface FranchiseMongoRepository extends ReactiveMongoRepository<FranchiseDocument, String>, ReactiveQueryByExampleExecutor<FranchiseDocument> {

    Mono<FranchiseDocument> findByName(String name);

    Mono<FranchiseDocument> findByBranchesId(String branchId);

    Mono<FranchiseDocument> findByBranchesProductsId(String productId);
}
