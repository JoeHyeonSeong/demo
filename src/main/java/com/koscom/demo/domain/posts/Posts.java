package com.koscom.demo.domain.posts;

import com.koscom.demo.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity

public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk 채번 방식
    private long id; //pk(auto increment, bigint)

    @Column(length = 500,nullable = false)
    private String title;

    @Column(length = 2000,nullable = false)
    private String content;

    private String author;//@Column없으면 varchar(255), nullable=true


    @Builder
    public Posts(String title, String content, String author) { //id가 없고 나머지 필드만 있는 생성자
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }
}
