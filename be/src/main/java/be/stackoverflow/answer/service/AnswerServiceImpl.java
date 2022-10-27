package be.stackoverflow.answer.service;

import be.stackoverflow.answer.dto.AnswerAddRequestDTO;
import be.stackoverflow.answer.dto.AnswerDeleteRequestDTO;
import be.stackoverflow.answer.dto.AnswerEditRequestDTO;
import be.stackoverflow.answer.dto.AnswerGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServicelmpl implements AnswerService {



    @Override

    public Long postAnswer(AnswerAddRequestDTO dto) {
        return null;
    }

    @Override
    public List<AnswerGetResponseDTO> getAnswer(Long answerId) {
        return null;
    }

    @Override
    public Long patchAnswer(AnswerEditRequestDTO dto) {
        return null;
    }

    @Override
    public Long deleteAnswer(AnswerDeleteRequestDTO dto) {
        return null;
    }
}
