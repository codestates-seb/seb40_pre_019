package be.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    /*
    * 필요시 추가로 구현하시면 됩니다 에러이름(code, message)
     */

    USER_NOT_FOUND(404, "User not exists"),
    QUESTION_NOT_FOUND(404, "Question not exists"),
    ANSWER_NOT_FOUND(404, "Answer not exists");
    
    /* 위에다가 추가 하세요 */
    
    @Getter
    private int code;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
