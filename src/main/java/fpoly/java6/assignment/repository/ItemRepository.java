package fpoly.java6.assignment.repository;

import fpoly.java6.assignment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
