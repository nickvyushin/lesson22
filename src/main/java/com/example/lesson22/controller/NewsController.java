package com.example.lesson22.controller;

import com.example.lesson22.model.news.MeduzaNews;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.zip.GZIPInputStream;

@RestController
public class NewsController {

    @GetMapping({"/news", "/"})
    public String getNews() throws URISyntaxException, IOException, InterruptedException {
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

        return meduzaNews.toString();
    }
}
