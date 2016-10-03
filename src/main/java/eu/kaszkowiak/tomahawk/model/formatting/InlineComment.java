package eu.kaszkowiak.tomahawk.model.formatting;

/**
 *
 * @author kan
 */
public class InlineComment extends Comment {

    public InlineComment(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return text;
    }
}
