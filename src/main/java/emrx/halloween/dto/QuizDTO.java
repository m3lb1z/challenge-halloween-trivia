package emrx.halloween.dto;

import java.util.List;

import emrx.halloween.dto.question.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {
  private String difficulty;
  private List<QuestionDTO> questions;
}
