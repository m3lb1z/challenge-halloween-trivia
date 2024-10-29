
package emrx.halloween.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import emrx.halloween.model.DifficultyLevel;

public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel, Long> {
    Optional<DifficultyLevel> findByName(String name);
}
