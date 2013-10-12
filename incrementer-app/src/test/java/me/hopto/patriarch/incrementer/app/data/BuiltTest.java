package me.hopto.patriarch.incrementer.app.data;

import static org.assertj.core.api.Assertions.assertThat;
import me.hopto.patriarch.incrementer.app.calculator.FormulaWrapperTest;
import me.hopto.patriarch.incrementer.core.building.Building;
import me.hopto.patriarch.incrementer.core.building.BuildingType;
import me.hopto.patriarch.incrementer.core.formula.FormulaWrapper;
import me.hopto.patriarch.incrementer.core.resource.Resource;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuiltTest {

	@Rule
	public TestName				name		= new TestName();
	private static Logger	logger	= Logger.getLogger(FormulaWrapperTest.class);
	private Built					built;

	@Before
	public void setup() {
		if (logger.isDebugEnabled()) {
			logger.debug("[BEGIN] " + name.getMethodName());
		}
		built = new Built();
	}

	@After
	public void tearDown() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("[ END ] " + name.getMethodName());
		}
	}

	@Test
	public void resourceTypeTest() {

		// Coupled with the next test, makes sure I only have one of each
		// buildings
		assertThat(built.buildings).hasSize(BuildingType.values().length);
		for (BuildingType buildingType : BuildingType.values()) {

			// Checking if i didn't forget buildings
			Building building = built.findBuildingByType(buildingType);
			assertThat(building).isNotNull();

			int nbResourcesProduced = 0;
			// Checking if i didn't missput formulas, one by resource
			for (ResourceType resourceType : ResourceType.values()) {
				FormulaWrapper formulaWrapper = building.findFormulaForResource(resourceType);
				assertThat(formulaWrapper).isNotNull();
				if (formulaWrapper.getNextIncrement() > 0.0d) nbResourcesProduced++;
			}
			// One and only one resource produced by building
			assertThat(nbResourcesProduced).isEqualTo(1);
			if (logger.isDebugEnabled()) logger.debug(buildingType.name() + " - All Formulas OK");
		}
		if (logger.isDebugEnabled()) logger.debug("Only one of each Building OK");
		// Coupled with the next test, makes sure I only have one of each
		// resources
		assertThat(built.resources).hasSize(ResourceType.values().length);
		for (ResourceType resourceType : ResourceType.values()) {
			// Checking if i didn't forget buildings
			Resource resource = built.findResourceByType(resourceType);
			assertThat(resource).isNotNull();
		}

		if (logger.isDebugEnabled()) logger.debug("Only one of each Resource OK");
	}
}
