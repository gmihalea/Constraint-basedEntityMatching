package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test Runner for Matcher class.
 */
public class MatcherTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MatcherTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
