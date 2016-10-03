/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.formatting.StringPrinter;
import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kan
 */
public class TagDirective extends Directive {

    private ArrayList<String> params;

    private String name;

    private String endName;

    public TagDirective() {
        params = new ArrayList();
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addParam(String param) {
        params.add(param);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    @Override
    public String toString(FormattingConfiguration fc, int indentLevel) {
        StringBuilder sb = new StringBuilder("<").append(name);

        for (String param : params) {
            sb.append(" ").append(param);  // TODO separation character could be customized
        }

        sb.append(">\n");

        if (hasChildren()) {
            final String childrenString = StringPrinter.getChildrenAsString(children, fc);
            if (StringUtils.isNotEmpty(childrenString)) {
                sb.append(StringPrinter.indent(childrenString, fc, indentLevel+1));
            }
        }

        sb.append("</").append(endName).append(">");

        return StringPrinter.indent(sb.toString(), fc, indentLevel);
    }

    @Override
    public String toString() {
        return toString(new FormattingConfiguration(), 0);
    }

    @Override
    public void replaceAll(Map<String, String> map) {
        if (hasChildren()) {
            for (ConfigurationEntry entry : getChildren()) {
                entry.replaceAll(map);
            }
        }
    }

}
