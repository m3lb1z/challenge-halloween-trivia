

package emrx.halloween.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import emrx.halloween.model.QuestionOption;
import emrx.halloween.model.Question;

public interface QuestionOptionRepository extends JpaRepository<QuestionOption, Long> {
    List<QuestionOption> findByQuestion(Question question);
    Optional<QuestionOption> findByQuestionAndCorrect(Question question, boolean correct);
}
