package co.com.franchises.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/franchises"), handler::createFranchise)
                .andRoute(POST("/api/franchises/{franchiseId}/branches"), handler::addBranch)
                .andRoute(GET("/api/franchises"), handler::findFranchiseByName)
                .andRoute(POST("/api/branches/{branchId}/products"), handler::addProduct)
                .andRoute(DELETE("/api/products/{productId}"), handler::deleteProduct)
                .andRoute(PATCH("/api/products/{productId}/stock"), handler::updateStock);
    }
}
