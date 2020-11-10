package com.example.lesson22.model.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeduzaNewsInfo {

    private String url;
    private String title;
    @JsonProperty("published_at")
    private Long publishedAt;
}
