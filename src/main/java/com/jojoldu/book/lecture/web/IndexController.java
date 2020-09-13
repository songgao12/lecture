package com.jojoldu.book.lecture.web;

import com.jojoldu.book.lecture.service.posts.PostsService;
import com.jojoldu.book.lecture.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model) throws IOException {
        model.addAttribute("posts",postsService.findAllDesc());
        model.addAttribute("userNames",httpSession.getAttribute("name"));
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
    @GetMapping("/posts/login")
    public String postsLogin(){
        return "login-test";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto= postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

}
