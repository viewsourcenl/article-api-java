package nl.viewsource.articleapi.article.usecase;

import nl.viewsource.articleapi.Fixtures;
import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

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
            findArticle.findById("testId");
            verify(articleRepository).findById("testId");
        }

        @Test
        void findById_returnsRepositoryResult() {
            var expectedResult = Optional.of(Fixtures.fakeArticle());
            when(articleRepository.findById("testId")).thenReturn(expectedResult);

            var result = findArticle.findById("testId");

            assertEquals(expectedResult, result);
        }
    }

    @Nested
    @DisplayName("When finding all")
    class When_findAllArticles {
        @Test
        void findAllArticles_callRepository() {
            findArticle.findAllArticles();
            verify(articleRepository).findAllArticles();
        }

        @Test
        void findAllArticles_returnsRepositoryResult() {
            var expectedResult = List.of(Fixtures.fakeArticle(), Fixtures.fakeArticle());
            when(articleRepository.findAllArticles()).thenReturn(expectedResult);

            var result = findArticle.findAllArticles();

            assertEquals(expectedResult, result);
        }
    }
}