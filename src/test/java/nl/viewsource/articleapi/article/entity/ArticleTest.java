package nl.viewsource.articleapi.article.entity;

import nl.viewsource.articleapi.Fixtures;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest {
    Article.Builder builder;
    Article fakeArticle;


    @BeforeEach
    void beforeEach() {
        fakeArticle  = Fixtures.fakeArticle();
        builder = Article.builder(fakeArticle);
    }


    @Test
    @DisplayName("builds without error")
    void happy() {
        Article article = Assertions.assertDoesNotThrow(() ->
                builder.build()
        );

        assertEquals(fakeArticle.title, article.title);
        assertEquals(fakeArticle.description, article.description);
        assertEquals(fakeArticle.link, article.link);
        assertEquals(fakeArticle.image, article.image);
        assertEquals(fakeArticle.date, article.date);
    }

    @Test
    @DisplayName("equals - article equal to itself")
    void sameArticleIsEqual() {
        Article article = builder.build();
        assertEquals(article, article);
    }

    @Test
    @DisplayName("equals - article equal to copy of itself")
    void copiedArticleIsEqual() {
        Article article = builder.build();
        Article articleCopy = builder.build();
        assertEquals(article, articleCopy);
    }

    @Test
    @DisplayName("equals - different ID")
    void different_id() {
        Article article1 = builder.build();
        Article article2 = builder.id("test").build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - different title")
    void different_title() {
        Article article1 = builder.build();
        Article article2 = builder.title("test").build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - different description")
    void different_description() {
        Article article1 = builder.build();
        Article article2 = builder.description("test").build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - different tags")
    void different_tags() {
        Article article1 = builder.build();
        Article article2 = builder.tags("test").build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - different link")
    void different_link() {
        Article article1 = builder.build();
        Article article2 = builder.link("test").build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - different image")
    void different_image() {
        Article article1 = builder.build();
        Article article2 = builder.image("test").build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - different date")
    void different_date() {
        Article article1 = builder.build();
        Article article2 = builder.date(new Date(0)).build();
        assertNotEquals(article1, article2);
    }

    @Test
    @DisplayName("equals - article not equal null")
    void notNull() {
        Article article = builder.build();
        assertNotEquals(null, article);
    }

    @Test
    @DisplayName("equals - article not equal String")
    void notNullSameObject() {
        Article article = builder.build();
        assertFalse(article.equals("Article"));
    }

    @Test
    @DisplayName("can have an id")
    void canHaveId() {
        builder.id("SomeOtherId");
        Article article = builder.build();
        assertEquals("SomeOtherId", article.id);
    }

    @Test
    @DisplayName("accepts tags as list")
    void canHaveTagsAsList() {
        builder.tags(List.of("Linux", "Java"));
        Article article = builder.build();
        assertEquals(List.of("Linux", "Java"), article.tags);
    }

    @Test
    @DisplayName("accepts tags multiple strings")
    void canHaveTagsAsVarArgs() {
        builder.tags("Linux", "Java");
        Article article = builder.build();
        assertEquals(List.of("Linux", "Java"), article.tags);
    }

    @Test
    @DisplayName("has an empty tags list when none provided")
    void defaultEmptyTagList() {
        Article article = Article.builder().build();
        assertEquals(Collections.emptyList(), article.tags);
    }

    @Test
    @DisplayName("Builder copies all Article values")
    void copyArticle() {
        Article src = builder.build();
        Article copy = Article.builder(src).build();

        assertEquals(copy, src);
    }
}
