package com.koscom.demo.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk 채번 방식
    private long id; //pk(auto increment, bigint)

    @Column(length = 500,nullable = false)
    private String title;

    @Column(length = 2000,nullable = false)
    private String contents;

    private String author;//@Column없으면 varchar(255), nullable=true

    @Builder
    public Posts(String title, String contents, String author) { //id가 없고 나머지 필드만 있는 생성자
        this.title = title;
        this.contents = contents;
        this.author = author;
    }
}
