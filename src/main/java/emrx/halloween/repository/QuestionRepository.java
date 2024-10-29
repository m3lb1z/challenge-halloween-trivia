

package emrx.halloween.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import emrx.halloween.model.Question;
import emrx.halloween.model.Category;
import emrx.halloween.model.DifficultyLevel;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);
    Page<Question> findByCategory(Category category, Pageable pageable);
    Page<Question> findByDifficultyLevel(DifficultyLevel difficultyLevel, Pageable pageable);
    Page<Question> findByCategoryAndDifficultyLevel(Category category, DifficultyLevel difficultyLevel, Pageable pageable);
}
