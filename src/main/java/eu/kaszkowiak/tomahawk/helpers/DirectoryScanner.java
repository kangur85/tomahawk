/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.helpers;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author Krzysztof
 */
public class DirectoryScanner {

    public static Collection<File> getFilesFromMaskPath(String path) {
        String directory = FilenameUtils.getFullPath(path);
        String mask = FilenameUtils.getName(path);
        directory = directory == null || directory.isEmpty() ? "./" : directory;
        return FileUtils.listFiles(new File(directory), new WildcardFileFilter(mask), null);
    }

}
