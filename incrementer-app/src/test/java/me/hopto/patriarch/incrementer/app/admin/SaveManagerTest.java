package me.hopto.patriarch.incrementer.app.admin;

import static org.assertj.core.api.Assertions.assertThat;
import me.hopto.patriarch.incrementer.app.data.Built;
import me.hopto.patriarch.incrementer.core.building.BuildingType;
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
public class SaveManagerTest {

	@Rule
	public TestName				name		= new TestName();
	private static Logger	logger	= Logger.getLogger(SaveManagerTest.class);
	private Built					built;
	private SaveManager		saveManager;

	@Before
	public void setup() {
		saveManager = new SaveManager();
		built = new Built();
		for (int i = 0; i < 15; i++)
			built.incrementOne(ResourceType.Food);
		built.levelUp(BuildingType.BerryPicker);
		built.incrementAll(100.0d);
		if (logger.isDebugEnabled()) logger.debug("[BEGIN] " + name.getMethodName());
	}

	@After
	public void tearDown() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("[ END ] " + name.getMethodName());
		}
	}

	@Test
	public void checkSavingManager() {
		// Setup

		// Test
		Save saveGame = saveManager.gameToMap(built);
		String save = saveManager.save(saveGame);

		Save loadedGame = saveManager.load(save);

		// Assert
		if (logger.isDebugEnabled()) logger.debug(saveGame);
		if (logger.isDebugEnabled()) logger.debug(loadedGame);
		assertThat(loadedGame).isEqualTo(saveGame);
	}
}
