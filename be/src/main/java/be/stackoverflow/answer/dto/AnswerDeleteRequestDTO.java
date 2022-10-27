package be.stackoverflow.answer.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDeleteRequestDTO {
    // 1. 답글 번호
    // 2. 사용자 ID
    // 3. 질문글 ID

    private Long answerId;
    private Long questionId;
    private Long UserId;
}
