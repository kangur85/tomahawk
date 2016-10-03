package eu.kaszkowiak.tomahawk.model.formatting;

import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;

/**
 *
 * @author kan
 */
public abstract class FormattingEntry extends ConfigurationEntry {
    
    @Override
    public boolean hasChildren() {
        return false;
    }
    
}
