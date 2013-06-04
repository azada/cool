package cool.tester;

import beaver.Parser;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import cool.MyCoolParser;
/**
 * Created with IntelliJ IDEA.
 * User: pedram
 * Date: 4/22/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParserTester extends TestCase {
    public void testClassDef() {
        System.out.println("ParserTester.testClassDef");
        try {
            FileInputStream file = new FileInputStream("cool/examples/TestVector.cool");
            MyCoolParser parser = new MyCoolParser(file);
            parser.parse2();
            Assert.assertTrue(true);

        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Parser.Exception e ) {
            System.out.println("e = " + e);
            Assert.assertTrue(false);
        }
    }

    public void testPairDef() {
        System.out.println("ParserTester.testPairDef");
        try {
            FileInputStream file = new FileInputStream("cool/examples/Pair.cool");
            MyCoolParser parser = new MyCoolParser(file);
            parser.parse2();
            Assert.assertTrue(true);

        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Parser.Exception e ) {
            System.out.println("e = " + e);
            Assert.assertTrue(false);
        }

    }
    public void testGFX() {
        System.out.println("ParserTester.testGFX");
        try {
            FileInputStream file = new FileInputStream("cool/examples/gfx.cool");
            MyCoolParser parser = new MyCoolParser(file);
            parser.parse2();
            Assert.assertTrue(true);

        } catch (FileNotFoundException e) {
            Assert.assertTrue(false);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Parser.Exception e ) {
            System.out.println("e = " + e);
            Assert.assertTrue(false);
        }

    }
}
