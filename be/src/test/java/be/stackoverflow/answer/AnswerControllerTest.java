/*
package be.stackoverflow.answer;

import be.stackoverflow.answer.controller.AnswerController;
import be.stackoverflow.answer.dto.AnswerDto;
import be.stackoverflow.answer.entity.Answer;
import be.stackoverflow.answer.mapper.AnswerMapper;
import be.stackoverflow.answer.service.AnswerService;
import be.stackoverflow.question.entity.Question;
import be.stackoverflow.question.service.questionService;
import be.stackoverflow.security.JwtTokenizer;
import be.stackoverflow.user.dto.UserDto;
import be.stackoverflow.user.entity.User;
import be.stackoverflow.user.service.UserService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private questionService questionService;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenizer jwtTokenizer;

    @MockBean
    private AnswerMapper mapper;

//     * ?????? : GET v1/answer
//     * Feature : ???????????? user ????????? ????????? ????????? ????????? ???????????? ???????????? ????????????.
//     * Scenario : ????????? page, size??? ????????????????????? ????????? page??? size??? ?????? ?????? ?????? ?????? ????????? ????????????.
//     * Given : AnswerDto.Response, page, size, ?????? ?????????
//     * Mockito??? Answer??????, getAnswer, AnswerResponseDto stubbing
//     * When : ????????? page, size??? ????????????,
//     * Then : ?????? ?????? ????????? ?????? ??????????????? ??????.



    @Test
    void getAnswersTest() throws Exception {
        //given
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);


        Answer answer1 = new Answer(1L,"??????1",0);

        Answer answer2 = new Answer(2L,"??????2",0);

        Page<Answer> answers = new PageImpl<>(List.of(answer1, answer2),
                PageRequest.of(0, 10, Sort.by("answerId").descending()), 2);
        List<AnswerDto.Response> responses = new ArrayList<>();
        responses.add(new AnswerDto.Response(1L,"??????1", 0, LocalDateTime.now(), LocalDateTime.now(),"userName1","userName1"));
        responses.add(new AnswerDto.Response(2L,"??????2", 0, LocalDateTime.now(), LocalDateTime.now(),"userName2","userName2"));

        given(answerService.findAllAnswer(Mockito.anyInt(),Mockito.anyInt())).willReturn(answers);
        given(mapper.answersToAnswerReponses(Mockito.anyList())).willReturn(responses);

        //when
        ResultActions actions = mockMvc.perform(
                get("/v1/answer")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andDo(
                                document(
                                        "get-answers",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        requestParameters(
                                                List.of(
                                                parameterWithName("page").description("page ??????"),
                                                parameterWithName("size").description("page ??????")
                                                )
                                        ),
                                        responseFields(
                                                Arrays.asList(
                                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????").optional(),
                                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                        fieldWithPath("data[].answerBody").type(JsonFieldType.STRING).description("?????? ??????"),
                                                        fieldWithPath("data[].answerVote").type(JsonFieldType.NUMBER).description("?????? ????????????"),
                                                        fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("?????? ??????"),
                                                        fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("?????? ??????"),
                                                        fieldWithPath("data[].create_by_user").type(JsonFieldType.STRING).description("?????????"),
                                                        fieldWithPath("data[].updated_by_user").type(JsonFieldType.STRING).description("?????????"),
                                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("????????? ??????").optional(),
                                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("????????? ??????").optional(),
                                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ?????????").optional(),
                                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("?????? ??? ???").optional(),
                                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("?????? ????????? ???").optional()
                                                )
                                        )
                                )
                        ).andReturn();
        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        assertThat(list.size()).isEqualTo(2);
    }
//     * ?????? : DELETE v1/answer/{answerId}
//     * Feature : ???????????? ????????? ????????? ????????? ????????? ???????????? ??????
//     * Scenario : ????????? answerId ????????? ?????? ????????? ???????????????????????? ?????????.
//     * Given : AnswerDto.Response, answerId
//     * When : ????????? answerId??? ????????????,
//     * Then : answerId??? ????????? ?????? ??????.


    @Test
    void deleteAnswerTest() throws Exception{
        //given
        long answerId = 1L;
        doNothing().when(answerService).deleteAnswer(Mockito.anyLong());

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/v1/answer/{answerId}", answerId));

        //then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-answer",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        Arrays.asList(parameterWithName("answerId").description("?????? ????????? ID"))
                                )
                        )
                );
    }
//     * ?????? : DELETE v1/answer/
//     * Feature : ???????????? ?????? ????????? ???????????? ???????????? ????????? ?????? ?????????.
//     * Scenario : ???????????? ?????? ????????? ???????????? ???????????? ????????? ?????? ?????????.
//     * Given : AnswerDto.Response
//     * When : ?????? ????????? ?????? ??????
//     * Then : ????????? ?????? ??????.


    @Test
    void deleteAnswersTest() throws Exception{
        //given
        doNothing().when(answerService).deleteAllAnswer();

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/v1/answer"));

        //then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-answers",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())

                        )
                );
    }
}
*/
