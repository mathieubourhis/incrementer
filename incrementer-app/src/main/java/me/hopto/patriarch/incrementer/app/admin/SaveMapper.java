package me.hopto.patriarch.incrementer.app.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.hopto.patriarch.incrementer.app.data.Built;
import me.hopto.patriarch.incrementer.core.building.BerryPicker;
import me.hopto.patriarch.incrementer.core.building.BlackSmith;
import me.hopto.patriarch.incrementer.core.building.Building;
import me.hopto.patriarch.incrementer.core.building.BuildingType;
import me.hopto.patriarch.incrementer.core.building.FisherMan;
import me.hopto.patriarch.incrementer.core.building.Inventor;
import me.hopto.patriarch.incrementer.core.building.LumberJack;
import me.hopto.patriarch.incrementer.core.building.MetalDigger;
import me.hopto.patriarch.incrementer.core.building.Miner;
import me.hopto.patriarch.incrementer.core.building.WoodGatherer;
import me.hopto.patriarch.incrementer.core.resource.Food;
import me.hopto.patriarch.incrementer.core.resource.Metal;
import me.hopto.patriarch.incrementer.core.resource.Resource;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import me.hopto.patriarch.incrementer.core.resource.Tool;
import me.hopto.patriarch.incrementer.core.resource.Wood;

public class SaveMapper {

	public Save toSave(Built game) {
		Map<String, String> saveResources = new HashMap<String, String>();
		for (ResourceType resourceType : ResourceType.values()) {
			saveResources.put(resourceType.name(), game.getFormattedResourceQuantity(resourceType));
		}

		Map<String, String> saveBuildings = new HashMap<String, String>();
		for (BuildingType buildingType : BuildingType.values()) {
			saveBuildings.put(buildingType.name(), game.getLevelForBuilding(buildingType));
		}
		return new Save(VersionProvider.getVersion(), saveResources, saveBuildings);
	}

	/**
	 * I have to manually map. In case I choose to add / remove stuff from the game.<BR>
	 * I can only keep what I know has to be here in this version of the game.<BR>
	 * Translate what I know has to be translated, Throw the rest.<BR>
	 * 
	 * @param save
	 * @return
	 */
	public Built toGame(Save save) {
		Map<String, String> saveResources = save.getSaveResources();
		Map<String, String> saveBuildings = save.getSaveBuildings();

		Resource food = mapFood(saveResources);
		Resource wood = mapWood(saveResources);
		Resource metal = mapMetal(saveResources);
		Resource tool = mapTool(saveResources);

		List<Resource> resources = new ArrayList<Resource>();
		resources.add(food);
		resources.add(wood);
		resources.add(metal);
		resources.add(tool);

		List<Building> buildings = new ArrayList<Building>();
		buildings.add(mapBerryPicker(saveBuildings));
		buildings.add(mapFisherMan(saveBuildings));
		buildings.add(mapWoodGatherer(saveBuildings));
		buildings.add(mapLumberJack(saveBuildings));
		buildings.add(mapMetalDigger(saveBuildings));
		buildings.add(mapMiner(saveBuildings));
		buildings.add(mapInventor(saveBuildings));
		buildings.add(mapBlackSmith(saveBuildings));

		for (Building building : buildings) {
			food.updateIncrementValue(building.findFormulaForResource(ResourceType.Food).getGlobalIncrementForLevel(building.getLevel()));
			wood.updateIncrementValue(building.findFormulaForResource(ResourceType.Wood).getGlobalIncrementForLevel(building.getLevel()));
			metal.updateIncrementValue(building.findFormulaForResource(ResourceType.Metal).getGlobalIncrementForLevel(building.getLevel()));
			tool.updateIncrementValue(building.findFormulaForResource(ResourceType.Tool).getGlobalIncrementForLevel(building.getLevel()));
		}

		return new Built(resources, buildings);
	}

	private Resource mapFood(Map<String, String> saveResources) {
		String savedQuantity = saveResources != null ? saveResources.get(ResourceType.Food.name()) : null;
		// savedQuantity = saveResources == null ? saveResources.get("oldName") : saveResources;
		double mappedQuantity = 0.0d;
		if (savedQuantity != null) {
			mappedQuantity = Double.parseDouble(savedQuantity);
		}
		return new Food(mappedQuantity, 0.0d);
	}

	private Resource mapWood(Map<String, String> saveResources) {
		String savedQuantity = saveResources != null ? saveResources.get(ResourceType.Wood.name()) : null;
		double mappedQuantity = 0.0d;
		if (savedQuantity != null) {
			mappedQuantity = Double.parseDouble(savedQuantity);
		}
		return new Wood(mappedQuantity, 0.0d);
	}

	private Resource mapMetal(Map<String, String> saveResources) {
		String savedQuantity = saveResources != null ? saveResources.get(ResourceType.Metal.name()) : null;
		double mappedQuantity = 0.0d;
		if (savedQuantity != null) {
			mappedQuantity = Double.parseDouble(savedQuantity);
		}
		return new Metal(mappedQuantity, 0.0d);
	}

	private Resource mapTool(Map<String, String> saveResources) {
		String savedQuantity = saveResources != null ? saveResources.get(ResourceType.Tool.name()) : null;
		double mappedQuantity = 0.0d;
		if (savedQuantity != null) {
			mappedQuantity = Double.parseDouble(savedQuantity);
		}
		return new Tool(mappedQuantity, 0.0d);
	}

	private Building mapBerryPicker(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.BerryPicker.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new BerryPicker(mappedLevel);
	}

	private Building mapFisherMan(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.FisherMan.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new FisherMan(mappedLevel);
	}

	private Building mapWoodGatherer(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.WoodGatherer.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new WoodGatherer(mappedLevel);
	}

	private Building mapLumberJack(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.LumberJack.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new LumberJack(mappedLevel);
	}

	private Building mapMetalDigger(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.MetalDigger.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new MetalDigger(mappedLevel);
	}

	private Building mapMiner(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.Miner.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new Miner(mappedLevel);
	}

	private Building mapInventor(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.Inventor.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new Inventor(mappedLevel);
	}

	private Building mapBlackSmith(Map<String, String> saveBuildings) {
		String savedLevel = saveBuildings != null ? saveBuildings.get(BuildingType.BlackSmith.name()) : null;
		int mappedLevel = 0;
		if (savedLevel != null) {
			mappedLevel = Integer.parseInt(savedLevel);
		}
		return new BlackSmith(mappedLevel);
	}
}
