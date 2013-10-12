package me.hopto.patriarch.incrementer.app.admin;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import java.io.Serializable;
import java.util.Map;

class Save implements Serializable {
	private static final long					serialVersionUID	= -1852577157467689714L;

	private final Map<String, String>	saveParameters;

	Save(Map<String, String> saveParameters) {
		this.saveParameters = saveParameters;
	}

	Map<String, String> getSaveParameters() {
		return saveParameters;
	}

	/**
	 * Using Guava to compare provided object to me for equality.
	 * 
	 * @param obj Object to be compared to me for equality.
	 * @return {@code true} if provided object is considered equal to me or {@code false} if provided object is not considered equal to me.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		//     final Save other = (Save) obj;

		return equal(this.saveParameters, ((Save) obj).saveParameters);
	}

	/**
	 * Uses Guava to assist in providing hash code of this employee instance.
	 * 
	 * @return My hash code.
	 */
	@Override
	public int hashCode() {
		//		return hashCode(this.saveParameters);
		return this.saveParameters.hashCode();
	}

	/**
	 * Method using Guava to provide String representation of this employee instance.
	 * 
	 * @return My String representation.
	 */
	@Override
	public String toString() {
		return toStringHelper(this).addValue(this.saveParameters).toString();
	}
}
