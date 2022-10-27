package be.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerAddRequestDTO {
    // 1. 답글 본문
    // 2. 사용자 ID
    // 3. 질문글 ID
    // 4. 답글 번호 - 화면에서 받지 않고 내부적으로 순차적인 번호를 부여]

    private String AnswerBody;
    private Long questionId;
    private Long userId;


}