package com.example.lesson22.controller;

import com.example.lesson22.model.Cat;
import com.example.lesson22.model.news.MeduzaNews;
import com.example.lesson22.model.news.News;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

@Controller
public class HtmlController {

    @GetMapping("/welcome")
    public String welcomePage(Model model, @RequestParam(name = "name") String name) {
        Cat cat = new Cat("Murzik", 5, "Beliy");

        String[] names = {"Murxik", "Leo", "Yarik"};

        model.addAttribute("names", names);
        return "main";
    }

    @GetMapping("/news-page")
    public String newsPage(Model model) throws IOException, InterruptedException, URISyntaxException {
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .GET()
                    .uri(new URI("https://meduza.io/api/v3/search?chrono=news&locale=ru&page=0&per_page=24"))
                    // .header("User-Agent", "Mazilla Firefox 5.0")
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

            String result;

            try (var gzip = new GZIPInputStream(new ByteArrayInputStream(response.body()));
                 var reader = new InputStreamReader(gzip);
                 var in = new BufferedReader(reader);
            ) {
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    builder.append(line);
                }
                result = builder.toString();
            }
            var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var meduzaNews = objectMapper.readValue(result, MeduzaNews.class);

            var news = new ArrayList<News>();
            var bufferNews1 = meduzaNews.getDocuments().getNews1();
            var bufferNews2 = meduzaNews.getDocuments().getNews2();
            var bufferNews3 = meduzaNews.getDocuments().getNews3();

            news.add(new News(bufferNews1.getUrl(), bufferNews1.getTitle(), bufferNews1.getPublishedAt()));
            news.add(new News(bufferNews2.getUrl(), bufferNews2.getTitle(), bufferNews2.getPublishedAt()));
            news.add(new News(bufferNews3.getUrl(), bufferNews3.getTitle(), bufferNews3.getPublishedAt()));

            model.addAttribute("news", news);

        return "news";
    }
}
