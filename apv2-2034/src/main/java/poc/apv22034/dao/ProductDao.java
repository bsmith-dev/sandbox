package poc.apv22034.dao;

import org.springframework.stereotype.Repository;
import poc.apv22034.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public class ProductDao {

    public Flux<Product> findAll() {
        Product product1 = new Product(1, "Laptop", 1000.00);
        Product product2 = new Product(2, "Phone", 500.00);
        Product product3 = new Product(3, "TV", 1500.00);

        return Flux.just(product1, product2, product3);
    }
    public Mono<Product> getById(Integer id) {
        return findAll()
                .filter(product -> product.getId() == id)
                .singleOrEmpty();
    }
}
