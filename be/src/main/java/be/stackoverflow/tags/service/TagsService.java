package be.stackoverflow.tags.service;

import be.stackoverflow.tags.entity.Tags;
import be.stackoverflow.tags.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagsService {
    private final TagsRepository tagsRepository;

    @PostConstruct
    public void init() {
        Tags tagJava = new Tags("JAVA", "자바는 어려워");
        Tags tagC = new Tags("C", "C는 더 어려워");
        Tags tagSpring = new Tags("SPRING", "스프링은 더더 어려워");

        tagsRepository.save(tagJava);
        tagsRepository.save(tagC);
        tagsRepository.save(tagSpring);
    }

    /**
     * tag 생성용이긴 하나 @PostConstruct를 활용해서 stub 데이터로 채울 예정
     */
    public Tags createTag(Tags tags) {
        return tagsRepository.save(tags);
    }

    public Tags findTag(long tagId) {
        return findVerifiedTag(tagId);
    }

    public List<Tags> findTags() {
        return tagsRepository.findAll();
    }


    public Tags findVerifiedTag(long tagId) {
        Optional<Tags> optionalTags = tagsRepository.findById(tagId);

        Tags findTag = optionalTags.orElseThrow(() -> new RuntimeException("해당 Tag는 존재하지 않습니다."));

        return findTag;
    }
}
