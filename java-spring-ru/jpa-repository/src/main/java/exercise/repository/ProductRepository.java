package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceGreaterThanOrderByPriceAsc(int price);
    List<Product> findByPriceLessThanOrderByPriceAsc(int price);

    @Query("SELECT p FROM Product p WHERE p.price >= ?1 AND p.price <= ?2 ORDER BY p.price ASC")
    List<Product> findAllByPriceeee(@Param("min") int min, @Param("max") int max);
    // END
}
