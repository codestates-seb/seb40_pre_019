package be.stackoverflow.question.dto;

import be.stackoverflow.answer.dto.AnswerDto;
import be.stackoverflow.answer.entity.Answer;
import be.stackoverflow.user.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class questionDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class questionPost {

        @NotBlank(message = "제목을 기입하기 바랍니다.")
        private String questionTitle;
        @NotBlank(message = "내용을 기입하기 바랍니다.")
        private String questionBody;

        private String tags; // tag CRUD 기능 완료시 구현 예정

    }

    @Getter
    @Builder
    public static class questionPatch {

        @NotBlank(message = "제목을 기입하기 바랍니다.")
        private String questionTitle;
        @NotBlank(message = "내용을 기입하기 바랍니다.")
        private String questionBody;

        private String tags; // tag CRUD 기능 완료시 구현 예정

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionFrontResponse {

        private Long questionId;
        private String questionTitle;
        private String tags; // tag CRUD 기능 완료시 구현 예정
        private int questionViewCount;
        private Boolean questionStatus;
        private int questionVote;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        // 로그인 기능 추가 후 구현 예정
        private String create_by_user;
        private String updated_by_user;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionDetailResponse {

        private Long questionId;
        private String questionTitle;
        private String questionBody;

        private String tags; // tag CRUD 기능 완료시 구현 예정
        private int questionViewCount;
        private Boolean questionStatus;
        private int questionVote;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private String create_by_user;
        private String updated_by_user;
        private List<QuestionAnswerResponseDto> answers;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionAnswerResponseDto {
        private Long answerId;
        private String answerBody;
        private int answerVote;
        //댓글 작성자 확인
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private String create_by_user;
        private String updated_by_user;
        private long answerSize;
    }



}
