package com.back.global.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

/**
 * 모든 JPA 엔티티가 상속받는 추상 기반 클래스.
 * 공통 필드(ID, 생성일, 수정일)를 정의하고, Spring Data JPA Auditing을 통해 시간 정보를 자동 기록.
 * 이 클래스는 데이터베이스 테이블과 직접 매핑되지 않고, 자식 엔티티의 컬럼으로 포함됨.
 */
// 자식 엔티티에게 매핑 정보를 상속하도록 지정
@MappedSuperclass
// 엔티티의 영속성 이벤트(생성/수정)를 감지하여 시간 필드 자동 업데이트 활성화.
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    // PK(Primary Key) 설정 및 자동 증가(Auto-increment)
    @Id
    // IDENTITY : 데이터베이스에 ID 생성을 위임 (MySQL의 AUTO_INCREMENT)
    @GeneratedValue(strategy = IDENTITY)
    private int id; // 엔티티의 고유 식별자.

    // 엔티티가 데이터베이스에 저장될 때 시간이 자동으로 기록되도록 지정
    @CreatedDate
    private LocalDateTime createDate;

    // 엔티티가 데이터베이스에서 수정될 때 시간이 자동으로 갱신되도록 지정
    @LastModifiedDate
    private LocalDateTime modifyDate;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}