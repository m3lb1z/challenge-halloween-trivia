package emrx.halloween.dto.question.validation;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import emrx.halloween.dto.question.QuestionDTO;
import emrx.halloween.model.DifficultyLevel;
import jakarta.validation.ValidationException;

@Component
public class DifficultyLevelInMemory implements QuestionValidation {

  @Override
  public void validate(QuestionDTO questionDTO) {
    List<String> difficultyLevels = Arrays.stream(DifficultyLevel.values())
                                          .map(Enum::name)
                                          .toList();

    if (!difficultyLevels.contains(questionDTO.getDifficulty())) {
      throw new ValidationException("Nivel de dificultad no válido");
    }
  }
}
