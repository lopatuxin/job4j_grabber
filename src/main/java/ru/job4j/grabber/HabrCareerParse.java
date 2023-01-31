package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com/vacancies/java_developer?page=";
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

    private Post getPost(Element element) {
        return new Post(
                element.select(".vacancy-card__title").first().text(),
                element.child(0).attr("href"),
                retrieveDescription(element.child(0).attr("href")),
                dateTimeParser.parse(element.select(".vacancy-card__date").first().child(0).attr("datetime"))
        );
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        try {
            for (int i = 1; i <= MAX_PAGE; i++) {
                String page_link = String.format("%s%s", link, i);
                Connection connection = Jsoup.connect(page_link);
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                rows.forEach(row -> {
                    list.add(getPost(row));
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        HabrCareerParse habrCareerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> listPosts = habrCareerParse.list("https://career.habr.com/vacancies/java_developer?page=");
        for (Post post : listPosts) {
            System.out.println(post.getTitle());
        }
    }
}
