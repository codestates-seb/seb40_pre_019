package be.stackoverflow.answer.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerEditRequestDTO {
    // 1. 수정된 답글 본문
    // 2. 사용자 정보
    //  - 사용자 ID
    // 3. 답글 ID
    // 4. 질문글 ID

    private Long questionId;
    private Long answerId;
    private String editAnswerBody;
    private Long userId;


}
