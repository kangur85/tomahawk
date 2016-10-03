/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.kaszkowiak.tomahawk.model.formatting;

import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;

/**
 *
 * @author Krzysztof
 */
public abstract class FormattingEntry extends ConfigurationEntry {
    
    @Override
    public boolean hasChildren() {
        return false;
    }
    
}
