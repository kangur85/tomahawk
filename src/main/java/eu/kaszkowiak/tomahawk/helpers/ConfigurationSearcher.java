package eu.kaszkowiak.tomahawk.helpers;

import eu.kaszkowiak.tomahawk.model.Configuration;
import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.model.directives.Directive;
import eu.kaszkowiak.tomahawk.model.directives.TagDirective;
import eu.kaszkowiak.tomahawk.model.directives.UseDirective;
import eu.kaszkowiak.tomahawk.model.formatting.Comment;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author kan
 */
public class ConfigurationSearcher {
    
    private static boolean isMacroNameEqual(final TagDirective macro, final String name) {
        return macro != null
                && "Macro".equals(macro.getName())
                && macro.getChildren() != null
                && macro.getParams() != null
                && macro.getParams().get(0).equals(name);
    }
    public static Directive getMacroByUseDirective(final Configuration conf, final UseDirective useDirective)  {
        return getMacroByUseDirective(conf.getEntries(), useDirective);
    }

    public static Directive getMacroByUseDirective(final List<ConfigurationEntry> entries, final UseDirective useDirective)  {
        if (StringUtils.isBlank(useDirective.getMacroName())) {
            throw new IllegalArgumentException("Macro name in use directive cannot be empty!");
        }

        Directive result = null;
        if (entries != null) {
            for (ConfigurationEntry entry : entries) {
                if (entry instanceof TagDirective && isMacroNameEqual((TagDirective) entry, useDirective.getMacroName())) {
                    result = (TagDirective) entry;
                }
            }
        }

        if (result == null) {
           //FIXME
           TagDirective tmp = new TagDirective();
           tmp.setName("Macro");
           tmp.setEndName("Macro");
           tmp.addParam("UNSUPPORTED");
           ArrayList<ConfigurationEntry> children = new ArrayList();
           children.add(new Comment(String.format("Macro with name \"%s\" couldn't be found.", useDirective.getMacroName())));
           children.add(useDirective);
           tmp.setChildren(children);
           return tmp;
        }
        
        return result;
    }
}
