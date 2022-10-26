package com.dayeon.study.springboot.web;

import com.dayeon.study.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        /*

         * mustache starter 덕분에 컨트롤러에서 문자열 반환 시 앞의 경로와 뒤의 파일 확장자 자동 지정
         * src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리

         */
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
