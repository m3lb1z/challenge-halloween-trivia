package emrx.halloween.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Question question;
    private String option;
    private boolean correct;
}
