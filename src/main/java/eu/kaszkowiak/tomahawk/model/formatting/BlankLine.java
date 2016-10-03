package eu.kaszkowiak.tomahawk.model.formatting;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import java.util.Map;

/**
 *
 * @author kan
 */
public class BlankLine extends FormattingEntry {

    public BlankLine() {
        
    }

    @Override
    public String toString(FormattingConfiguration fc, int indentLevel) {
        return "";
    }

    @Override
    public void replaceAll(Map<String, String> map) {
        // intentionally blank
    }

}
