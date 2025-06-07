package co.com.franchises.usecase.branch;

import co.com.franchises.model.branch.Branch;
import co.com.franchises.model.franchise.gateways.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class BranchUseCase {
    private final FranchiseRepository franchiseRepository;

    public Mono<Branch> addBranch(String franchiseId, Branch newBranch) {

        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new Exception("Franchise not found with id: " + franchiseId)))
                .flatMap(franchise -> {
                    Branch branchToAdd = newBranch.toBuilder()
                            .id(UUID.randomUUID().toString())
                            .products(new ArrayList<>())
                            .build();

                    franchise.getBranches().add(branchToAdd);
                    return franchiseRepository.save(franchise)
                            .thenReturn(branchToAdd);
                });
    }
}
