package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.model.formatting.InlineComment;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author kan
 */
public class DirectiveTest extends TestCase {

    public DirectiveTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getInlineComment method, of class Directive.
     */
    public void testGetInlineComment() {
        Directive instance = new DirectiveImpl();
        InlineComment x = new InlineComment("test");
        instance.setInlineComment(x);
        InlineComment expResult = x;
        InlineComment result = instance.getInlineComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasInlineComment method, of class Directive.
     */
    public void testHasInlineComment() {
        Directive instance = new DirectiveImpl();
        assertFalse(instance.hasInlineComment());
        instance.setInlineComment(new InlineComment("test"));
        assertTrue(instance.hasInlineComment());
    }

    public class DirectiveImpl extends Directive {

        @Override
        public String toString(FormattingConfiguration fc, int indentLevel) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void replaceAll(Map<String, String> map) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


    }

}
