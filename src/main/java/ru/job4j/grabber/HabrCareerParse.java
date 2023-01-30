package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrCareerParse {
    private static final String SOURCE_LINK = "https://career.habr.com";
    private static String page_link = "";

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 6; i++) {
            page_link = String.format("%s/vacancies/java_developer?page=%s", SOURCE_LINK, i);
            Connection connection = Jsoup.connect(page_link);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                Element dateEl = dateElement.child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                String date = dateEl.attr("datetime");
                System.out.printf("%s %s %s%n", vacancyName, link, date);
            });
        }
    }
}
