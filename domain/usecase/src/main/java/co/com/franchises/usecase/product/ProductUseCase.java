package co.com.franchises.usecase.product;

import co.com.franchises.model.franchise.gateways.FranchiseRepository;
import co.com.franchises.model.product.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class ProductUseCase {
    private final FranchiseRepository franchiseRepository;

    public Mono<Product> addProduct(String branchId, Product product) {
        return franchiseRepository.findByBranchId(branchId)
                .switchIfEmpty(Mono.error(new RuntimeException("Branch not found with id: " + branchId)))
                .flatMap(franchise -> {
                    return franchise.getBranches().stream()
                            .filter(branch -> branch.getId().equals(branchId))
                            .findFirst()
                            .map(foundBranch -> {
                                Product productToAdd = product.toBuilder()
                                        .id(UUID.randomUUID().toString())
                                        .build();
                                foundBranch.getProducts().add(productToAdd);

                                return franchiseRepository.save(franchise)
                                        .thenReturn(productToAdd);
                            })
                            .orElse(Mono.error(new RuntimeException("Internal error: Branch not found in franchise")));
                });
    }

    public Mono<Void> deleteProduct(String productId) {
        return franchiseRepository.findByProductsId(productId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found with id: " + productId)))
                .flatMap(franchise -> {
                    franchise.getBranches().forEach(branch ->
                            branch.getProducts().removeIf(product -> product.getId().equals(productId))
                    );
                    return franchiseRepository.save(franchise).then();
                });
    }

    public Mono<Product> updateStock (String ProductId,int newStock){
        return franchiseRepository.findByProductsId(ProductId)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found with id: " + ProductId)))
                .flatMap(franchise -> {
                    return franchise.getBranches().stream()
                            .flatMap(branch -> branch.getProducts().stream())
                            .filter(product -> product.getId().equals(ProductId))
                            .findFirst()
                            .map(product -> {
                                product.setStock(newStock);
                                return franchiseRepository.save(franchise)
                                        .thenReturn(product);
                            })
                            .orElse(Mono.error(new RuntimeException("Internal error: Product not found in franchise")));
                });
    }


}
