package com.example.lesson22.model.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeduzaNewsDetailsInfo {

    @JsonProperty("news/2020/10/22/zhitelya-krasnoyarska-raspylivshego-gaz-iz-ballonchika-v-sotrudnikov-fsb-prigovorili-k-vosmi-godam-kolonii")
    private MeduzaNewsInfo news1;

    @JsonProperty("news/2020/10/22/umerla-amerikanskaya-tantsovschitsa-mardzh-chempion-stavshaya-proobrazom-belosnezhki-dlya-multfilma-disney")
    private MeduzaNewsInfo news2;

    @JsonProperty("news/2020/10/22/sud-v-moskve-postanovil-snyat-ogranicheniya-na-pokaz-filma-beslan-na-yutyub-kanale-rossii-1")
    private MeduzaNewsInfo news3;
}
