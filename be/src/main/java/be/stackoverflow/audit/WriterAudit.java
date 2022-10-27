package be.stackoverflow.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class WriterAudit extends TimeAudit {
    /**
     * 생성자, 수정자는 일부 엔티티에 적용 되나, TimeAudit가 필요하므로 TimeAudit를 상속받게 설계함
     */
    @CreatedBy
    @Column(updatable = false)
    private String create_by_user;

    @LastModifiedBy
    private String updated_by_user;
}
