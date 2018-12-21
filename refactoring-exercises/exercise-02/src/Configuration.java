import java.util.*;

public class Configuration {
	public int interval;

	public int duration;

	public int departure;

	//bed smells:
	//long method
	//code duplication
	private int loadInterval(Properties props) throws ConfigurationException {
		return loadPropertyValue(props, "interval");
	}


	private int loadDuration(Properties props) throws ConfigurationException {
		int duration = loadPropertyValue(props, "duration");
		if ((duration % interval) != 0) {
			throw new ConfigurationException("duration % interval");
		}
		return duration;
	}

	private int loadDepature(Properties props) throws ConfigurationException {
		int departure = loadPropertyValue(props, "departure");
		if ((departure % interval) != 0) {
			throw new ConfigurationException("departure % interval");
		}
		return departure;
	}

	private int loadPropertyValue(Properties props, String propName) throws ConfigurationException {
		String valueString;
		int value;

		valueString = props.getProperty(propName);
		if (valueString == null) {
			throw new ConfigurationException(propName + " interval");
		}
		value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new ConfigurationException("monitor " + propName + " > 0");
		}
		return value;
	}

	public void load(Properties props) throws ConfigurationException {
		interval = loadInterval(props);
		duration = loadDuration(props);
		departure = loadDepature(props);
	}
}
