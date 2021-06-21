package nl.viewsource.articleapi.adapter.id;

import nl.viewsource.articleapi.article.entity.port.IdValidator;
import nl.viewsource.articleapi.article.usecase.port.IdGenerator;
import com.fasterxml.uuid.*;

/**
 * Jug = Java UUID Generator
 */
public class JugIdGenerator implements IdGenerator {


    @Override
    public String generate() {
        return generator().generate().toString();
    }

    private static NoArgGenerator generator() {
        return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
    }
}
