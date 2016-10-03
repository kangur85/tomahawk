package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.formatting.StringPrinter;
import eu.kaszkowiak.tomahawk.helpers.StringUtils;
import java.util.Map;

/**
 *
 * @author kan
 */
public class UseDirective extends SimpleDirective {

    private String macroName;

    public UseDirective() {
        super();
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public String getMacroName() {
        return macroName;
    }

    @Override
    public String toString(FormattingConfiguration fc, int indentLevel) {
        StringBuilder sb = new StringBuilder("Use ");
        sb.append(getMacroName());
        if (params != null) {
            for (String parameter : params) {
                sb.append(" ").append(parameter);
            }
        }

        return StringPrinter.indent(sb.toString(), fc, indentLevel);
    }

    @Override
    public void replaceAll(Map<String, String> map) {
        super.replaceAll(map);
        macroName = StringUtils.replaceAll(macroName, map);
    }

}
