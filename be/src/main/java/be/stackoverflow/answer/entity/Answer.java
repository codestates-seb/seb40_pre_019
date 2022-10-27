package be.stackoverflow.answer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity  // 이 어노테이션이 엔티티라는걸 알린다
@Table(name = "ANSWERS")  // 엔티티 에 꼭 필요한 테이블명
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false)
    private Long questionId;
    @Column(nullable = false)
    private String answerBody;
    @Column(nullable = false)
    private int answerVote = 0;
    @Column(nullable = false)
    private Boolean answerStatus = true; //삭제시 false로 처리

    @CreatedDate   // 모든 컨트롤러 완성된뒤에 추가적으로 상속받을예쩡
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate   // 모든 컨트롤러 완성된뒤에 추가적으로 상속받을예쩡
    @Column(name = "last_modified_at")
    private LocalDateTime modifiedAt = LocalDateTime.now();

}
