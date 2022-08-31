package paystation.impl;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Test PayStation")
@SelectClasses(PaystationImplTest.class)
public class TestAll {
}
