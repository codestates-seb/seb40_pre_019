package be.stackoverflow.answer.service;

import be.stackoverflow.answer.dto.AnswerAddRequestDTO;
import be.stackoverflow.answer.dto.AnswerDeleteRequestDTO;
import be.stackoverflow.answer.dto.AnswerEditRequestDTO;
import be.stackoverflow.answer.dto.AnswerGetResponseDTO;

import java.util.List;

public interface AnswerService {
    public Long postAnswer(AnswerAddRequestDTO dto);
    public List<AnswerGetResponseDTO> getAnswer(Long answerId);
    public Long patchAnswer(AnswerEditRequestDTO dto);
    public Long deleteAnswer(AnswerDeleteRequestDTO dto);
}
