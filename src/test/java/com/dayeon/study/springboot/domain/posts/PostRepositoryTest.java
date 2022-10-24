package com.dayeon.study.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// @WebMvcTest(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRepositoryTest {
    @Autowired  // 스프링이 관리하는 빈 주입
    PostsRepository postsRepository;

    @AfterEach  // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정: 배포 전 전체 테스트 수행 시 테스트 간 데이터 침범을 막기 위해 사용: 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2가 데이터에 그대로 남아있어 다음 테스트 실행 시 테스트 실패 가능성
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(   // 테이블 posts에 insert 또는 update 쿼리를 실행
            Posts.builder()
                .title(title)
                .content(content)
                .author("dayeon2399@naver.com")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();  // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
