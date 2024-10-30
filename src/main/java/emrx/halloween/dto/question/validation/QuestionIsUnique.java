package emrx.halloween.dto.question.validation;

import org.springframework.stereotype.Component;

import emrx.halloween.dto.question.QuestionDTO;
import emrx.halloween.repository.QuestionRepository;
import jakarta.validation.ValidationException;

@Component
public class QuestionIsUnique implements QuestionValidation {

  private final QuestionRepository questionRepository;

  public QuestionIsUnique(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  @Override
  public void validate(QuestionDTO questionDTO) {
    if (questionRepository.existsByQuestion(questionDTO.getQuestion())) {
      throw new ValidationException("La pregunta ya existe");
    }
  }
}
