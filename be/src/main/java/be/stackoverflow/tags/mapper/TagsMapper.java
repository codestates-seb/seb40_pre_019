package be.stackoverflow.tags.mapper;

import be.stackoverflow.tags.dto.TagsDto;
import be.stackoverflow.tags.entity.Tags;
import be.stackoverflow.user.dto.UserDto;
import be.stackoverflow.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagsMapper {

    Tags tagsPostDtoToTags(TagsDto.Post request);

    TagsDto.Response tagsToTagsResponseDto(Tags tags);

    List<TagsDto.Response> tagsToTagsReponses(List<Tags> tags);
}
