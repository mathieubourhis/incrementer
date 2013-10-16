package me.hopto.patriarch.incrementer.app.admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.log4j.Logger;
import biz.source_code.base64Coder.Base64Coder;

public class SaveToFile implements SavingBehaviour {
	private static Logger	logger	= Logger.getLogger(SaveToFile.class);
	private String				save;
	private final String	path;

	public SaveToFile(String path) {
		this.path = path;
	}

	@Override
	public Save load() {
		Save o = null;
		try {
			//Read from file
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			Charset encoding = Charset.defaultCharset();
			save = encoding.decode(ByteBuffer.wrap(encoded)).toString();
			// Decode
			byte[] data = Base64Coder.decode(save);
			// Serialize
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			o = (Save) ois.readObject();
			ois.close();
			// Notify event "GameLoaded" 
			// Maybe store save within event ?
			if (logger.isInfoEnabled()) logger.info("Game loaded");
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Could'not load the game");
			// Notify event + exception "GameNotLoaded"
		}
		return o;
	}

	@Override
	public String save(Save o) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
			save = new String(Base64Coder.encode(baos.toByteArray()));

			FileWriter fileWriter = new FileWriter(new File(path));
			fileWriter.write(save);
			fileWriter.close();
			// Notify event "GameSaved" 
			// Maybe store save within event ?
			if (logger.isInfoEnabled()) logger.info("Game saved");
		} catch (IOException e) {
			logger.error("Could'not save the game");
			// Notify event + exception "GameNotSaved"
		}
		return save;
	}

}
