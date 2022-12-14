package be.stackoverflow.question.dto;

import be.stackoverflow.answer.dto.AnswerDto;
import be.stackoverflow.answer.entity.Answer;
import be.stackoverflow.user.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

        @Pattern(regexp = "^[@a-zA-Z-+\\s]{0,100}$", message = "태그 기입시 영어로 기입 해주세요. 여러 태그 입력시, 각 태그 앞에 @로 입력하고, 태그와 @사이에 공백만 쓰세요.")
        private String tags;

        public questionPost(String questionTitle, String questionBody) {
            this.questionTitle = questionTitle;
            this.questionBody = questionBody;
        }
    }

    @Getter
    @Builder
    public static class questionPatch {

        @NotBlank(message = "제목을 기입하기 바랍니다.")
        private String questionTitle;
        @NotBlank(message = "내용을 기입하기 바랍니다.")
        private String questionBody;

        @Pattern(regexp = "^[@a-zA-Z-+\\s]{0,100}$", message = "태그 기입시 영어로 기입 해주세요. 여러 태그 입력시, 각 태그 앞에 @로 입력하고, 태그와 @사이에 공백만 쓰세요.")
        private String tags;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionFrontResponse {

        private Long questionId;
        private String questionTitle;

        //post에서 String으로 받은 tags를 List<String>로 변경해서 응답
        private List<String> tags;

        private int questionViewCount;
        private Boolean questionStatus;
        private int questionVote=0;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        // 로그인 기능 추가 후 구현 예정
        private String create_by_user;
        private String updated_by_user;
        // 댓글 수 표시
        private int answerSize;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionDetailResponse {

        private Long questionId;
        private String questionTitle;
        private String questionBody;

        //post에서 String으로 받은 tags를 List<String>로 변경해서 응답
        private List<String> tags;

        private int questionViewCount;
        private Boolean questionStatus;
        private int questionVote=0;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private String create_by_user;
        private String updated_by_user;
        private List<QuestionAnswerResponseDto> answers;
        // 댓글 수 표시
        private int answerSize;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionAnswerResponseDto {
        private Long answerId;
        private String answerBody;
        private int answerVote=0;
        //댓글 작성자 확인
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private String create_by_user;
        private String updated_by_user;
    }



}
