package co.com.franchises.api;

import co.com.franchises.model.branch.Branch;
import co.com.franchises.model.franchise.Franchise;
import co.com.franchises.usecase.branch.BranchUseCase;
import co.com.franchises.usecase.franchise.FranchiseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final FranchiseUseCase franchiseUseCase;
    private final BranchUseCase BranchUseCase;

    public Mono<ServerResponse> createFranchise(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Franchise.class)
                .flatMap(franchiseUseCase::createFranchise)
                .flatMap(savedFranchise -> ServerResponse
                        .status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedFranchise));
    }

    public Mono<ServerResponse> addBranch(ServerRequest request) {
        final String franchiseId = request.pathVariable("franchiseId");
        return request.bodyToMono(Branch.class)
                .flatMap(branch -> BranchUseCase.addBranch(franchiseId, branch))
                .flatMap(savedBranch -> ServerResponse
                        .status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedBranch));
    }

    public Mono<ServerResponse> findFranchiseByName(ServerRequest request) {

        return request.queryParam("name")
                .map(franchiseUseCase::findByName)
                .map(franchisesFlux -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(franchisesFlux, Franchise.class))
                .orElse(ServerResponse.badRequest().bodyValue("Query param 'name' is required"));
    }
}
