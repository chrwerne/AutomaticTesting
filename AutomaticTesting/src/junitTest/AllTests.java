package junitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LoginLogoutTest.class, CreateEligibilityChecklist.class, CreateMaxCallTest.class, ApplicationFormTest.class, Evaluation.class })
public class AllTests {
	
}
