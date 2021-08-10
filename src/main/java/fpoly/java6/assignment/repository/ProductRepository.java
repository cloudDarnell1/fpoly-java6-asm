package fpoly.java6.assignment.repository;

import fpoly.java6.assignment.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(Long categoryId, Pageable page);

    @Query(nativeQuery = true, value = "select * from product where name like :name")
    List<Product> findByName(@Param("name") String name);
}
