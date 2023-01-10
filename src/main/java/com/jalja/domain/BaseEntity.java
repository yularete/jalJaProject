package com.jalja.domain;

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

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public class BaseEntity {

    @CreatedDate //엔티티가 생성되어 저장될 때 시간을 자동으로 저장
    @Column(name ="regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 엔티티 값을 변경할 때 시간을 자동으로 저장
    @Column(name ="modDate")
    private LocalDateTime modDate;
}
