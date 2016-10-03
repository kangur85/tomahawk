package eu.kaszkowiak.tomahawk.model.directives;

import eu.kaszkowiak.tomahawk.formatting.FormattingConfiguration;
import eu.kaszkowiak.tomahawk.model.formatting.BlankLine;
import eu.kaszkowiak.tomahawk.model.formatting.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author kan
 */
public class TagDirectiveTest extends TestCase {

    private TagDirective tag;
    private TagDirective tagBL;
    private Map<String, String> replaceMap;

    public TagDirectiveTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tag = new TagDirective();
        SimpleDirective directive = new SimpleDirective();
        tag.addChildEntry(new Comment("# abc"));
        directive.setName("test");
        directive.addParam("${c}");
        directive.addParam("$b");
        directive.addParam("$a");
        tag.addChildEntry(directive);
        tag.addChildEntry(new Comment("# abc"));
        tag.addChildEntry(new Comment("# def"));
        tag.setName("Macro");
        tag.setEndName("Macro");
        tag.addParam("macroName");
        tag.addParam("$a");
        tag.addParam("$b");
        tag.addParam("${c}");

        tagBL = new TagDirective();
        SimpleDirective directiveBL = new SimpleDirective();
        tagBL.addChildEntry(new BlankLine());
        tagBL.addChildEntry(new Comment("# abc"));
        directiveBL.setName("test");
        directiveBL.addParam("${c}");
        directiveBL.addParam("$b");
        directiveBL.addParam("$a");
        tagBL.addChildEntry(directiveBL);
        tagBL.addChildEntry(new BlankLine());
        tagBL.addChildEntry(new Comment("# abc"));
        tagBL.addChildEntry(new Comment("# def"));
        tagBL.setName("Macro");
        tagBL.setEndName("Macro");
        tagBL.addParam("macroName");
        tagBL.addParam("$a");
        tagBL.addParam("$b");
        tagBL.addParam("${c}");

        replaceMap = new HashMap();
        replaceMap.put("$a", "1");
        replaceMap.put("$b", "2");
        replaceMap.put("${c}", "3");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testToString() {
        final String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "\t# abc\n"
                + "\ttest ${c} $b $a\n"
                + "\t# abc\n"
                + "\t# def\n"
                + "</Macro>";
        assertEquals(expected, tag.toString());
    }

    public void testReplaceAll() {


        String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "\t# abc\n"
                + "\ttest 3 2 1\n"
                + "\t# abc\n"
                + "\t# def\n"
                + "</Macro>";

        tag.replaceAll(replaceMap);

        assertEquals(expected, tag.toString());

        FormattingConfiguration fc = new FormattingConfiguration();
        fc.setPrintComments(false)
                .setPrintEmptyLines(false);

        expected
                = "<Macro macroName $a $b ${c}>\n"
                + "\ttest 3 2 1\n"
                + "</Macro>";

        assertEquals(expected, tag.toString(fc));
    }

    public void testReplaceAllBlankLineFirst() {
        tagBL.replaceAll(replaceMap);

        FormattingConfiguration fc = new FormattingConfiguration();
        fc.setPrintComments(false)
          .setPrintEmptyLines(false);

        String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "\ttest 3 2 1\n"
                + "</Macro>";

        assertEquals(expected, tagBL.toString(fc));
    }

    public void testReplaceAllBlankLine() {
        tagBL.replaceAll(replaceMap);

        FormattingConfiguration fc = new FormattingConfiguration();
        fc.setPrintComments(false)
          .setPrintEmptyLines(true);

        String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "\t\n"
                + "\ttest 3 2 1\n"
                + "\t\n"
                + "</Macro>";

        assertEquals(expected, tagBL.toString(fc));
    }

    public void testToStringNoChildren() {
        tag.setChildren(null);

        final String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "</Macro>";

        tag.replaceAll(null);

        assertEquals(expected, tag.toString());
    }

     public void testToStringBlankChildren() {
        tagBL.setChildren(new ArrayList());
        tagBL.addChildEntry(new BlankLine());

        final String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "</Macro>";

        tagBL.replaceAll(null);

        FormattingConfiguration fc = new FormattingConfiguration();
        fc.setPrintEmptyLines(false);

        assertEquals(expected, tagBL.toString(fc));
    }

    public void testToStringBlankLineOnly() {
        tag.setChildren(new ArrayList());
        tag.addChildEntry(new BlankLine());

        final String expected
                = "<Macro macroName $a $b ${c}>\n"
                + "\t\n"
                + "</Macro>";

        tag.replaceAll(null);

        assertEquals(expected, tag.toString());
    }

}
