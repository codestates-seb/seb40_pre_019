//package be.stackoverflow.question;
//
//import be.stackoverflow.answer.controller.AnswerController;
//import be.stackoverflow.answer.dto.AnswerDto;
//import be.stackoverflow.answer.entity.Answer;
//import be.stackoverflow.question.controller.questionController;
//import be.stackoverflow.question.dto.questionDto;
//import be.stackoverflow.question.entity.Question;
//import be.stackoverflow.question.mapper.questionMapper;
//import be.stackoverflow.question.service.questionService;
//import be.stackoverflow.security.JwtTokenizer;
//import be.stackoverflow.user.entity.User;
//import be.stackoverflow.user.service.UserService;
//import com.google.gson.Gson;
//import com.jayway.jsonpath.JsonPath;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(questionController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureMockMvc(addFilters = false)
//@AutoConfigureRestDocs
//public class QuestionControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @MockBean
//    private questionService questionService;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private questionMapper mapper;
//
//    @MockBean
//    private JwtTokenizer jwtTokenizer;
//
//
//    /* ?????? : GET v1/questions/{questionId}
//     * Feature : ???????????? ?????? ?????? ????????? ?????? ?????? ??? ???????????? ?????? ?????? ???????????? ????????????.
//     * Scenario : ????????? ?????? ???????????? ??????????????? ???????????? ?????? ????????? ?????? ???????????? ?????? ??????.
//     * Given : questionDto.questionDetailResponse, questionDto.QuestionAnswerResponseDto, ??????????????? ??????????????? ??????, question ??????, user??????
//     * When : ????????? ?????? ???????????? ????????????
//     * Then : ???????????? ???????????? ????????? ?????? ???????????? ????????????.
//     */
//    @Test
//    void getQuestionTest() throws Exception {
//        // given
//        long questionId = 1L;
//
//        List<questionDto.QuestionAnswerResponseDto> answers = new ArrayList<>();
//        answers.add(new questionDto.QuestionAnswerResponseDto(1L,"??????1",0,
//                LocalDateTime.now(),LocalDateTime.now(),"userName3","userName4",2));
//        answers.add(new questionDto.QuestionAnswerResponseDto(2L,"??????2",0,
//                LocalDateTime.now(),LocalDateTime.now(),"userName5","userName6",2));
//
//        questionDto.questionDetailResponse response = new questionDto.questionDetailResponse(1L,"??????1","??????1 ???????????????????",
//                "tags",0,true,0,
//                LocalDateTime.now(),LocalDateTime.now(),"userName1","userName2",answers);
//
//        //Stubbing by Mockito
//        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
//        given(mapper.questionToDetailResponse(Mockito.any(Question.class))).willReturn(response);
//
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders.get("/v1/questions/{questionId}", questionId)
//                                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.questionId").value(questionId))
//                .andExpect(jsonPath("$.data.questionTitle").value(response.getQuestionTitle()))
//                .andDo(
//                        document("get-question",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                pathParameters(
//                                        Arrays.asList(parameterWithName("questionId").description("?????? ????????? ID"))
//                                ),
//                                responseFields(
//                                        Arrays.asList(
//                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
//                                                fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                                fieldWithPath("data.questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.questionBody").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.tags").type(JsonFieldType.STRING).description("????????? ??????"),
//                                                fieldWithPath("data.questionViewCount").type(JsonFieldType.NUMBER).description("?????? ???"),
//                                                fieldWithPath("data.questionStatus").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
//                                                fieldWithPath("data.questionVote").type(JsonFieldType.NUMBER).description("?????? ????????? ??????"),
//                                                fieldWithPath("data.created_at").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.updated_at").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.create_by_user").type(JsonFieldType.STRING).description("?????????"),
//                                                fieldWithPath("data.updated_by_user").type(JsonFieldType.STRING).description("?????????"),
//                                                fieldWithPath("data.answers").type(JsonFieldType.ARRAY).description("?????? ?????????").optional(),
//                                                fieldWithPath("data.answers[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                                fieldWithPath("data.answers[].answerBody").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.answers[].answerVote").type(JsonFieldType.NUMBER).description("?????? ????????????"),
//                                                fieldWithPath("data.answers[].created_at").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.answers[].updated_at").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                fieldWithPath("data.answers[].create_by_user").type(JsonFieldType.STRING).description("?????????"),
//                                                fieldWithPath("data.answers[].updated_by_user").type(JsonFieldType.STRING).description("?????????"),
//                                                fieldWithPath("data.answers[].answerSize").type(JsonFieldType.NUMBER).description("????????????")
//                                        )
//                                )
//                        ));
//
//    }
//
//    /* ?????? : GET v1/questions
//     * Feature : ?????? ??????????????? ?????? ?????? ????????? ?????? ???????????? ?????????.
//     * Scenario : ????????? page, size??? ????????????????????? ????????? page??? size??? ?????? ?????? ?????? ?????? ????????? ????????????.
//     * Given : questionDto.questionFrontResponse, page, size, ?????? ?????????
//     * When : ????????? page, size??? ????????????,
//     * Then : ?????? ?????? ????????? ?????? ??????????????? ??????.
//     */
//
//    @Test
//    void getQuestionsTest() throws Exception {
//        //given
//        String page = "1";
//        String size = "10";
//
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//        queryParams.add("page", page);
//        queryParams.add("size", size);
//
//        Question question1 = new Question(1L,"??????1","??????1??? ??????????????????",0,true,0);
//        question1.setUser(new User(1L,"username1","useremail1","123456k*",true,Arrays.asList("USER")));
//        Question question2 = new Question(2L,"??????2","??????2??? ??????????????????",0,true,0);
//        question2.setUser(new User(2L,"username2","useremail2","123456k*",true,Arrays.asList("USER")));
//
//        Page<Question> questions = new PageImpl<>(List.of(question1, question2),
//                PageRequest.of(0, 10, Sort.by("questionId").descending()), 2);
//        List<questionDto.questionFrontResponse> responses = new ArrayList<>();
//        responses.add(new questionDto.questionFrontResponse(1L, "??????1", "tags1", 0, true,
//                0, LocalDateTime.now(), LocalDateTime.now(),
//                question1.getUser().getUserName(), question1.getUser().getUserName()));
//        responses.add(new questionDto.questionFrontResponse(2L, "??????2", "tags2", 0, true,
//                0, LocalDateTime.now(), LocalDateTime.now(),
//                question2.getUser().getUserName(), question2.getUser().getUserName()));
//
//        given(questionService.findAllQuestion(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString())).willReturn(questions);
//        given(mapper.questionListResponse(Mockito.anyList())).willReturn(responses);
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                get("/v1/questions")
//                        .params(queryParams)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        MvcResult result =
//                actions
//                        .andExpect(status().isOk())
//                        .andDo(
//                                document(
//                                        "get-questions",
//                                        preprocessRequest(prettyPrint()),
//                                        preprocessResponse(prettyPrint()),
//                                        requestParameters(
//                                                List.of(
//                                                        parameterWithName("page").description("page ??????"),
//                                                        parameterWithName("size").description("page ??????")
//                                                )
//                                        ),
//                                        responseFields(
//                                                Arrays.asList(
//                                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????").optional(),
//                                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                                        fieldWithPath("data[].questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                        fieldWithPath("data[].tags").type(JsonFieldType.STRING).description("????????? ??????"),
//                                                        fieldWithPath("data[].questionViewCount").type(JsonFieldType.NUMBER).description("?????? ???"),
//                                                        fieldWithPath("data[].questionStatus").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
//                                                        fieldWithPath("data[].questionVote").type(JsonFieldType.NUMBER).description("?????? ????????? ??????"),
//                                                        fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                        fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("?????? ??????"),
//                                                        fieldWithPath("data[].create_by_user").type(JsonFieldType.STRING).description("?????????"),
//                                                        fieldWithPath("data[].updated_by_user").type(JsonFieldType.STRING).description("?????????"),
//                                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("????????? ??????").optional(),
//                                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("????????? ??????").optional(),
//                                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ?????????").optional(),
//                                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("?????? ??? ???").optional(),
//                                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("?????? ????????? ???").optional()
//                                                )
//                                        )
//                                )
//                        ).andReturn();
//        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
//        assertThat(list.size()).isEqualTo(2);
//    }
//
//    /*
//     * ?????? : DELETE v1/question/{questionId}
//     * Feature : ???????????? ????????? ????????? ????????? ????????? ???????????? ??????
//     * Scenario : ????????? questionId??? ????????? ?????? ????????? ???????????????????????? ?????????.
//     * Given : questionId
//     * When : ????????? questionId??? ????????????,
//     * Then : questionId??? ????????? ?????? ??????.
//     */
//    @Test
//    void deleteQuestionTest() throws Exception{
//        //given
//        long questionId = 1L;
//        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                RestDocumentationRequestBuilders
//                        .delete("/v1/questions/{questionId}", questionId));
//
//        //then
//        actions.andExpect(status().isNoContent())
//                .andDo(
//                        document(
//                                "delete-question",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                pathParameters(
//                                        Arrays.asList(parameterWithName("questionId").description("?????? ????????? ID"))
//                                )
//                        )
//                );
//    }
//}
