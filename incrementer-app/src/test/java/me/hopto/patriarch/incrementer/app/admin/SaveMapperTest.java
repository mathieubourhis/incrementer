package me.hopto.patriarch.incrementer.app.admin;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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
public class SaveMapperTest {

	@Rule
	public TestName				name		= new TestName();
	private static Logger	logger	= Logger.getLogger(SaveMapperTest.class);
	private Built					built;
	private SaveManager		saveManager;
	private SaveMapper		saveMapper;

	@Before
	public void setup() {
		saveManager = new SaveManager();
		saveMapper = new SaveMapper();
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
	public void checkSavingMapper() {
		// Setup

		// Test
		Save saveGame = saveMapper.toSave(built);
		String save = saveManager.save(saveGame);

		Save loadedGame = saveManager.load(save);
		Built loadedBuilt = saveMapper.toGame(loadedGame);

		// Assert
		if (logger.isDebugEnabled()) logger.debug(saveGame);
		if (logger.isDebugEnabled()) logger.debug(save);
		if (logger.isDebugEnabled()) logger.debug(loadedGame);
		if (logger.isDebugEnabled()) logger.debug(built);
		if (logger.isDebugEnabled()) logger.debug(loadedBuilt);

		assertThat(loadedGame).isEqualTo(saveGame);
		assertThat(loadedBuilt).isEqualTo(built);
	}

	@Test
	public void checkPreviousSaves() {
		// Setup

		// Test
		File saveDir = new File("src/test/resources/saves");
		if (saveDir.exists() && saveDir.isDirectory()) {
			File[] listFiles = saveDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("save-");
				}

			});
			for (File saveFile : listFiles) {
				if (logger.isDebugEnabled()) logger.debug(saveFile.getPath());
				try {
					Save saveGameFromFile = (Save) SaveManager.fromFile(saveFile.getPath());
					if (logger.isDebugEnabled()) logger.debug(saveGameFromFile);
					Built loadedBuilt = saveMapper.toGame(saveGameFromFile);
					// Assert
					assertThat(loadedBuilt).isNotNull();
					if (logger.isDebugEnabled()) logger.debug(loadedBuilt);
				} catch (ClassNotFoundException | IOException e) {
					logger.error("Can't load save : " + saveFile.getName());
				}
			}
		}
	}
}
