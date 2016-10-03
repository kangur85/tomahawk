package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.formatting.StringPrinter;
import eu.kaszkowiak.tomahawk.helpers.DirectoryScanner;
import eu.kaszkowiak.tomahawk.helpers.FileReader;
import eu.kaszkowiak.tomahawk.helpers.StringUtils;
import eu.kaszkowiak.tomahawk.model.Configuration;
import eu.kaszkowiak.tomahawk.model.ConfigurationEntry;
import eu.kaszkowiak.tomahawk.parser.ParseException;
import eu.kaszkowiak.tomahawk.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kan
 */
public class IncludeDirective extends Directive {

    private String includeMask;

    public IncludeDirective(String includeMask) throws ParseException {
        this.includeMask = includeMask;
        try {
            children.addAll(getIncludedEntries());
        } catch (IOException ex) {
            throw new ParseException("ParsingError: " + ex.getMessage());
        }
    }

    public Collection<File> getIncludedFiles() {
        return DirectoryScanner.getFilesFromMaskPath(includeMask);
    }

    public ArrayList<ConfigurationEntry> getIncludedEntries() throws IOException, ParseException {
        ArrayList<ConfigurationEntry> result = new ArrayList();
        Collection<File> files = getIncludedFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    result.addAll(getEntriesFromFile(file));
                } catch (ParseException ex) {
                    Logger.getLogger(IncludeDirective.class.getName()).log(Level.SEVERE, null, ex);
                    throw new ParseException(file.getAbsolutePath() + ": " + ex.getMessage());
                }
            }
        }
        return result;
    }

    private static ArrayList<ConfigurationEntry> getEntriesFromFile(File file) throws IOException, ParseException {
        String fileContent = FileReader.readFile(file.getAbsolutePath());
        Parser parser = new Parser(fileContent);
        Configuration conf = parser.parse();
        return conf.getEntries();
    }

    @Override
    public String toString(FormattingConfiguration fc, int indentLevel) {
        StringBuilder sb = new StringBuilder("Include ");
        sb.append(includeMask);
        return StringPrinter.indent(sb.toString(), fc, indentLevel);
    }

    @Override
    public void replaceAll(Map<String, String> map) {
        includeMask = StringUtils.replaceAll(includeMask, map);
    }

    

}
