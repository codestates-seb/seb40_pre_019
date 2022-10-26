package be.stackoverflow.tags.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class TagsDto {
    @Getter
    public static class Post {
        @NotBlank
        private String tagName;

        @NotBlank
        private String tagDetail;
    }

    @Getter
    @Builder
    public static class Response {
        private Long tagId;
        private String tagName;
        private String tagDetail;
    }
}
