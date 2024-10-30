package emrx.halloween.dto.question.validation;

import emrx.halloween.dto.question.QuestionDTO;

public interface QuestionValidation {
  
  void validate(QuestionDTO questionDTO);
}
