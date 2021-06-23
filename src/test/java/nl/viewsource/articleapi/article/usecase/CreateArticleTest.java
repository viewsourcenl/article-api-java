package nl.viewsource.articleapi.article.usecase;

import static org.mockito.Mockito.*;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
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
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CreateArticleTest {
    Faker faker = new Faker();

    ArticleRepository articleRepository;
    IdGenerator idGenerator;
    ArticleValidator articleValidator;
    CreateArticle createArticle;
    Article.Builder builder;


    @BeforeEach
    void beforeEach() {
        articleRepository = mock(ArticleRepository.class);
        idGenerator = mock(IdGenerator.class, invocationOnMock -> "TestId");
        articleValidator = mock(ArticleValidator.class);
        createArticle = new CreateArticle(articleRepository, idGenerator, articleValidator);
        builder = Article.builder()
                .title(faker.lorem().sentence())
                .description(faker.lorem().paragraph())
                .link(faker.internet().url())
                .image(faker.internet().image())
                .date(faker.date().past(1000, TimeUnit.DAYS));
    }

    @Nested
    @DisplayName("When creating an instance of the usecase")
    class WhenCreateNewCreateArticle {
        @Test()
        @DisplayName("requires ArticleRepository")
        void new_requiresArticleRepository() {
            assertThrows(
                    NullPointerException.class,
                    () -> new CreateArticle(null, idGenerator, articleValidator)
            );
        }

        @Test()
        @DisplayName("requires IdGenerator")
        void new_requiresIdGenerator() {
            assertThrows(
                    NullPointerException.class,
                    () -> new CreateArticle(articleRepository, null, articleValidator)
            );
        }

        @Test()
        @DisplayName("requires ArticleValidator")
        void new_requiresArticleValidator() {
            assertThrows(
                    NullPointerException.class,
                    () -> new CreateArticle(articleRepository, idGenerator, null)
            );
        }
    }

    @Nested
    @DisplayName("When creating an article")
    class WhenCreating {

        @Test
        @DisplayName("adds an id")
        void create_addsId() throws ArticleValidationException {
            var input = builder.build();
            var expectedResult = Article.builder(input).id("TestId").build();
            createArticle.create(input);

            verify(articleRepository).create(expectedResult);
        }

        @Test
        @DisplayName("returns result of  articleRepository")
        void create_returnRepoResult() throws ArticleValidationException {
            var input = builder.build();
            var expectedResult = Article.builder()
                    .id("ID")
                    .title("testTitle")
                    .description("testDescription")
                    .date(new Date(0))
                    .build();

            when(articleRepository.create(any(Article.class))).thenReturn(expectedResult);

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
    @DisplayName("When updating an article")
    class WhenUpdating {
        @Test
        @DisplayName("Returns empty optional when article not found in articleRepository")
        void update_empty_notFound() throws ArticleValidationException {
            Optional<Article> expectedResult = Optional.empty();
            var input = builder.build();

            when(articleRepository.findById("test_id")).thenReturn(Optional.empty());

            var result = createArticle.update("test_id", input);

            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("throws on article validation exception")
        void update_throwsOnValidationException() throws ArticleValidationException {
            var input = builder.build();
            var optionalArticle = Optional.of(builder.id("test_id").build());

            when(articleRepository.findById("test_id")).thenReturn(optionalArticle);

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

            when(articleRepository.findById("test_id")).thenReturn(optionalArticle);
            when(articleRepository.replace(any(Article.class))).thenReturn(updatedArticle);

            createArticle.update("test_id", updatedArticle);

            verify(articleRepository).replace(updatedArticle);
        }

        @Test
        @DisplayName("returns optional of article returned from repo")
        void update_returns_repo_result() throws ArticleValidationException {
            var input = builder.build();
            var optionalArticle = Optional.of(builder.id("test_id").title("other title").build());
            var updatedArticle = builder.id("test_id").build();
            var expectedResult = builder.title("expected_title").id("test_id").build();

            when(articleRepository.findById("test_id")).thenReturn(optionalArticle);
            when(articleRepository.replace(any(Article.class))).thenReturn(expectedResult);

            var result = createArticle.update("test_id", input);

            assertEquals(expectedResult, result.get());
        }
    }
}