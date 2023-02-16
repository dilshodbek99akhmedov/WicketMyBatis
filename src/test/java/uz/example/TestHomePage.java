package uz.example;

import junit.framework.TestCase;
import org.apache.wicket.util.tester.WicketTester;
import uz.example.view.HomePage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage extends TestCase {
    private WicketTester tester;

    public void setUp() {
        tester = new WicketTester();
    }

    public void testRenderMyPage() {
        //start and render the test page
        tester.startPage(HomePage.class);

        //assert rendered page class
        tester.assertRenderedPage(HomePage.class);

        //assert rendered label component
        tester.assertLabel("message", "If you see this message wicket is properly configured and running");
    }
}