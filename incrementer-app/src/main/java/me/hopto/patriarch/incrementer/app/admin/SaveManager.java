package me.hopto.patriarch.incrementer.app.admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	static Object fromString(String s) throws IOException, ClassNotFoundException {
		byte[] data = Base64Coder.decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;
	}

	/** Write the object to a Base64 string. */
	static String toString(Serializable o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		return new String(Base64Coder.encode(baos.toByteArray()));
	}

	/** Reads the object from Base64 string within a file. */
	static Object fromFile(String path) throws IOException, ClassNotFoundException {
		return fromString(readFile(path, Charset.defaultCharset()));
	}

	/** Reads a file. */
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	/** Writes the object to a Base64 string in a file. */
	static void toFile(Serializable o, String path) throws IOException {
		FileWriter fileWriter = new FileWriter(new File(path));
		String encodedSave = toString(o);
		fileWriter.write(encodedSave);
		fileWriter.close();
	}
}
