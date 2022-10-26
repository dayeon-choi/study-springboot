package com.dayeon.study.springboot.service.posts;

import com.dayeon.study.springboot.domain.posts.Posts;
import com.dayeon.study.springboot.domain.posts.PostsRepository;
import com.dayeon.study.springboot.web.dto.PostsListResponseDto;
import com.dayeon.study.springboot.web.dto.PostsResponseDto;
import com.dayeon.study.springboot.web.dto.PostsSaveRequestDto;
import com.dayeon.study.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)   // readOnly:  true 시 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도 개선됨
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
