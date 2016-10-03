package eu.kaszkowiak.tomahawk.helpers;

import java.util.Map;

/**
 *
 * @author kan
 */
public class StringUtils {
    
    static String[] convertToStringArray(final Object[] inputArray) {
        if (inputArray == null) {
            return null;
        }
        String[] result = new String[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] instanceof String) {
                result[i] = (String) inputArray[i];
            }
            else {
                throw new IllegalArgumentException("InputArray contains a class not being an instance of String but " + inputArray[i].getClass().getName());
            }
        }
        return result;
    }

    public static String replaceAll(String source, Map<String, String> map) {
        String[] keys = convertToStringArray(map.keySet().toArray());
        String[] values = convertToStringArray(map.values().toArray());
        return org.apache.commons.lang.StringUtils.replaceEach(source, keys, values);
    }
}
