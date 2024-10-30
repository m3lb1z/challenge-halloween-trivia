
package emrx.halloween.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import emrx.halloween.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
