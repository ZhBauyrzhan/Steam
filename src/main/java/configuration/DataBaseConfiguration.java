package configuration;

import com.j256.ormlite.support.ConnectionSource;

public interface DataBaseConfiguration {
	ConnectionSource connectionSource();
}