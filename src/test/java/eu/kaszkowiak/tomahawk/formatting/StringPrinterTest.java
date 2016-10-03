/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.kaszkowiak.tomahawk.formatting;

import junit.framework.TestCase;

/**
 *
 * @author kan
 */
public class StringPrinterTest extends TestCase {
    
    private FormattingConfiguration fc = new FormattingConfiguration();

    public StringPrinterTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        fc.setIndentationString("x");
    }

    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of indent method, of class StringPrinter.
     */
    public void testIndentMultipleLinesWithTrailingEndline() {
        final String test = "abc\ndef\n\n";
        String result = StringPrinter.indent(test, fc, 1);
        assertEquals("xabc\nxdef\nx\n", result);
    }
    
    public void testIndentMultipleLinesWithoutTrailingEndline() {
        final String test = "abc\n\ndef";
        String result = StringPrinter.indent(test, fc, 1);
        assertEquals("xabc\nx\nxdef", result);
    }
    
    public void testIndentSingleLineWithTrailingEndline() {
        final String test = "abc\n";
        String result = StringPrinter.indent(test, fc, 3);
        assertEquals("xxxabc\n", result);
    }
    
    public void testIndentNoIndent() {
        fc.setIndentationString("");
        final String test = "abc\n";
        String result = StringPrinter.indent(test, fc, 3);
        assertEquals("abc\n", result);
        fc.setIndentationString(null);
        assertEquals("abc\n", result);
    }
    
    public void testGetIndentLevel3() {
        assertEquals("xxx", StringPrinter.getIndent(fc, 3));
    }
    
    public void testGetIndentLevel1() {
        FormattingConfiguration fc = new FormattingConfiguration();
        fc.setIndentationString("x");
        assertEquals("", StringPrinter.getIndent(fc, 0));
    }
    
    public void testIndent() {
        FormattingConfiguration fc = new FormattingConfiguration();
        fc.setIndentationString("x");
        assertEquals("x\n", StringPrinter.indent("\n", fc, 1));
    }
    
    public void testIndentNullString() {
        assertNull(StringPrinter.indent(null, new FormattingConfiguration(), 5));
        assertNull(StringPrinter.indent(null, new FormattingConfiguration(), 0));
        assertEquals("", StringPrinter.indent("", new FormattingConfiguration(), 0));
        assertEquals("", StringPrinter.indent("", new FormattingConfiguration(), 5));
    }

}
