package emrx.halloween.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;
    
    @NotBlank
    private String question;

    @NotNull
    @Size(min = 4, max = 5)
    private List<String> options;

    @NotBlank
    private String correctAnswer;
    
    @NotBlank
    private String difficulty;
    
    @NotBlank
    private String category;
}
