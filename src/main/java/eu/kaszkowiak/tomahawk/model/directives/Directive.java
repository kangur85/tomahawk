/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.model.formatting.InlineComment;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Krzysztof
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
