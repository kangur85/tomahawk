/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.model.formatting;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import java.util.Map;

/**
 *
 * @author Krzysztof
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
