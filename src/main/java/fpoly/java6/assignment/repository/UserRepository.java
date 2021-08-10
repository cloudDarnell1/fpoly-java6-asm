package fpoly.java6.assignment.repository;

import fpoly.java6.assignment.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    Page<User> findAllByRole(String role, Pageable page);

    @Modifying
    @Query(value = "update User u set u.active = false where u.id = :id")
    Integer disable(@Param("id") Long id);

    boolean existsByUsername(String username);

    @Modifying
    @Query(value = "update User u set u.active = true where u.id = :id")
    Integer enable(Long id);
}
