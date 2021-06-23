package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.entity.ArticleValidator;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FindArticleTest {

    ArticleRepository articleRepository;
    FindArticle findArticle;

    @BeforeEach
    void setUp() {
        articleRepository = mock(ArticleRepository.class);
        findArticle = new FindArticle(articleRepository);
    }

    @Nested
    @DisplayName("When creating an instance of the usecase")
    class When_newFindArticle {
        @Test()
        @DisplayName("requires ArticleRepository")
        void new_requiresArticleRepository() {
            assertThrows(
                    NullPointerException.class,
                    () -> new FindArticle(null)
            );
        }
    }

    @Nested
    @DisplayName("When finding by id")
    class When_findById {
        @Test
        void findById_callsRepository() {
        }
        @Test
        void findById_returnsRepositoryResult() {

        }
    }

    @Nested
    @DisplayName("When finding all")
    class When_findAllArticles {
        @Test
        void findAllArticles_callRepository() {
        }
        @Test
        void findAllArticles_returnsRepositoryResult() {
        }

    }
}