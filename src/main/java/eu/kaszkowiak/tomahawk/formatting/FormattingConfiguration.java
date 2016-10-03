/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.formatting;

/**
 *
 * @author Krzysztof
 */
public class FormattingConfiguration {
    
    private String indentationString;
    
    private boolean printComments;
    
    private boolean printEmptyLines;

    public FormattingConfiguration() {
        indentationString = "\t";
        printComments = true;
        printEmptyLines = true;
    }

    public String getIndentationString() {
        return indentationString;
    }

    public boolean isPrintComments() {
        return printComments;
    }

    public boolean isPrintEmptyLines() {
        return printEmptyLines;
    }

    public FormattingConfiguration setIndentationString(String indentationString) {
        this.indentationString = indentationString;
        return this;
    }

    public FormattingConfiguration setPrintComments(boolean printComments) {
        this.printComments = printComments;
        return this;
    }

    public FormattingConfiguration setPrintEmptyLines(boolean printEmptyLines) {
        this.printEmptyLines = printEmptyLines;
        return this;
    }
    
    public boolean isIndented() {
        return indentationString != null && !indentationString.isEmpty();
    }
}
