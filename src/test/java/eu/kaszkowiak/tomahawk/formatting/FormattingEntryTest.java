package eu.kaszkowiak.tomahawk.formatting;

import eu.kaszkowiak.tomahawk.model.formatting.FormattingEntry;
import junit.framework.TestCase;

import java.util.Map;

/**
 * Created by kan on 03.10.16.
 */
public class FormattingEntryTest extends TestCase {

    class FormattingEntryTestClass extends FormattingEntry {

        @Override
        public void replaceAll(Map<String, String> map) {

        }

        @Override
        public String toString(FormattingConfiguration fc, int indentLevel) {
            return null;
        }
    }

    public void testHasChildren() {
        // given
        FormattingEntryTestClass fetc = new FormattingEntryTestClass();

        // when
        boolean result = fetc.hasChildren();

        // then
        assertFalse(result);
    }


}
