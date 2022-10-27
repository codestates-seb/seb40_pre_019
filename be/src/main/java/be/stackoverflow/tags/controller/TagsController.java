package be.stackoverflow.tags.controller;

import be.stackoverflow.tags.dto.TagsDto;
import be.stackoverflow.tags.entity.Tags;
import be.stackoverflow.tags.mapper.TagsMapper;
import be.stackoverflow.tags.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tags")
public class TagsController {

    private final TagsService tagsService;
    private final TagsMapper tagsMapper;


    /**
     * @PostConstruct로 미리 넣어둘 예정
     */

    @PostMapping
    public ResponseEntity postTag(@RequestBody TagsDto.Post request) {

        Tags tags = tagsMapper.tagsPostDtoToTags(request);
        tagsService.createTag(tags);

        return new ResponseEntity<>(tagsMapper.tagsToTagsResponseDto(tags), HttpStatus.CREATED);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity getTag(@PathVariable("tagId") long tagId) {
        Tags tags = tagsService.findTag(tagId);

        return new ResponseEntity<>(tagsMapper.tagsToTagsResponseDto(tags),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getTags() {
        List<Tags> tags = tagsService.findTags();

        return new ResponseEntity<>(tagsMapper.tagsToTagsReponses(tags),HttpStatus.OK);
    }

}
