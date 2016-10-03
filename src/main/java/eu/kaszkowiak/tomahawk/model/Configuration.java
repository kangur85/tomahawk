package eu.kaszkowiak.tomahawk.model;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.formatting.StringPrinter;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author kan
 */
public class Configuration {

    private ArrayList<ConfigurationEntry> entries;

    public Configuration() {
        entries = new ArrayList();
    }

    public void addEntry(ConfigurationEntry entry) {
        if (entry != null) {
            entries.add(entry);
        }
    }

    public void addAllEntries(final Collection<ConfigurationEntry> entries) {
        if (entries != null) {
            this.entries.addAll(entries);
        }
    }

    public String toString(FormattingConfiguration fc) {
        return StringPrinter.getChildrenAsString(entries, fc);
    }

    @Override
    public String toString() {
        return toString(new FormattingConfiguration());
    }

    public ArrayList<ConfigurationEntry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<ConfigurationEntry> entries) {
        this.entries = entries;
    }

}
