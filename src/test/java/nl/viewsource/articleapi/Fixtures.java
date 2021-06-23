package nl.viewsource.articleapi;

import com.github.javafaker.Faker;
import nl.viewsource.articleapi.article.entity.Article;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Fixtures {
    private static Faker faker = new Faker();

    public static Article fakeArticle() {
        return Article.builder()
                .id(faker.idNumber().toString())
                .title(faker.lorem().sentence())
                .description(faker.lorem().paragraph())
                .link(faker.internet().url())
                .image(faker.internet().image())
                .date(faker.date().past(1000, TimeUnit.DAYS))
                .tags(List.of(faker.lorem().word(), faker.lorem().word()))
                .build()
                ;
    }
}
