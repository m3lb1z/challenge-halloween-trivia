package emrx.halloween.service;

import emrx.halloween.infra.advices.ValidationIntegrity;
import emrx.halloween.model.Category;
import emrx.halloween.model.DifficultyLevel;
import emrx.halloween.model.Question;
import emrx.halloween.repository.CategoryRepository;
import emrx.halloween.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<String> findAllCategoriesNames() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

    public List<String> findAllDifficultyLevelsNames() {
        return List.of(DifficultyLevel.values()).stream()
                .map(DifficultyLevel::name)
                .collect(Collectors.toList());
    }

    public List<Question> findRandomQuestionsByDifficultyLevel(DifficultyLevel difficultyLevel) {
        if(questionRepository.countByDifficultyLevel(difficultyLevel) < 5) {
            throw new ValidationIntegrity("No hay suficientes preguntas para el nivel de dificultad indicado");
        }
        return questionRepository.findRandomQuestionsByDifficultyLevel(difficultyLevel.toString());
    }

}
