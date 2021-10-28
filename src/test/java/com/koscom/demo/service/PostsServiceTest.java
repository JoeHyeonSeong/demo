package com.koscom.demo.service;

import com.koscom.demo.domain.posts.Posts;
import com.koscom.demo.domain.posts.PostsRepository;
import com.koscom.demo.web.dto.posts.PostsUpdateRequestDto;
import com.koscom.demo.web.dto.posts.PostssaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsServiceTest {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsService postsService;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    void postsService를통해서_저장이된다(){
        String title = "test";
        String content = "test2";
        PostssaveRequestDto dto=PostssaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        postsService.save(dto);

        List<Posts> result = postsRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);
    }

    @Test
    void postsService를통해서_수정이된다(){
        Posts save=postsRepository.save(Posts.builder()
                .title("1")
                .content("2").build());

        String title = "test";
        String content = "test2";


        PostsUpdateRequestDto dto=PostsUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        postsService.update(save.getId(),dto);

        List<Posts> result = postsRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);
    }

    @Test
    void Posts를_수정하면_수정시간이_갱신된다(){
        Posts save=postsRepository.save(Posts.builder()
                .title("1")
                .content("2").build());
        LocalDateTime beforeTime=save.getModifiedDate();

        String title = "test";
        String content = "test2";


        PostsUpdateRequestDto dto=PostsUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .build();
        postsService.update(save.getId(),dto);

        List<Posts> result = postsRepository.findAll();
        LocalDateTime newTime=result.get(0).getModifiedDate();

        assertThat(newTime).isAfter(beforeTime);
        System.out.println(beforeTime);
        System.out.println(newTime);
    }
}
