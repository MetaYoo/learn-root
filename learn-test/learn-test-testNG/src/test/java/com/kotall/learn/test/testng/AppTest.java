package com.kotall.learn.test.testng;

import junit.framework.TestCase;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    @DataProvider
    public Object[][] getData() {
        Map<String, Object> arg1 = new HashMap<>();
        arg1.put("a1", "a1");
        arg1.put("a2", "a2");

        Map<String, Object> arg2 = new HashMap<>();
        arg2.put("b1", "b1");
        arg2.put("b2", "b2");

        return new Object[][]{{arg1}, {arg2}};
    }

    @Test(dataProvider = "getData")
    public void test1(Map<String, Object> arg) {
        System.out.println("===== test1, arg=" + arg.get("a1") + ", a2=" + arg.get("a2"));
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("=====before method ");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("=====after method ");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("=====before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("=====after Class");
    }

}
