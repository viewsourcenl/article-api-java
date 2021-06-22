package nl.viewsource.articleapi.article.usecase;

import static org.mockito.Mockito.*;

import nl.viewsource.articleapi.article.entity.Article;
import nl.viewsource.articleapi.article.entity.ArticleValidationException;
import nl.viewsource.articleapi.article.entity.ArticleValidator;
import nl.viewsource.articleapi.article.usecase.port.ArticleRepository;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateArticleTest {
    ArticleRepository repository;
    IdGenerator idGenerator;
    ArticleValidator articleValidator;
    CreateArticle createArticle;
    Article.Builder builder;

    @BeforeEach
    void beforeEach() {
        repository = mock(ArticleRepository.class);
        idGenerator = mock(IdGenerator.class, invocationOnMock -> "TestId");
        articleValidator = mock(ArticleValidator.class);
        createArticle = new CreateArticle(repository, idGenerator, articleValidator);
        builder = Article.builder()
                .title("Something Something")
                .description("You won't believe what happened.")
                .link("https://www.example.com/article/123")
                .image("https://www.example.com/article/pic.png")
                .date(new Date());
    }

    @Nested
    @DisplayName("creating an article")
    class WhenCreating {

        @Test
        @DisplayName("adds an id")
        void create_addsId() throws ArticleValidationException {
            var input = builder.build();
            var expectedResult = Article.builder(input).id("TestId").build();
            createArticle.create(input);

            verify(repository).create(expectedResult);
        }

        @Test
        @DisplayName("returns result of  repository")
        void create_returnRepoResult() throws ArticleValidationException {
            var input = builder.build();
            var expectedResult = Article.builder()
                    .id("ID")
                    .title("testTitle")
                    .description("testDescription")
                    .date(new Date(0))
                    .build();

            when(repository.create(any(Article.class))).thenReturn(expectedResult);

            var result = createArticle.create(input);

            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("throws on article validation exception")
        void create_throwsOnValidationException() throws ArticleValidationException {
            var input = builder.build();
            doThrow(ArticleValidationException.class)
                    .when(articleValidator)
                    .validate(any(Article.class));

            assertThrows(
                    ArticleValidationException.class,
                    () -> createArticle.create(input)
            );
        }
    }

    @Nested
    @DisplayName("updating an article")
    class WhenUpdating {
        @Test
        @DisplayName("Returns empty optional when article not found in repository")
        void update_empty_notFound() throws ArticleValidationException {
            Optional<Article> expectedResult = Optional.empty();
            var input = builder.build();

            when(repository.findById("test_id")).thenReturn(Optional.empty());

            var result =  createArticle.update("test_id", input);

            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("throws on article validation exception")
        void update_throwsOnValidationException() throws ArticleValidationException {
            var input = builder.build();
            var optionalArticle = Optional.of(builder.id("test_id").build());

            when(repository.findById("test_id")).thenReturn(optionalArticle);

            doThrow(ArticleValidationException.class)
                    .when(articleValidator)
                    .validate(any(Article.class));

            assertThrows(
                    ArticleValidationException.class,
                    () -> createArticle.update("test_id", input)
            );
        }


        @Test
        @DisplayName("calls repo with updated article")
        void update_update_repo() throws ArticleValidationException {
            var input = builder.build();
            var optionalArticle = Optional.of(builder.id("test_id").title("other title").build());
            var updatedArticle = builder.id("test_id").title("updated_title").build();

            when(repository.findById("test_id")).thenReturn(optionalArticle);
            when(repository.replace(any(Article.class))).thenReturn(updatedArticle);

            createArticle.update("test_id", updatedArticle);

            verify(repository).replace(updatedArticle);
        }

        @Test
        @DisplayName("returns optional of article returned from repo")
        void update_returns_repo_result() throws ArticleValidationException {
            var input = builder.build();
            var optionalArticle = Optional.of(builder.id("test_id").title("other title").build());
            var updatedArticle = builder.id("test_id").build();
            var expectedResult = builder.title("expected_title").id("test_id").build();

            when(repository.findById("test_id")).thenReturn(optionalArticle);
            when(repository.replace(any(Article.class))).thenReturn(expectedResult);

            var result = createArticle.update("test_id", input);

            assertEquals(expectedResult, result.get());
        }
    }
}