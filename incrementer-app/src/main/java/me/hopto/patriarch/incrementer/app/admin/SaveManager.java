package me.hopto.patriarch.incrementer.app.admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import me.hopto.patriarch.incrementer.app.data.Built;
import me.hopto.patriarch.incrementer.core.building.BuildingType;
import me.hopto.patriarch.incrementer.core.resource.ResourceType;
import biz.source_code.base64Coder.Base64Coder;

public class SaveManager {
	public Save load(String save) {
		Save saveGame = null;
		try {
			saveGame = (Save) fromString(save);
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return saveGame;
	}

	public Save gameToMap(Built game) {
		Map<String, String> saveParameters = new HashMap<String, String>();
		for (ResourceType resourceType : ResourceType.values()) {
			saveParameters.put(resourceType.name(), game.getFormattedResourceQuantity(resourceType));
		}

		for (BuildingType buildingType : BuildingType.values()) {
			saveParameters.put(buildingType.name(), game.getLevelForBuilding(buildingType));
		}
		return new Save(saveParameters);
	}

	public String save(Save game) {
		String save = null;
		try {
			save = toString(game);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return save;
	}

	/** Read the object from Base64 string. */
	private static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64Coder.decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}

	/** Write the object to a Base64 string. */
	private static String toString(Serializable o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		return new String(Base64Coder.encode(baos.toByteArray()));
	}
}
