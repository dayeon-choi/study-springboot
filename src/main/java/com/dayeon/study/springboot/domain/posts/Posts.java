package com.dayeon.study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 어노테이션 정렬: 주요 어노테이션을 클래스에 가깝게 둔다: 유지보수 용이성↑ (언어 전환 등, 롬복이 더 이상 필요 없을 경우 쉽게 삭제 가능)
@NoArgsConstructor  // 롬복 어노테이션: 기본 생성자 자동 추가, Public Post(){}와 같은 효과
@Getter             // 롬복 어노테이션: 클래스 내 모든 필드의 Getter 메소드 자동 생성
@Entity             // JPA 제공 어노테이션: 테이블과 링크될 클래스임을 나타냄, 기본값으로 카멜클래스 이름을 언어 스코어 네이밍으로 테이블 이름 매칭
public class Posts {
    @Id             // 해당 테이블 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)             // length 옵션: 기본값이 VARCHAR(255), 사이즈 500으로 변경
    private String title;

    @Column(columnDefinition = "Text", nullable = false) // columnDefinition 옵션: 문자열의 타입, Text로 변경
    private String content;

    // @Column 생략 가능: 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨, 기본값 이외의 추가로 변경이 필요한 옵션이 있을 때 사용
    private String author;

    @Builder        // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
