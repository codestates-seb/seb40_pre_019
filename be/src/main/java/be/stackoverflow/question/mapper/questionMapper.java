package be.stackoverflow.question.mapper;

import be.stackoverflow.question.dto.questionDto;
import be.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface questionMapper {

    Question questionPostToQuestion(questionDto.questionPost postDataToEntity); //postDto 데이터를 Question entity화
    Question questionPatchToQuestion(questionDto.questionPatch patchToEntity); //patchDto 데이터를 Question entity화
    questionDto.questionFrontResponse questionToFrontResponse(Question question);//게시판 처음에 쏴줄 데이터들 변환

    questionDto.questionDetailResponse questionToDeatilResponse(Question question); //상세 게시글에 쏴줄 데이터로 변환

    List<questionDto.questionFrontResponse> questionListResponse(List<Question> questionList);

}
