package com.koscom.demo.service;

import com.koscom.demo.domain.posts.Posts;
import com.koscom.demo.domain.posts.PostsRepository;
import com.koscom.demo.web.dto.posts.PostsResponseDto;
import com.koscom.demo.web.dto.posts.PostsUpdateRequestDto;
import com.koscom.demo.web.dto.posts.PostssaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostssaveRequestDto requestDto){
        Posts posts = postsRepository.save(requestDto.toEntity());
        return posts.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto){
        Posts entity=postsRepository.findById(id)
                .orElseThrow((()->new IllegalArgumentException("사용자 없음 id="+id)));
        entity.update(dto.getTitle(),dto.getContent());
        return entity.getId();
    }

    public PostsResponseDto findById(Long id){
        Posts entity=postsRepository.findById(id)
                .orElseThrow((()->new IllegalArgumentException("사용자 없음 id="+id)));
        return new PostsResponseDto(entity);
    }
}
