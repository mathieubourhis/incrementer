package me.hopto.patriarch.incrementer.app.admin;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import java.io.Serializable;
import java.util.Map;
import com.google.common.base.Objects;

/**
 * This class used to represent a save. <br>
 * The save may evolve. The game should still be able to load it.
 */
class Save implements Serializable {
	private static final long					serialVersionUID	= -1852577157467689714L;

	/** The version of the game this save has been saved in. */
	private final String							saveVersion;
	/** The state of the game's resources. */
	private final Map<String, String>	saveResources;
	/** The state of the game's buildings. */
	private final Map<String, String>	saveBuildings;

	Save(String saveVersion, Map<String, String> saveResources, Map<String, String> saveBuildings) {
		this.saveVersion = saveVersion;
		this.saveResources = saveResources;
		this.saveBuildings = saveBuildings;
	}

	/** @return the saveVersion */
	public String getSaveVersion() {
		return saveVersion;
	}

	/** @return the saveResources */
	public Map<String, String> getSaveResources() {
		return saveResources;
	}

	/** @return the saveBuildings */
	public Map<String, String> getSaveBuildings() {
		return saveBuildings;
	}

	/**
	 * @param obj Object to be compared to me for equality.
	 * @return {@code true} if provided object is considered equal to me or {@code false} if provided object is not considered equal to me.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		//     final Save other = (Save) obj;

		return equal(this.saveVersion, ((Save) obj).saveVersion) && equal(this.saveResources, ((Save) obj).saveResources) && equal(this.saveBuildings, ((Save) obj).saveBuildings);
	}

	/** @return basic HashCode. */
	@Override
	public int hashCode() {
		//		return hashCode(this.saveParameters);
		return Objects.hashCode(this.saveVersion, this.saveResources, this.saveBuildings);
	}

	/** @return basic toString. */
	@Override
	public String toString() {
		return toStringHelper(this).addValue(this.saveVersion).addValue(this.saveResources).addValue(this.saveBuildings).toString();
	}
}
