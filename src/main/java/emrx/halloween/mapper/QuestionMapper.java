package emrx.halloween.mapper;

import emrx.halloween.dto.QuestionDTO;
// import emrx.halloween.dto.QuestionPage;
import emrx.halloween.dto.QuizDTO;
import emrx.halloween.model.DifficultyLevel;
import emrx.halloween.model.Question;
import emrx.halloween.model.QuestionOption;
import emrx.halloween.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public QuestionDTO toDto(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setQuestion(question.getQuestion());
        questionDTO.setOptions(question.getOptions().stream().map(
            QuestionOption -> QuestionOption.getOption()
        ).toList());
        questionDTO.setCorrectAnswer(question.getOptions().stream().filter(QuestionOption -> QuestionOption.isCorrect()).findFirst().map(QuestionOption -> QuestionOption.getOption()).orElse(null));
        questionDTO.setDifficulty(question.getDifficultyLevel().toString());
        questionDTO.setCategory(question.getCategory().getName());
        return questionDTO;
    }

    public Question toEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        
        question.setQuestion(questionDTO.getQuestion());
        question.setCategory(categoryRepository.findByName(questionDTO.getCategory()).orElse(null));
        question.setDifficultyLevel(DifficultyLevel.valueOf(questionDTO.getDifficulty().toUpperCase()));

        List<QuestionOption> options = questionDTO.getOptions().stream()
                .map(option -> {
                    QuestionOption questionOption = new QuestionOption();
                    questionOption.setOption(option);
                    questionOption.setCorrect(option.equals(questionDTO.getCorrectAnswer()));
                    questionOption.setQuestion(question);
                    return questionOption;
                })
                .collect(Collectors.toList());

        question.setOptions(options);

        return question;
    }

    public List<QuestionDTO> toDto(List<Question> questions) {
        return questions.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Question> toEntities(List<QuestionDTO> questionDTOs) {
        return questionDTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public QuizDTO toQuizDTO(List<Question> questions) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuestions(questions.stream().map(this::toDto).collect(Collectors.toList()));
        // Assuming all questions have the same difficulty level
        quizDTO.setDifficulty(questions.get(0).getDifficultyLevel().toString());
        return quizDTO;
    }
    
}
