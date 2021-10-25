package com.koscom.demo.web;

import com.koscom.demo.service.PostsService;
import com.koscom.demo.web.dto.posts.PostsUpdateRequestDto;
import com.koscom.demo.web.dto.posts.PostssaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {
    private  final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostssaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostsUpdateRequestDto dto){
        return postsService.update(id, dto);
    }
}
