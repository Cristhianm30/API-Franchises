package co.com.franchises.model.franchise.gateways;

import co.com.franchises.model.franchise.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {

    Mono<Franchise> findById(String id);

    Mono<Franchise> save(Franchise franchise);

    Mono<Franchise> findByName(String name);

    Mono<Franchise> findByBranchId(String branchId);

    Mono<Franchise> findByProductsId(String productId);

}
