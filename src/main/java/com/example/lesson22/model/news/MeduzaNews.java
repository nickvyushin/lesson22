package com.example.lesson22.model.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MeduzaNews {

    @JsonProperty("has_next")
    private Boolean hasNext;
    private MeduzaNewsDetailsInfo documents;
    private List<String> collection;
    @JsonProperty("_count")
    private Integer count;

}
