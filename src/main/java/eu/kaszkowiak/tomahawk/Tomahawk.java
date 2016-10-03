/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.model.Configuration;
import eu.kaszkowiak.tomahawk.parser.ParseException;
import eu.kaszkowiak.tomahawk.parser.Parser;
import eu.kaszkowiak.tomahawk.transforms.FlattenTransform;
import eu.kaszkowiak.tomahawk.transforms.ResolveUsesTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krzysztof
 */
public class Tomahawk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {

            FormattingConfiguration fc = new FormattingConfiguration()
                    .setPrintComments(true);
                    //.setIndentationString(null);
            Parser parser = new Parser(new FileInputStream(new File(args[0])));
            Configuration conf = parser.parse();

            Configuration flattenConf = FlattenTransform.apply(conf);
            Configuration resolvedConf = ResolveUsesTransform.apply(flattenConf);
            
            FileWriter fw = new FileWriter("/home/kan/output.conf");
            fw.write(resolvedConf.toString(fc));
            fw.flush();
            fw.close();
            
            FileWriter fw2 = new FileWriter("/home/kan/output_conf.conf");
            fw2.write(flattenConf.toString(fc));
            fw2.flush();
            fw2.close();

        } catch ( ParseException | IOException ex) {
            Logger.getLogger(Tomahawk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
