package com.koscom.demo.domain.posts;

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
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;


    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    void 게시글저장_불러오기() {
        String title="test title";
        String contents="test contents";
        postsRepository.save(Posts.builder()
                .title(title)
                .content(contents)
                .build());
        List<Posts> result=postsRepository.findAll();
        System.out.println(result.get(0).getId());
        System.out.println(result.get(0).getTitle());

        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(contents);
    }

    @Test
    public void 등록시간_수정시간이_저장된다(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
              .title("title")
              .content("content")
              .author("author")
              .build());
         //when
         List<Posts> postsList = postsRepository.findAll();

         //then
         Posts posts = postsList.get(0);

         System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate());
         assertThat(posts.getCreatedDate()).isAfter(now);
         assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
