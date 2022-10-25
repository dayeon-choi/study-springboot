package com.dayeon.study.springboot.service.posts;

import com.dayeon.study.springboot.domain.posts.Posts;
import com.dayeon.study.springboot.domain.posts.PostsRepository;
import com.dayeon.study.springboot.web.dto.PostsResponseDto;
import com.dayeon.study.springboot.web.dto.PostsSaveRequestDto;
import com.dayeon.study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    // final이 선언된 모든 필드를 인자값으로 생성하는 생성자 대신 생성
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        PostsResponseDto responseDto = new PostsResponseDto(posts);

        return responseDto;
    }
}
