/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.transforms;

import eu.kaszkowiak.tomahawk.helpers.ConfigurationSearcher;
import eu.kaszkowiak.tomahawk.model.Configuration;
import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.model.directives.Directive;
import eu.kaszkowiak.tomahawk.model.directives.TagDirective;
import eu.kaszkowiak.tomahawk.model.directives.UseDirective;
import eu.kaszkowiak.tomahawk.model.formatting.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Krzysztof
 */
public class ResolveUsesTransform {

    private ResolveUsesTransform() {
    }

    public static Configuration apply(Configuration conf) {
        Configuration result = conf;
        result.setEntries(apply(conf, conf.getEntries()));
        return result;
    }

    private static ArrayList<ConfigurationEntry> substituteUseParametersToCollection(final UseDirective use, final ConfigurationEntry... entries) {
        ArrayList<ConfigurationEntry> result = new ArrayList();
        if (entries != null) {
            for (ConfigurationEntry entry : entries) {
                result.add(substituteEntryParameters(use, entry));
            }
        }
        return result;
    }

    private static ConfigurationEntry substituteEntryParameters(UseDirective use, ConfigurationEntry entry) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static HashMap<String, String> getParameterMappings(final TagDirective macro, final UseDirective use) {
        HashMap<String, String> result = new HashMap();

        if (macro.getParams().size() != use.getParams().size() + 1) {
            // return null when number of parameters do not fit
            return null;
//            throw new IllegalArgumentException(
//                    String.format("Number of macro parameters (%s) must be equal with number of use parameters (%s). Macro name: %s",
//                            macro.getParams().size(), use.getParams().size(), macro.getName()));
        }

        ArrayList<String> macroParams = macro.getParams();
        ArrayList<String> useParams = use.getParams();

        for (int i = 0; i < useParams.size(); i++) {
            if (result.containsKey(macroParams.get(i + 1))) {
                throw new IllegalArgumentException(
                        "Macro should contain unique argument names. Non-unique argument: " + macroParams.get(i));
            }
            result.put(macroParams.get(i + 1), useParams.get(i));
        }
        return result;
    }

    private static List<ConfigurationEntry> substituteUseParametersToMacro(final TagDirective macro, final UseDirective use) {
        ArrayList<ConfigurationEntry> result = new ArrayList();
        HashMap<String, String> mapping = getParameterMappings(macro, use);
        if (mapping != null) {
            if (macro.hasChildren()) {
                for (ConfigurationEntry entry : macro.getChildren()) {
                    entry.replaceAll(mapping);
                    result.add(entry);
                }
            }
        } else {
            result.add(use);
        }

        return result;
    }

    private static Directive getMacroByUseDirective(final List<ConfigurationEntry> conf, final UseDirective useDirective) {
        return ConfigurationSearcher.getMacroByUseDirective(conf, useDirective);

    }

    private static ArrayList<ConfigurationEntry> apply(Configuration conf, List<ConfigurationEntry> entries) {
        ArrayList<ConfigurationEntry> result = new ArrayList();
        if (entries != null) {
            for (ConfigurationEntry entry : entries) {
                if (entry instanceof UseDirective) {
                    UseDirective useDirective = (UseDirective) entry;
                    Directive macroDirective = getMacroByUseDirective(conf.getEntries(), useDirective);

                    result.add(debugLoggingComments(useDirective, conf.getEntries()));

                    if (macroDirective instanceof TagDirective) {
                        result.addAll(substituteUseParametersToMacro((TagDirective) macroDirective, useDirective));
                    } else if (macroDirective instanceof UseDirective) {
                        result.add(macroDirective);
                    }

                } else {
                    entry.setChildren(apply(conf, entry.getChildren()));
                    result.add(entry);
                }
            }
        }
        return result;
    }

    private static Comment debugLoggingComments(UseDirective useDirective, ArrayList<ConfigurationEntry> entries) {
        // TODO -> remove the block below or make its call conditional
        StringBuilder sb = new StringBuilder("#Tomahawk: Substitution of ");
        sb.append(useDirective.getMacroName());
        if (useDirective.getParams() != null) {
            for (String param : useDirective.getParams()) {
                sb.append(" ").append(param);
            }
        }
        return new Comment(sb.toString());
        //--------------------
    }

}
