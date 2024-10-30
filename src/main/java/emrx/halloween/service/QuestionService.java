package emrx.halloween.service;

import emrx.halloween.dto.question.QuestionDTO;
import emrx.halloween.dto.question.validation.QuestionValidation;
import emrx.halloween.mapper.QuestionMapper;
import emrx.halloween.model.Category;
import emrx.halloween.model.DifficultyLevel;
import emrx.halloween.model.Question;
import emrx.halloween.model.QuestionOption;
import emrx.halloween.repository.CategoryRepository;
import emrx.halloween.repository.QuestionRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private List<QuestionValidation> questionValidations;

    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    public Question findById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.orElse(null);
    }

    
    @Transactional
    public Question create(QuestionDTO question) {
        questionValidations.forEach(validation -> validation.validate(question));
        Question newQuestion = questionMapper.toEntity(question);
        return questionRepository.save(newQuestion);
    }

    public boolean saveAll(List<Question> questions) {
        questionRepository.saveAll(questions);
        return true;
    }

    @Transactional
    public Question update(Long id, QuestionDTO question) {
        questionValidations.forEach(validation -> validation.validate(question));
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setQuestion(question.getQuestion());
            Category category = categoryRepository.findByName(question.getCategory()).orElse(null);
            existingQuestion.setCategory(category);
            existingQuestion.setDifficultyLevel(DifficultyLevel.valueOf(question.getDifficulty().toUpperCase()));
            List<QuestionOption> options = question.getOptions().stream()
                    .map(option -> {
                        QuestionOption questionOption = new QuestionOption();
                        questionOption.setOption(option);
                        questionOption.setCorrect(option.equals(question.getCorrectAnswer()));
                        questionOption.setQuestion(existingQuestion);
                        return questionOption;
                    })
                    .toList();
            existingQuestion.setOptions(options);

            return questionRepository.save(existingQuestion);
        }

        return null;
    }

    @Transactional
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

}
