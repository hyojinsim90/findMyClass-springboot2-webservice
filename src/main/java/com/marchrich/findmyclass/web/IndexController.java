package com.marchrich.findmyclass.web;

import com.marchrich.findmyclass.config.auth.LoginUser;
import com.marchrich.findmyclass.config.auth.dto.SessionUser;
import com.marchrich.findmyclass.service.posts.PostsService;
import com.marchrich.findmyclass.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){ // model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
                                    // @LoginUser : 기존에 httpSession.getAttribute("user")로가져오던 세션의 리스트가 개션됨.
        model.addAttribute("posts", postsService.findAllDesc()); // postsService.findAllDesc()가져온 결과를 posts로 index.mustache에 전달

//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
            // SessionUser에 이미 인증된 사용자 정보가 저장되있으므로 로그인 성공 시 httpSession.getAttribute로 정보를 가져올 수 있다
            // -> 처음 세션에서 가져오는걸 구현할 때, @LoginUser어노테이션으로 가져오는걸 만든 이후에는 사용할 필요 없음

        if (user != null) { // 세션에 저장된 값이 있으면 넘겨줄 userName을 만듬
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
