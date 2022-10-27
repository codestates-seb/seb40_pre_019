package be.stackoverflow.answer.repository;

import be.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // jpaRepository 그냥 외워 무조건 필요한놈 <엔티티 클래스명 , PK>
    //검색 기능 구현시 findBy제목만들어야함
    //삭제된글뺴고 전체 질문글 가져올 기능(page네이션) 쿼리추가 필요할뜻
    // 웹에서 findBy 검색해보자
    Answer findByAnswerIdAndQuestionId(Long answerId, Long questionId);
    List<Answer> findByAnswerId(Long answerId);
}
