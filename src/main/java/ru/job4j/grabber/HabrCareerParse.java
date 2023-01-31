package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static final int MAX_PAGE = 5;

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) {
        String allLink = String.format("%s%s", SOURCE_LINK, link);
        String rs = "";
        try {
            Connection connection = Jsoup.connect(allLink);
            Document document = connection.get();
            rs = document.select(".vacancy-description__text").text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        try {
            for (int i = 1; i <= MAX_PAGE; i++) {
                String page_link = String.format("%s%s%s", SOURCE_LINK, link, i);
                Connection connection = Jsoup.connect(page_link);
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> {
                     Post post = new Post(
                             row.select(".vacancy-card__title").first().text(),
                             row.child(0).attr("href"),
                             retrieveDescription(row.child(0).attr("href")),
                             dateTimeParser.parse(row.select(".vacancy-card__date").first().child(0).attr("datetime"))
                     );
                     list.add(post);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> listPosts = habrCareerParse.list("/vacancies/java_developer?page=");
        for (Post post : listPosts) {
            System.out.println(post.getTitle());
        }
    }
}
