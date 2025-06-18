package co.com.franchises.api;

import co.com.franchises.model.branch.Branch;
import co.com.franchises.model.franchise.Franchise;
import co.com.franchises.model.product.Product;
import co.com.franchises.usecase.branch.BranchUseCase;
import co.com.franchises.usecase.franchise.FranchiseUseCase;
import co.com.franchises.usecase.product.ProductUseCase;
import co.com.franchises.mongo.dto.UpdateStockDTO;
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
    private final BranchUseCase branchUseCase;
    private final ProductUseCase productUseCase;

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
                .flatMap(branch -> branchUseCase.addBranch(franchiseId, branch))
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

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        final String branchId = request.pathVariable("branchId");
        return request.bodyToMono(Product.class)
                .flatMap(product -> productUseCase.addProduct(branchId, product))
                .flatMap(savedBranch -> ServerResponse
                        .status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedBranch));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        final String productId = request.pathVariable("productId");
        return productUseCase.deleteProduct(productId)
                .then(ServerResponse.noContent().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> updateStock(ServerRequest request) {
        final String productId = request.pathVariable("productId");
        return request.bodyToMono(UpdateStockDTO.class)
                .flatMap(stock -> productUseCase.updateStock(productId, stock.getStock()))
                .flatMap(updatedProduct -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedProduct))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
}
