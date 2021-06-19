package nl.viewsource.articleapi.usecase.port;

public interface IdGenerator {
    public String generate();
    public boolean isValid(String id);
}
