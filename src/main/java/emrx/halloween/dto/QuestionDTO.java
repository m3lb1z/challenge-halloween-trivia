package emrx.halloween.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;
    
    @NotBlank
    private String question;

    @NotNull
    private List<String> options;

    @NotBlank
    private String correctAnswer;
    
    @NotBlank
    private String difficulty;
    
    @NotBlank
    private String category;
}
