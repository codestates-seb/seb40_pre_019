package be.stackoverflow.answer.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerGetResponseDTO {

    private Long answerId;
    private Long questionId;
    private String answerBody;
    private int answerVote;
    private Boolean answerStatus;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
