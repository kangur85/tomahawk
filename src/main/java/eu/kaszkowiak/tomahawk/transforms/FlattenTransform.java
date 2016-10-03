package eu.kaszkowiak.tomahawk.transforms;

import eu.kaszkowiak.tomahawk.model.Configuration;
import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.model.directives.IncludeDirective;
import eu.kaszkowiak.tomahawk.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kan
 */
public class FlattenTransform {
    
    private FlattenTransform() {
    }

    public static Configuration apply(final Configuration configuration) {
        Configuration result = new Configuration();

        boolean includeFound = false;
        
        if (configuration.getEntries() != null) {
            for (ConfigurationEntry entry : configuration.getEntries()) {
                if (entry instanceof IncludeDirective) {
                    includeFound = true;
                    IncludeDirective includeDirective = (IncludeDirective) entry;
                    try {
                        ArrayList<ConfigurationEntry> entries = includeDirective.getIncludedEntries();
                        result.addAllEntries(entries);
                    } catch (IOException | ParseException ex) {
                        Logger.getLogger(FlattenTransform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    result.addEntry(entry);
                }
            }
        }

        return includeFound ? apply(result) : result;
    }

}
