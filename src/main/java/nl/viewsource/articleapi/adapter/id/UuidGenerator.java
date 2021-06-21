package nl.viewsource.articleapi.adapter.id;

import nl.viewsource.articleapi.article.entity.port.IdValidator;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;

import java.util.UUID;

public class UuidGenerator implements IdGenerator, IdValidator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean isValid(String id) {
        try {
            return UUID.fromString(id).toString().equals(id);
        } catch (Exception e) {
            return false;
        }
    }
}
