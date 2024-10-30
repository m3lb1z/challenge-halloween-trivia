package emrx.halloween.controller;

import emrx.halloween.dto.QuestionDTO;
import emrx.halloween.mapper.QuestionMapper;
import emrx.halloween.model.DifficultyLevel;
import emrx.halloween.model.Question;
import emrx.halloween.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/categories")
    public ResponseEntity<List<String>> findAllCategoriesNames() {
        List<String> categories = quizService.findAllCategoriesNames();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/difficulty-levels")
    public ResponseEntity<List<String>> findAllDifficultyLevelsNames() {
        List<String> difficultyLevels = quizService.findAllDifficultyLevelsNames();
        return new ResponseEntity<>(difficultyLevels, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<List<QuestionDTO>> findRandomQuestionsByDifficultyLevel(
            @RequestParam(defaultValue = "EASY") String difficulty
    ) {
        List<Question> questions = quizService.findRandomQuestionsByDifficultyLevel(DifficultyLevel.valueOf(difficulty.toUpperCase()));
        return new ResponseEntity<>(questionMapper.toDto(questions), HttpStatus.OK);
    }
}
