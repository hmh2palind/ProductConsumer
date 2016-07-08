package com.sunil.file.reader;

import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The class reads configure file by format xml
 * 
 * @author HungHM5
 * 
 */
public class XMLDOMConfiguration {
	private static final Logger logger = Logger.getLogger(XMLDOMConfiguration.class);
	
	public static final String PROPERTY_NAME = "property";
	private static final String JOB_NAME = "QuartzJob";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	

	private static XMLDOMConfiguration instance;
	private Properties properties;

	private List<QuartzJobConfig> quartzJobsList;

	private XMLDOMConfiguration() {
		properties = new Properties();
		quartzJobsList = new ArrayList<QuartzJobConfig>();
	}

	/**
	 * Get instance of this singleton
	 * 
	 * @return
	 */
	public static XMLDOMConfiguration getInstance() {
		if (instance != null)
			return instance;

		synchronized (XMLDOMConfiguration.class) {
			if (instance == null) {
				instance = new XMLDOMConfiguration();
			}
		}

		return instance;
	} // getInstance()

	public List<QuartzJobConfig> getQuartzJobsList() {
		return quartzJobsList;
	} // getQuartzJobsList()

	
	/**
	 * @param configPath This path of configure file
	 * @return
	 */
	public boolean readConfigFile(String configPath) 
	{
		logger.debug("Verifying config file");
		File configFile = new File(configPath);
		if (!configFile.exists()) {
			logger.error("Config file " + configPath + " does not exist");
			return false;
		}
		if (!configFile.canRead()) {
			logger.error("No read access to the config file " + configPath);
			return false;
		}
		
		logger.debug("Reading config file");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					
			// documentBuilderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document configDoc = documentBuilder.parse(configFile);

			if (configDoc != null) {
				/*if (configDoc.getElementsByTagName("Property") != null) {
					logger.debug("Reading Properties");
					readProperties(configDoc.getElementsByTagName("Property"));
				}*/

				if (configDoc.getElementsByTagName(PROPERTY_NAME) != null) {
					logger.debug("Reading properties");
					readProperties(configDoc.getElementsByTagName(PROPERTY_NAME));
				}

				if (configDoc.getElementsByTagName(JOB_NAME) != null) {
					logger.debug("Reading Quartz jobs");
					readQuartzJobs(configDoc.getElementsByTagName(JOB_NAME));
				}
			} else {
				logger.error("Cannot read config file");
				return false;
			}

			return true;
		}
		catch (Exception ex) {
			logger.error("Exception parsing configuration file: " + ex.getMessage(), ex);
			return false;
		}
	} // readConfigFile(String configPath)

	
	/**
	 * read properties for node list
	 */
	private void readProperties(NodeList propertiesNodes) {
		if (propertiesNodes == null)
			return;

		for (int i = 0; i < propertiesNodes.getLength(); i++) {
			Node propertyNode = propertiesNodes.item(i);
			NodeList nameAndValueNodes = propertyNode.getChildNodes();

			String propertyName = getNodeValue(NAME, nameAndValueNodes);
			String propertyValue = getNodeValue(VALUE, nameAndValueNodes);
			if (propertyName != null && propertyValue != null)
				properties.put(propertyName, propertyValue);
		}
	} // readProperties(Properties prop, List propertiesList)

	
	/**
	 * @param read quartz jobs form jobs list
	 */
	private void readQuartzJobs(NodeList quartzJobsNodes) {
		if (quartzJobsNodes == null)
			return;

		int jobsCount = quartzJobsNodes.getLength();
		for (int i = 0; i < jobsCount; i++) {
			Node quartzJobNode = quartzJobsNodes.item(i);
			NodeList quartzJobParametersNodes = quartzJobNode.getChildNodes();
			if (quartzJobParametersNodes == null)
				continue;

			QuartzJobConfig quartzJobConfig = new QuartzJobConfig();
			quartzJobConfig.setName(getNodeValue("Name",		quartzJobParametersNodes));
			quartzJobConfig.setClassName(getNodeValue("Class",	quartzJobParametersNodes));
			quartzJobConfig.setSeconds(getNodeValue("Seconds", 	quartzJobParametersNodes));
			quartzJobConfig.setMinute(getNodeValue("Minute",	quartzJobParametersNodes));
			quartzJobConfig.setHour(getNodeValue("Hour",		quartzJobParametersNodes));
			quartzJobConfig.setDayOfMonth(getNodeValue("DayOfMonth",quartzJobParametersNodes));
			quartzJobConfig.setMonth(getNodeValue("Month",		quartzJobParametersNodes));
			quartzJobConfig.setDayOfWeek(getNodeValue("DayOfWeek",	quartzJobParametersNodes));
			
			if (quartzJobConfig.getName() != null
					&& quartzJobConfig.getClassName() != null
					&& quartzJobConfig.getSeconds() != null
					&& quartzJobConfig.getMinute() != null
					&& quartzJobConfig.getHour() != null
					&& quartzJobConfig.getDayOfMonth() != null
					&& quartzJobConfig.getMonth() != null
					&& quartzJobConfig.getDayOfWeek() != null) {
				quartzJobsList.add(quartzJobConfig);
			}

		} // for (int i = 0; i < quartzJobsNodes.getLength(); i++)
	} // readQuartzJobs(...)

	
	/**
	 * The method get value of node by tag name
	 */
	private static String getNodeValue(String tagName, NodeList nodes) {
		if (nodes == null)
			return null;
		
		int nodeLen = nodes.getLength();
		for (int i = 0; i < nodeLen; i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node data = childNodes.item(j);
					if (data.getNodeType() == Node.TEXT_NODE)
						return data.getNodeValue();
				}
			}
		}
		return null;
	} // getNodeValue(String tagName, NodeList nodes)

	/**
	 * Returns the value of the <code>name</code> property, or null if no such
	 * property exists.
	 */
	public Object getObject(String name) {
		return properties.get(name);
	}

	/** Sets the value of the <code>name</code> property. */
	public void setObject(String name, Object value) {
		properties.put(name, value);
	}

	/**
	 * Returns the value of the <code>name</code> property. If no such property
	 * exists, then <code>defaultValue</code> is returned.
	 */
	public Object get(String name, Object defaultValue) {
		Object res = getObject(name);
		if (res != null)
			return res;
		else
			return defaultValue;
	}

	/**
	 * Returns the value of the <code>name</code> property, or null if no such
	 * property exists.
	 */
	public String get(String name) {
		return properties.getProperty(name);
	}

	/**
	 * Returns the value of the <code>name</code> property. If no such property
	 * exists, then <code>defaultValue</code> is returned.
	 */
	public String get(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	/**
	 * Returns the value of the <code>name</code> property as an integer. If no
	 * such property is specified, or if the specified value is not a valid
	 * integer, then <code>defaultValue</code> is returned.
	 */
	public int getInt(String name, int defaultValue) {
		String valueString = get(name);
		if (valueString == null)
			return defaultValue;
		try {
			return Integer.parseInt(valueString);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns the value of the <code>name</code> property as a long. If no such
	 * property is specified, or if the specified value is not a valid long,
	 * then <code>defaultValue</code> is returned.
	 */
	public long getLong(String name, long defaultValue) {
		String valueString = get(name);
		if (valueString == null)
			return defaultValue;
		try {
			return Long.parseLong(valueString);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns the value of the <code>name</code> property as a float. If no
	 * such property is specified, or if the specified value is not a valid
	 * float, then <code>defaultValue</code> is returned.
	 */
	public float getFloat(String name, float defaultValue) {
		String valueString = get(name);
		if (valueString == null)
			return defaultValue;
		try {
			return Float.parseFloat(valueString);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns the value of the <code>name</code> property as an boolean. If no
	 * such property is specified, or if the specified value is not a valid
	 * boolean, then <code>defaultValue</code> is returned. Valid boolean values
	 * are "true" and "false".
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String valueString = get(name);
		if ("true".equalsIgnoreCase(valueString))
			return true;
		else if ("false".equalsIgnoreCase(valueString))
			return false;
		else
			return defaultValue;
	}

	/**
	 * Returns the value of the <code>name</code> property as a Class. If no
	 * such property is specified, then <code>defaultValue</code> is returned.
	 */
	@SuppressWarnings("rawtypes")
	public Class getClass(String name, Class defaultValue) {
		String valueString = get(name);
		if (valueString == null)
			return defaultValue;
		try {
			return Class.forName(valueString);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the value of the <code>name</code> property as a Class. If no
	 * such property is specified, then <code>defaultValue</code> is returned.
	 * An error is thrown if the returned class does not implement the named
	 * interface.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getClass(String propertyName, Class defaultValue, Class xface) {
		try {
			Class theClass = getClass(propertyName, defaultValue);
			if (theClass != null && !xface.isAssignableFrom(theClass))
				throw new RuntimeException(theClass + " not " + xface.getName());
			return theClass;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get List of { propertyName, propertyValue } string arrays
	 * 
	 * @return
	 */
	public List<String[]> getPropertiesStrings() {
		List<String[]> propertiesList = new ArrayList<String[]>();
		Enumeration<Object> e = properties.keys();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			Object object = properties.get(name);
			if (object instanceof String) {
				String value = (String) object;
				String[] nameValuePair = { name, value };
				propertiesList.add(nameValuePair);
			}
		}

		Collections.sort(propertiesList, new Comparator<String[]>() {
			public int compare(String[] nameValuePair1, String[] nameValuePair2) {
				return nameValuePair1[0].compareTo(nameValuePair2[0]);
			}
		});
		return propertiesList;
	} // getPropertiesStrings()

} // class ServerConfiguration