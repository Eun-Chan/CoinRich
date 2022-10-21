package com.ddigrang.coinrich.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 테이블로 맵핑X , 자식 Entity에게 매핑정보 상속
@EntityListeners(AuditingEntityListener.class) // JPA에게 해당 Entity는 Auditing 기능 사용
public class TimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime modified_date;
}
