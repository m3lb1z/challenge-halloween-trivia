package emrx.halloween.repository;

import emrx.halloween.model.Question;
import emrx.halloween.model.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    boolean existsByQuestion(String question);
    
    long countByDifficultyLevel(DifficultyLevel difficultyLevel);

    @Query(value = """
            SELECT q.* FROM question q
            WHERE q.difficulty_level = :difficultyLevel
            ORDER BY RANDOM() LIMIT 5
            """, nativeQuery = true)
    List<Question> findRandomQuestionsByDifficultyLevel(@Param("difficultyLevel") String difficultyLevel);
}
