package be.stackoverflow.question.repository;

import be.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface questionRepository extends JpaRepository<Question,Long> {
    //검색 기능 구현시 findBy제목만들어야함
    //삭제된글뺴고 전체 질문글 가져올 기능(page네이션) 쿼리추가 필요할뜻
}
