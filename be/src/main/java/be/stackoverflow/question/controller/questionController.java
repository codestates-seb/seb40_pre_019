package be.stackoverflow.question.controller;

import be.stackoverflow.dto.MultiResponseDto;
import be.stackoverflow.dto.SingleResponseDto;
import be.stackoverflow.question.dto.questionDto;
import be.stackoverflow.question.entity.Question;
import be.stackoverflow.question.mapper.questionMapper;
import be.stackoverflow.question.service.questionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@Slf4j
@RequestMapping("/v1/questions")
public class questionController {

    //DI 주입
    private final questionService questionService;
    private final questionMapper mapper;

    //R: 모든 질문페이지 요청하기
    @GetMapping
    public ResponseEntity getAllQuestions(@Positive @RequestParam int page,
                                          @Positive @RequestParam int size) {

        Page<Question> pageInfomation = questionService.findAllQuestion(page-1, size);
        List<Question> allQuestions = pageInfomation.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionListResponse(allQuestions),pageInfomation) , HttpStatus.CREATED);
    }




    //create (게시글 생성)
    @PostMapping("/createQuestion")
    public ResponseEntity postQuestion(@Validated @RequestBody questionDto.questionPost postdata) {
        log.info("postdata ={}", postdata);
        Question question = mapper.questionPostToQuestion(postdata);
        Question savedQuestion = questionService.createQuestion(question);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToFrontResponse(savedQuestion)), HttpStatus.CREATED);
    }


    //Read (조회)
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id")@Positive long questionId) throws Exception {

        Question FoundQuestion = questionService.findQuestion(questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToDeatilResponse(FoundQuestion)), HttpStatus.CREATED);
    }



    //UPDATE (수정)
    @PatchMapping("/{question-id}")
    public ResponseEntity postQuestion(@PathVariable("question-id")@Positive long questionId,
                                        @Validated @RequestBody questionDto.questionPatch patchData) throws Exception {
        patchData.setQuestionId(questionId);
        Question ModifiedQuestion = questionService.updateQuestion(mapper.questionPatchToQuestion(patchData));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToDeatilResponse(ModifiedQuestion)), HttpStatus.CREATED);
    }


    //Delete(삭제)
    @DeleteMapping("/{question-id}")
    public ResponseEntity postQuestion(@PathVariable("question-id")@Positive long questionId) {
        // Stauts를 false로 바꾸고 자료는 남길지?
        questionService.deleteQuestion(questionId);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
