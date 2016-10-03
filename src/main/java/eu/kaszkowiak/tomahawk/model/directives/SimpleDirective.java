package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.formatting.StringPrinter;
import eu.kaszkowiak.tomahawk.helpers.StringUtils;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author kan
 */
public class SimpleDirective extends Directive {

    protected ArrayList<String> params;
    
    protected String name;

    public SimpleDirective() {
        this.params = new ArrayList();
    }
    
    public void addParam(String parameter) {
        getParams().add(parameter);
    }

    /**
     * @return the params
     */
    public ArrayList<String> getParams() {
        return params;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(FormattingConfiguration fc, int indentLevel) {
        StringBuilder sb = new StringBuilder(getName());
        for (String parameter: getParams()) {
            sb.append(" ").append(parameter);
        }
        if (fc.isPrintComments() && hasInlineComment()) {
            sb.append(" ").append(getInlineComment().getText());
        }
        return StringPrinter.indent(sb.toString(), fc, indentLevel);        
    }

    @Override
    public void replaceAll(Map<String, String> map) {
        name = StringUtils.replaceAll(name, map);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                params.set(i, StringUtils.replaceAll(params.get(i), map));
            }
        }
    }
}
