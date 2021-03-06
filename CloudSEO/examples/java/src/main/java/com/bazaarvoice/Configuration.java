package com.bazaarvoice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    static Properties _properties;
    protected static final Log _log = LogFactory.getLog(Configuration.class);

    protected Configuration() {

    }

    static {
        Properties classProperties = new Properties();
        try {
            classProperties.load(BazaarvoiceDisplayHelper.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            _log.error("Unable to find config.properties file in path.  Some required properties are not defined.");
            throw new RuntimeException(ex);
        }

        Properties clientProperties = new Properties();
        try {
            clientProperties.load(new FileInputStream("client.properties"));
        } catch (IOException ex) {
            _log.error("Unable to find client.properties file in path.  Some required properties are not defined.");
            throw new RuntimeException(ex);
        }
        _properties = new Properties();
        _properties.putAll(classProperties);
        _properties.putAll(clientProperties);
    }

    static public String get(String key) {
        return _properties.getProperty(key);
    }

    static public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
