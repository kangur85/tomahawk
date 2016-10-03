package eu.kaszkowiak.tomahawk.helpers;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author kan
 */
public class DirectoryScanner {

    public static Collection<File> getFilesFromMaskPath(String path) {
        String directory = FilenameUtils.getFullPath(path);
        String mask = FilenameUtils.getName(path);
        directory = directory == null || directory.isEmpty() ? "./" : directory;
        return FileUtils.listFiles(new File(directory), new WildcardFileFilter(mask), null);
    }

}
