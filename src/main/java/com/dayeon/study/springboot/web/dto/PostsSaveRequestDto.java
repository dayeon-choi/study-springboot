package com.dayeon.study.springboot.web.dto;

import com.dayeon.study.springboot.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    /*

    * Entity와 거의 유사한 형태임에도 Dto 클래스 추가
    * Entity 클래스를 Request/Response 클래스로 사용해서는 안됨
    * Entity 클래스를 기준으로 테이블 생성 및 스키마 변경이 이루어지므로, View Layer와 DB Layer의 역할 분리 필요
    * Request/Response 용 Dto는 View를 위한 클래스

     */

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
