package emrx.halloween.dto.question.validation;

import org.springframework.stereotype.Component;

import emrx.halloween.dto.question.QuestionDTO;
import jakarta.validation.ValidationException;

@Component
public class EnsureAnswerExists implements QuestionValidation {

  @Override
  public void validate(QuestionDTO questionDTO) {
    String correctAnswer = questionDTO.getCorrectAnswer().toLowerCase();
    boolean correctAnswerExists = questionDTO.getOptions().stream()
            .map(String::toLowerCase)
            .anyMatch(option -> option.equals(correctAnswer));

    if (!correctAnswerExists) {
        throw new ValidationException("La respuesta correcta no está entre las opciones");
    }
  }
}
