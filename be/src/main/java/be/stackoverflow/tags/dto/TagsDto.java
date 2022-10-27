package be.stackoverflow.tags.dto;

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
    public static class Response {
        private Long tagId;
        private String tagName;
        private String tagDetail;
    }
}
