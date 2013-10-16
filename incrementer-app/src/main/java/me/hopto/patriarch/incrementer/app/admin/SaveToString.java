package me.hopto.patriarch.incrementer.app.admin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;
import biz.source_code.base64Coder.Base64Coder;

public class SaveToString implements SavingBehaviour {
	private static Logger	logger	= Logger.getLogger(SaveToString.class);
	private String				save;

	public SaveToString(String save) {
		this.save = save;
	}

	public SaveToString() {
		this(null);
	}

	@Override
	public Save load() {
		Save o = null;
		try {
			// Decode
			byte[] data = Base64Coder.decode(save);
			// Serialize
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			o = (Save) ois.readObject();
			ois.close();
			// Notify event "GameLoaded" 
			// Maybe store save within event ?
			if (logger.isInfoEnabled()) logger.info("Game loaded");
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Could'not load the game");
			// Notify event + exception "GameNotLoaded"
		}
		return o;
	}

	@Override
	public String save(Save o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
			save = new String(Base64Coder.encode(baos.toByteArray()));
			// Notify event "GameSaved" 
			// Maybe store save within event ?
			if (logger.isInfoEnabled()) logger.info("Game saved");
		} catch (IOException e1) {
			logger.error("Could'not save the game");
			// Notify event + exception "GameNotSaved"
		}
		return save;
	}

}
