package me.hopto.patriarch.incrementer.web;

import static org.assertj.core.api.Assertions.assertThat;
import org.apache.log4j.Logger;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IncrementerTest {

	@Rule
	public TestName							name		= new TestName();
	private static Logger				logger	= Logger.getLogger(IncrementerTest.class);
	private static WicketTester	wicketTester;

	@BeforeClass
	public static void setUpBeforeClass() {
		wicketTester = new WicketTester();
	}

	@Before
	public void setUp() {
		if (logger.isDebugEnabled()) {
			logger.debug("[BEGIN] " + name.getMethodName());
		}
		wicketTester.startPage(Incrementer.class);
	}

	@After
	public void tearDown() {
		if (logger.isDebugEnabled()) {
			logger.debug("[ END ] " + name.getMethodName());
		}
	}

	@Test
	public void testIncrementer() {
		wicketTester.assertRenderedPage(Incrementer.class);
		logger.debug(wicketTester.getApplication().getHomePage());
		assertThat(wicketTester.getTagByWicketId("BerryPickerLevel").getValue()).isNotNull().isEqualTo("0");

		// wicketTester.startPanel(SomePanel.class);
		// DropDownChoice dChoice = (DropDownChoice)
		// wicketTester.getComponentFromLastRenderedPage("panel:department");
		// assertThat(dChoice.getChoices()).hasSize(3);
	}

}
