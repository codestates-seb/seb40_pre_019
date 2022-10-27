package be.stackoverflow.answer.service;

import be.stackoverflow.answer.dto.AnswerAddRequestDTO;
import be.stackoverflow.answer.dto.AnswerDeleteRequestDTO;
import be.stackoverflow.answer.dto.AnswerEditRequestDTO;
import be.stackoverflow.answer.dto.AnswerGetResponseDTO;
import be.stackoverflow.answer.entity.Answer;
import be.stackoverflow.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    @Override
    @Transactional(readOnly = false)
    public Long postAnswer(AnswerAddRequestDTO dto) {
        Answer answer = new Answer();

        answer.setAnswerBody(dto.getAnswerBody());
        answer.setQuestionId(dto.getQuestionId());

        answerRepository.save(answer);
        // answer.setUserId(dto.getUserId());
        return 1L;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerGetResponseDTO> getAnswer(Long answerId) {
        List<Answer> list = answerRepository.findByAnswerId(answerId);

        return this.mappingAnswerGetResponse(list);
    }

    private List<AnswerGetResponseDTO> mappingAnswerGetResponse(List<Answer> list) {
        List<AnswerGetResponseDTO> dtoList = new ArrayList<>();

        for (Answer answer : list) {
            AnswerGetResponseDTO dto = new AnswerGetResponseDTO();

            dto.setAnswerId(answer.getAnswerId());
            dto.setQuestionId(answer.getQuestionId());
            dto.setAnswerBody(answer.getAnswerBody());
            dto.setAnswerVote(answer.getAnswerVote());
            dto.setAnswerStatus(answer.getAnswerStatus());
            dto.setCreatedAt(answer.getCreatedAt());
            dto.setModifiedAt(answer.getModifiedAt());

            dtoList.add(dto);
        }

        return dtoList;
    }



    @Override
    @Transactional(readOnly = false)
    public Long patchAnswer(AnswerEditRequestDTO dto) {
        Answer answer = answerRepository.findByAnswerIdAndQuestionId(dto.getAnswerId(), dto.getQuestionId());
        // db에서 가지고 옴
        answer.setAnswerBody(dto.getEditAnswerBody());
        // 수정이 더 필요한게 있으면 여기에 추가하면 됨

        answerRepository.save(answer);

        return 1L;
    }

    @Override
    public Long deleteAnswer(AnswerDeleteRequestDTO dto) {
        Answer answer = answerRepository.findByAnswerIdAndQuestionId(dto.getAnswerId(), dto.getQuestionId());

        answerRepository.delete(answer);

        return 1L;
    }
}
