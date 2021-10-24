package com.koscom.demo.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository: 기본 CRUD자동 구현
public interface PostsRepository extends JpaRepository<Posts,Long> {//대상, pk타입
    
}
