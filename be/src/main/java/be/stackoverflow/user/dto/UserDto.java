package be.stackoverflow.user.dto;

import be.stackoverflow.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


public class UserDto {

    @Getter
    public static class Post {
        @NotBlank(message = "이름 입력란을 채워주세요.")
        @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
        private String userName;


        @NotBlank(message = "이메일 입력란을 채워주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String userEmail;

        @NotBlank(message = "비밀번호 입력란을 채워주세요.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;


    }

    @Getter
    public static class Patch {

        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String userEmail;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;
    }

    @Builder
    @Getter
    public static class Response {
        private long userId;
        private String userName;
        private String userEmail;
        private String password;
        private boolean userStatus;
        private User.Role role;

        /**
         * 유현 : Audit 기능 추가
         * 코드리뷰 후 적용 예정 부분
         */
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
        private String create_by;
        private String updated_by;
    }
}
