
package emrx.halloween.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import emrx.halloween.model.Quiz;
import emrx.halloween.model.Category;
import emrx.halloween.model.DifficultyLevel;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(Category category);
    List<Quiz> findByDifficultyLevel(DifficultyLevel difficultyLevel);
    List<Quiz> findByCategoryAndDifficultyLevel(Category category, DifficultyLevel difficultyLevel);
}
