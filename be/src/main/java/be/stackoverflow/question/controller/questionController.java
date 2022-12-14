package be.stackoverflow.question.controller;

import be.stackoverflow.dto.MultiResponseDto;
import be.stackoverflow.dto.PageInfo;
import be.stackoverflow.dto.SingleResponseDto;
import be.stackoverflow.question.dto.questionDto;
import be.stackoverflow.question.entity.Question;
import be.stackoverflow.question.mapper.questionMapper;
import be.stackoverflow.question.service.questionService;
import be.stackoverflow.security.JwtTokenizer;
import be.stackoverflow.user.entity.User;
import be.stackoverflow.user.service.UserService;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@Slf4j
@CrossOrigin(origins = "http://pre-19.s3-website.ap-northeast-2.amazonaws.com" , exposedHeaders = {"Authorization","Refresh"} )
@RequestMapping("/v1/questions")
public class questionController {

    //DI 주입
    private final questionService questionService;
    private final UserService userService;
    private final questionMapper mapper;
    private final JwtTokenizer jwtTokenizer;




    //R: 모든 질문페이지 요청하기
    @GetMapping
    public ResponseEntity getAllQuestions(@Positive @RequestParam int page,
                                          @Positive @RequestParam int size,
                                          @RequestParam(defaultValue = "questionId") String sort) {

        Page<Question> pageInformation = questionService.findAllQuestion(page-1, size, sort);
        List<Question> allQuestions = pageInformation.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionListResponse(allQuestions),pageInformation) , HttpStatus.OK);
    }

    /**
     * 폼 형태로 검색바에 입력된 내용을 쿼리 파라미터에 v1/questions?title="검색어"로 받아서 검색된 질문들을 반환한다.
     * 페이지네이션도 적용
     */
    @GetMapping("/search")
    public ResponseEntity getSearchQuestions(@RequestParam("title") String title,
                                             @Positive @RequestParam int page,
                                             @Positive @RequestParam int size) {
        Page<Question> pageInformation = questionService.searchQuestion(title,page-1,size);
        List<Question> allQuestions = pageInformation.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionListResponse(allQuestions),pageInformation), HttpStatus.OK);
    }

    /*
     * create (게시글 생성) 로그인정보로 부터 아이디를 받아온 뒤에 그 아이디를 기반으로 USER 정보를 가져오고
     *  USER 정보로 Question을 저장함.
     */
    @PostMapping("/createQuestion")
    public ResponseEntity postQuestion(@Valid @RequestBody questionDto.questionPost postdata, HttpServletRequest request) {
        String emailWithToken = jwtTokenizer.getEmailWithToken(request);
        User user = userService.findIdByEmail(emailWithToken);

        Question question = mapper.questionPostToQuestion(postdata);
        Question savedQuestion = questionService.createQuestion(question,user);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToFrontResponse(savedQuestion)), HttpStatus.CREATED);
    }


    //Read (조회)
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id")@Positive long questionId) throws Exception {

        Question FoundQuestion = questionService.increaseViewCount(questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToDetailResponse(FoundQuestion)), HttpStatus.OK);
    }

    //UPDATE (수정)
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id")@Positive long questionId,
                                        @Validated @RequestBody questionDto.questionPatch patchData,
                                        HttpServletRequest request) throws Exception {
        String emailWithToken = jwtTokenizer.getEmailWithToken(request);
        User user = userService.findIdByEmail(emailWithToken);

        Question ModifiedQuestion = questionService.updateQuestion(questionId, mapper.questionPatchToQuestion(patchData), user);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToDetailResponse(ModifiedQuestion)), HttpStatus.CREATED);
    }


    //Delete(삭제)
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id")@Positive long questionId,HttpServletRequest request) {
        String emailWithToken = jwtTokenizer.getEmailWithToken(request);
        User user = userService.findIdByEmail(emailWithToken);

        questionService.deleteQuestion(questionId,user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
