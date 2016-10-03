package eu.kaszkowiak.tomahawk.formatting;

import junit.framework.TestCase;

/**
 *
 * @author kan
 */
public class FormattingConfigurationTest extends TestCase {
    
    public FormattingConfigurationTest(String testName) {
        super(testName);
    }
    /**
     * Test of isIndented method, of class FormattingConfiguration.
     */
    public void testIsIndented() {
        FormattingConfiguration instance = new FormattingConfiguration();
        instance.setIndentationString("");
        assertFalse(instance.isIndented());
        instance.setIndentationString(null);
        assertFalse(instance.isIndented());
        instance.setIndentationString("\t");
        assertTrue(instance.isIndented());
    }
    
}
