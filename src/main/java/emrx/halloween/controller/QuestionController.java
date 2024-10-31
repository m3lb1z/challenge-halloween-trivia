package emrx.halloween.controller;

import emrx.halloween.dto.question.QuestionDTO;
import emrx.halloween.mapper.QuestionMapper;
import emrx.halloween.model.DifficultyLevel;
import emrx.halloween.model.Question;
import emrx.halloween.service.QuestionService;
import emrx.halloween.service.QuizService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping
    public ResponseEntity<Page<QuestionDTO>> getAllQuestions(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sort,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Question> questions = questionService.findAll(pageable);
        Page<QuestionDTO> questionDTOs = questions.map(questionMapper::toDto);
        return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        Question question = questionService.findById(id);
        if (question != null) {
            QuestionDTO questionDTO = questionMapper.toDto(question);
            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        Question createdQuestion = questionService.create(questionDTO);
        QuestionDTO createdQuestionDTO = questionMapper.toDto(createdQuestion);
        return new ResponseEntity<>(createdQuestionDTO, HttpStatus.CREATED);
    }

    @PostMapping("batch")
    public ResponseEntity<Boolean> postMethodName(@RequestBody List<QuestionDTO> questions) {
        boolean request = questionService.saveAll(questions);  
        return new ResponseEntity<>(request, HttpStatus.NO_CONTENT);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionDTO questionDTO) {
        Question updatedQuestion = questionService.update(id, questionDTO);
        if (updatedQuestion != null) {
            QuestionDTO updatedQuestionDTO = questionMapper.toDto(updatedQuestion);
            return new ResponseEntity<>(updatedQuestionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    

    @GetMapping("/quiz/categories")
    public ResponseEntity<List<String>> findAllCategoriesNames() {
        List<String> categories = quizService.findAllCategoriesNames();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/quiz/difficulty-levels")
    public ResponseEntity<List<String>> findAllDifficultyLevelsNames() {
        List<String> difficultyLevels = quizService.findAllDifficultyLevelsNames();
        return new ResponseEntity<>(difficultyLevels, HttpStatus.OK);
    }

    @GetMapping("/quiz/random")
    public ResponseEntity<List<QuestionDTO>> findRandomQuestionsByDifficultyLevel(
            @RequestParam(defaultValue = "EASY") String difficulty
    ) {
        List<Question> questions = quizService.findRandomQuestionsByDifficultyLevel(DifficultyLevel.valueOf(difficulty.toUpperCase()));
        return new ResponseEntity<>(questionMapper.toDto(questions), HttpStatus.OK);
    }
}
