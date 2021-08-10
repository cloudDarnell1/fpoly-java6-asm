package fpoly.java6.assignment.repository;

import fpoly.java6.assignment.model.Receipt;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByUserIdAndDate(long userId, Date date, Sort sort);

    List<Receipt> findByUserId(Long id, Sort st);
}
