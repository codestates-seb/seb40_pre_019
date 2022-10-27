package be.stackoverflow.question.service;

import be.stackoverflow.exception.BusinessLogicException;
import be.stackoverflow.exception.ExceptionCode;
import be.stackoverflow.question.entity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class questionService {

    private final be.stackoverflow.question.repository.questionRepository questionRepository;

    //전체 질문 조회 페이지
    public Page<Question> findAllQuestion(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending())); //최신순으로 나중에 좋아요 정렬까지 필요하면 questionId를 변수받을 예정
    }

    //C: 질문 추가 페이지
    public Question createQuestion(Question question) {

        return questionRepository.save(question);
    }

    //R: 질문 상세 페이지
    public Question findQuestion(long questionId){

        Question found_question = verifyQuestionUsingID(questionId);
        found_question.setQuestionViewCount(found_question.getQuestionViewCount()+1); // 조회할때마다 상승...? 인간당으로 바꿔야하나? 고민필요
        questionRepository.save(found_question);

        return found_question;
    }


    //U: 질문 수정페이지
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    //propagation(번식) 동작도중에 다른 트랜잭션을 호출시 어찌할지(전파옵션), isolation 일관성없는 데이터 허용수준 설정
    public Question updateQuestion(Question question){


        Question questionFromRepository = verifyQuestionUsingID(question.getQuestionId());

        //CustomBeanUtils 써보기 <리팩토링시>
        Optional.ofNullable(question.getQuestionTitle())
                .ifPresent(new_title -> questionFromRepository.setQuestionTitle(new_title));//title
        Optional.ofNullable(question.getQuestionBody())
                .ifPresent(new_body -> questionFromRepository.setQuestionBody(new_body));//body

        log.info("tag 관련 업데이트 필요");//tag 관련 업데이트 필요
        /**
         * 유현 : Audit 기능 추가
         * 코드리뷰이후 audit 채택 되면 아래 내용 삭제 예정
         */
//        questionFromRepository.setModifiedAt(LocalDateTime.now()); //수정시간 현재로 수정

        return questionRepository.save(questionFromRepository);

    }


    //D: 질문 삭제
    public void deleteQuestion(long questionId){
        Question verifiedQuestion = verifyQuestionUsingID(questionId);//삭제하기전에 해당아이디에 데이터가 있는지 확인한다.
        questionRepository.delete(verifiedQuestion);
    }


    //질문이 있는지 없는지 확인
    private Question verifyQuestionUsingID(Long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return question;

    }

}
