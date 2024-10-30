package emrx.halloween.mapper;

// import emrx.halloween.dto.QuestionPage;
import emrx.halloween.dto.QuizDTO;
import emrx.halloween.dto.question.QuestionDTO;
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
        questionDTO.setOptions(mapOptionsToDto(question.getOptions()));
        questionDTO.setCorrectAnswer(findCorrectAnswer(question.getOptions()));
        questionDTO.setDifficulty(question.getDifficultyLevel().toString());
        questionDTO.setCategory(question.getCategory().getName());
        return questionDTO;
    }

    private List<String> mapOptionsToDto(List<QuestionOption> options) {
        return options.stream().map(QuestionOption::getOption).toList();
    }

    private String findCorrectAnswer(List<QuestionOption> options) {
        return options.stream()
                .filter(QuestionOption::isCorrect)
                .findFirst()
                .map(QuestionOption::getOption)
                .orElse(null);
    }

    public Question toEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setCategory(categoryRepository.findByName(questionDTO.getCategory()).orElse(null));
        question.setDifficultyLevel(DifficultyLevel.valueOf(questionDTO.getDifficulty().toUpperCase()));
        question.setOptions(mapOptionsToEntity(questionDTO.getOptions(), questionDTO.getCorrectAnswer(), question));
        return question;
    }

    private List<QuestionOption> mapOptionsToEntity(List<String> options, String correctAnswer, Question question) {
        return options.stream()
                .map(option -> createQuestionOption(option, correctAnswer, question))
                .collect(Collectors.toList());
    }

    private QuestionOption createQuestionOption(String option, String correctAnswer, Question question) {
        QuestionOption questionOption = new QuestionOption();
        questionOption.setOption(option);
        questionOption.setCorrect(option.equalsIgnoreCase(correctAnswer));
        questionOption.setQuestion(question);
        return questionOption;
    }

    public List<QuestionDTO> toDto(List<Question> questions) {
        return questions.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Question> toEntities(List<QuestionDTO> questionDTOs) {
        return questionDTOs.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public QuizDTO toQuizDTO(List<Question> questions) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuestions(toDto(questions));
        quizDTO.setDifficulty(questions.get(0).getDifficultyLevel().toString());
        return quizDTO;
    }
}
