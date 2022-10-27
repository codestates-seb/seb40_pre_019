package be.stackoverflow.tags.repository;

import be.stackoverflow.tags.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface TagsRepository extends JpaRepository<Tags,Long> {

}
