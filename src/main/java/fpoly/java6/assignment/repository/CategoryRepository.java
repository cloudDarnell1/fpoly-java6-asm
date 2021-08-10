package fpoly.java6.assignment.repository;

import fpoly.java6.assignment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM category LIMIT 1", nativeQuery = true)
    Category findOne();
}
