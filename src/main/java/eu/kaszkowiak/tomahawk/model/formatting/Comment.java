/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.model.formatting;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.formatting.StringPrinter;
import java.util.Map;

/**
 *
 * @author Krzysztof
 */
public class Comment extends FormattingEntry {

    protected final String text;

    public Comment(String text) {
        this.text = text;
    }

    @Override
    public String toString(FormattingConfiguration fc, int indentLevel) {
        return StringPrinter.indent(text, fc, indentLevel);
    }

    public String getText() {
        return text;
    }

    @Override
    public void replaceAll(Map<String, String> map) {
        //TODO conditional replace of text in comments - in the future
    }

}
