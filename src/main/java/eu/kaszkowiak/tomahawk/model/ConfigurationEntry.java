package eu.kaszkowiak.tomahawk.model;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author kan
 */
public abstract class ConfigurationEntry {

    public abstract void replaceAll(Map<String, String> map);

    public abstract String toString(FormattingConfiguration fc, int indentLevel);

    protected ArrayList<ConfigurationEntry> children;

    @Override
    public String toString() {
        return toString(new FormattingConfiguration(), 0);
    }

    public String toString(FormattingConfiguration fc) {
        return toString(fc, 0);
    }

    protected ConfigurationEntry() {
        children = new ArrayList();
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public void setChildren(ArrayList<ConfigurationEntry> children) {
        this.children = children;
    }

    public ArrayList<ConfigurationEntry> getChildren() {
        return children;
    }

    public void addChildEntry(ConfigurationEntry ce) {
        if (ce != null) {
            children.add(ce);
        }
    }

}
