package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AllTests.class)
@Suite.SuiteClasses({
        BaseTest.class,
})
public class AllTests {
}
