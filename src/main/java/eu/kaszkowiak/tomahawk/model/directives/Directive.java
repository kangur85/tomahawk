package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.model.formatting.InlineComment;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kan
 */
public abstract class Directive extends ConfigurationEntry {
    
    private InlineComment inlineComment;

    public InlineComment getInlineComment() {
        return inlineComment;
    }

    public void setInlineComment(InlineComment inlineComment) {
        this.inlineComment = inlineComment;
    }
    
    public boolean hasInlineComment() {
        return inlineComment != null && StringUtils.isNotBlank(inlineComment.getText());
    }
}
