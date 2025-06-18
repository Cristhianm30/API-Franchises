package co.com.franchises.usecase.franchise;

import co.com.franchises.model.franchise.Franchise;
import co.com.franchises.model.franchise.gateways.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class FranchiseUseCase {
    private final FranchiseRepository franchiseRepository;

    public Mono<Franchise> createFranchise(Franchise franchise) {
        String id = UUID.randomUUID().toString();
        Franchise franchiseToSave = franchise.toBuilder()
                .id(id)
                .branches(new ArrayList<>())
                .build();
        return franchiseRepository.save(franchiseToSave);
    }

    public Mono<Franchise> findByName(String name) {
        return franchiseRepository.findByName(name);
    }
}
