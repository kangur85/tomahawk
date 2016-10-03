/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.formatting;

import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.model.formatting.BlankLine;
import eu.kaszkowiak.tomahawk.model.formatting.Comment;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Krzysztof
 */
public class StringPrinter {
    
    private StringPrinter() {}

    /**
     *
     * @param children - cannot be null
     * @param fc
     * @return
     */
    public static String getChildrenAsString(List<ConfigurationEntry> children, FormattingConfiguration fc) {
        StringBuilder sb = new StringBuilder();
            
        for (ConfigurationEntry entry : children) {
            if (entry instanceof Comment) {
                if (fc.isPrintComments()) {
                    sb.append(entry.toString(fc)).append("\n");
                }
            } else if (entry instanceof BlankLine) {
                if (fc.isPrintEmptyLines()) {
                    sb.append("\n");
                }
            } else {
                sb.append(entry.toString(fc));
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    static String getIndent(FormattingConfiguration fc, int indentLevel) {
        if (indentLevel > 0) {
            return StringUtils.repeat(fc.getIndentationString(), indentLevel);
        } else {
            return "";
        }
    }

    public static String indent(String entry, FormattingConfiguration fc, int indentLevel) {
        if (entry == null || StringUtils.isEmpty(entry)) {
            return entry;
        }

        String result = fc.isIndented() ? entry.replaceAll("(?m)^", getIndent(fc, indentLevel)) : entry;
        
        return result;
    }
}
