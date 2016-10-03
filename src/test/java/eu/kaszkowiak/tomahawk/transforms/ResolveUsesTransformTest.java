/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.transforms;

import eu.kaszkowiak.tomahawk.Tomahawk;
import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.model.Configuration;
import eu.kaszkowiak.tomahawk.parser.ParseException;
import eu.kaszkowiak.tomahawk.parser.Parser;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Krzysztof
 */
public class ResolveUsesTransformTest extends TestCase {

    public ResolveUsesTransformTest(String testName) {
        super(testName);
    }

    /**
     * Test of apply method, of class ResolveUsesTransform.
     */
    public void testApply_Configuration() {
        try {

            String test = "<Macro abc $a $b $c>\n"
                    + "println a=$a\n"
                    + "println b=$b\n"
                    + "println c=$c\n"
                    + "</Macro>\n"
                    + "\n"
                    + "Use abc 1 2 3";

            String expected = "<Macro abc $a $b $c>\n"
                    + "\tprintln a=1\n"
                    + "\tprintln b=2\n"
                    + "\tprintln c=3\n"
                    + "</Macro>\n"
                    + "\n" 
                    + "#Tomahawk: Substitution of abc 1 2 3\n"
                    + "println a=1\n"
                    + "println b=2\n"
                    + "println c=3\n";

            FormattingConfiguration fc = new FormattingConfiguration();
            fc.setIndentationString("\t")
                    .setPrintComments(true)
                    .setPrintEmptyLines(true);

            Parser parser = new Parser(test);
            Configuration conf = parser.parse();

            Configuration flattenConf = FlattenTransform.apply(conf);
            flattenConf = ResolveUsesTransform.apply(flattenConf);

            String actual = flattenConf.toString();
            
            //System.out.println(String.format("Expected: <%s>, Actual: <%s>", expected, actual));

            assertEquals(expected, actual);

        } catch (ParseException ex) {
            Logger.getLogger(Tomahawk.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
