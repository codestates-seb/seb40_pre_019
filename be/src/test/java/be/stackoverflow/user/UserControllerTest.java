/*
package be.stackoverflow.user;

import be.stackoverflow.answer.entity.Answer;
import be.stackoverflow.question.entity.Question;
import be.stackoverflow.security.filter.JwtAuthenticationFilter;
import be.stackoverflow.user.controller.UserController;
import be.stackoverflow.user.dto.UserDto;
import be.stackoverflow.user.entity.User;
import be.stackoverflow.user.mapper.UserMapper;
import be.stackoverflow.user.service.UserService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper mapper;

    */
/**
     * ?????? : POST v1/sign
     * Feature : ????????? ????????? ????????? ???????????? UserRepository??? ????????????.
     * Scenario : ???????????? ????????? ????????? ????????? ?????? ?????? ?????? ??? ????????? ????????? ?????????????????? ????????????.
     * Given : UserDto.Post ??????, UserDto.Response ????????? ????????? ???????????? ?????? ??? ??? ?????????
     * Mockito??? User??????, createUser, UserResponseDto stubbing
     * When : UserDto.Post ????????? Json ????????? ????????? ????????? ???????????? ?????? ??????
     * Then : Json?????? ?????? user????????? ?????? ???????????? ???????????? ????????? ???????????? ??????.
     *//*

    @Test
    void postUserTest() throws Exception {
        //given
        Question question = new Question(1L);
        Answer answer = new Answer(1L);
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        questions.add(question);
        answers.add(answer);
        UserDto.Post post = new UserDto.Post("username","useremail@gmail.com","123456k*");
        String content = gson.toJson(post);
        UserDto.Response response = new UserDto.Response(1L,"username", "useremail@gmail.com",
                "123456k*", true, Arrays.asList("USER"),
                LocalDateTime.now(), LocalDateTime.now(),questions,answers
                );

        //Stubbing by Mockito
        given(mapper.userPostToUser(Mockito.any(UserDto.Post.class))).willReturn(new User());
        given(userService.createUser(Mockito.any(User.class))).willReturn(new User());
        given(mapper.userToUserResponse(Mockito.any(User.class))).willReturn(response);



        //when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/v1/sign")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.userName").value(post.getUserName()))
                .andExpect(jsonPath("$.data.userEmail").value(post.getUserEmail()))
                .andExpect(jsonPath("$.data.password").value(post.getPassword()))
                .andDo(document("post-user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("userName").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("userEmail").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("????????????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.userName").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.userEmail").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("data.password").type(JsonFieldType.STRING).description("????????????"),
                                        fieldWithPath("data.userStatus").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                        fieldWithPath("data.roles").type(JsonFieldType.ARRAY).description("????????????"),
                                        fieldWithPath("data.created_at").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.updated_at").type(JsonFieldType.STRING).description("?????? ??????")
                                )
                        )
                ));
    }
    */
/**
     * ?????? : GET v1/{userId}
     * Feature : ????????? ?????????(userId)??? ??????????????? ???????????? ?????? ?????? ????????? ????????????.
     * Scenario : ????????? ?????? ????????? ????????? ??? ?????? ?????? ???????????? ????????? ????????? ?????????????????? ????????????.
     * Given : UserDto.Response ????????? ????????? ???????????? ?????? ??? ??? ?????????
     * Mockito??? User??????, getUser, UserResponseDto stubbing
     * When : ????????? ?????????(userId)??? ????????? ????????? ?????????,
     * Then : Json?????? ?????? user????????? ????????? ????????? ???????????? ??????.
     *//*

    @Test
    void getUserTest() throws Exception {
        Question question = new Question(1L);
        Answer answer = new Answer(1L);
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        questions.add(question);
        answers.add(answer);
        // given
        long userId =1L;
        UserDto.Response response = new UserDto.Response(1L,"userName", "userEmail@gmail.com",
                "123456k*", true, Arrays.asList("USER"),
                LocalDateTime.now(), LocalDateTime.now(),questions,answers);

        //Stubbing by Mockito
        given(userService.findUser(Mockito.anyLong())).willReturn(new User());
        given(mapper.userToUserResponse(Mockito.any(User.class))).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId").value(userId))
                .andExpect(jsonPath("$.data.userName").value(response.getUserName()))
                .andDo(
                        document("get-user",
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        Arrays.asList(parameterWithName("userId").description("?????? ????????? ID"))
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
                                                fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("data.userName").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data.userEmail").type(JsonFieldType.STRING).description("?????????"),
                                                fieldWithPath("data.password").type(JsonFieldType.STRING).description("????????????"),
                                                fieldWithPath("data.userStatus").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                                fieldWithPath("data.roles").type(JsonFieldType.ARRAY).description("????????????"),
                                                fieldWithPath("data.created_at").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("data.updated_at").type(JsonFieldType.STRING).description("?????? ??????")
                                        )
                                )
                        ));
    }
    */
/**
     * ?????? : GET v1/
     * Feature : ????????? ?????? ????????? ????????????.
     * Scenario : ????????? page, size??? ????????????????????? ????????? page??? size??? ?????? ?????? ?????? ?????? ????????? ???????????? ????????????.
     * Given : UserDto.Response, page, size, ?????? ?????????
     * Mockito??? User??????, getUser, UserResponseDto stubbing
     * When : ????????? page, size??? ????????????,
     * Then : ?????? ?????? ????????? ?????? ??????????????? ??????.
     *//*

    @Test
    void getUsersTest() throws Exception {
        //given
        Question question = new Question(1L);
        Answer answer = new Answer(1L);
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        questions.add(question);
        answers.add(answer);
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        User user1 = new User(1L,"userName1","userEamil1@gmail.com","123456k*",true,
                Arrays.asList("USER"));

        User user2 = new User(2L,"userName2","userEamil2@gmail.com","123456k*",true,
                Arrays.asList("USER"));

        Page<User> users = new PageImpl<>(List.of(user1, user2),
                PageRequest.of(0, 10, Sort.by("userId").descending()), 2);
        List<UserDto.Response> responses = new ArrayList<>();
        responses.add(new UserDto.Response(1L,"userName1", "userEamil1@gmail.com", "123456k*",
                true, Arrays.asList("USER"), LocalDateTime.now(), LocalDateTime.now(),questions,answers));

        responses.add(new UserDto.Response(2L,"userName2", "userEamil2@gmail.com", "123456k*",
                true, Arrays.asList("USER"), LocalDateTime.now(), LocalDateTime.now(),questions,answers));

        given(userService.findUserAll(Mockito.anyInt(),Mockito.anyInt())).willReturn(users);
        given(mapper.usersToUserReponses(Mockito.anyList())).willReturn(responses);

        //when
        ResultActions actions = mockMvc.perform(
                get("/v1")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andDo(
                                document(
                                        "get-users",
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
                                                        fieldWithPath("data[].userId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                        fieldWithPath("data[].userName").type(JsonFieldType.STRING).description("?????? ??????"),
                                                        fieldWithPath("data[].userEmail").type(JsonFieldType.STRING).description("?????????"),
                                                        fieldWithPath("data[].password").type(JsonFieldType.STRING).description("????????????"),
                                                        fieldWithPath("data[].userStatus").type(JsonFieldType.BOOLEAN).description("?????? ??????"),
                                                        fieldWithPath("data[].roles").type(JsonFieldType.ARRAY).description("????????????"),
                                                        fieldWithPath("data[].created_at").type(JsonFieldType.STRING).description("?????? ??????"),
                                                        fieldWithPath("data[].updated_at").type(JsonFieldType.STRING).description("?????? ??????"),
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
}
*/
