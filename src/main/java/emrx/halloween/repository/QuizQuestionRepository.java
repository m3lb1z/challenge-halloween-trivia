

package emrx.halloween.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import emrx.halloween.model.QuizQuestion;
import emrx.halloween.model.Quiz;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    List<QuizQuestion> findByQuiz(Quiz quiz);
}
