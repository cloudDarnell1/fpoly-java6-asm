package fpoly.java6.assignment.repository;

import fpoly.java6.assignment.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
