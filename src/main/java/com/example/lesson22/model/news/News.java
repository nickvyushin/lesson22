package com.example.lesson22.model.news;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class News {

    private String url;
    private String title;
    private Long publishedAt;

}
