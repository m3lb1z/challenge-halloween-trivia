package emrx.halloween.dto.question.validation;

import org.springframework.stereotype.Component;

import emrx.halloween.dto.question.QuestionDTO;
import emrx.halloween.repository.CategoryRepository;
import jakarta.validation.ValidationException;

@Component
public class CategoryExistsInDatabase implements QuestionValidation {

  private final CategoryRepository categoryRepository;

  public CategoryExistsInDatabase(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void validate(QuestionDTO questionDTO) {
    if (!categoryRepository.existsByName(questionDTO.getCategory())) {
      throw new ValidationException("Categoría no válida");
    }
  }
  
}
