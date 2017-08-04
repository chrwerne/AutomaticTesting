package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Properties {
	

	private Logger logger = LoggerFactory.getLogger(Properties.class);
	private String propertyFilename;
	private java.util.Properties prop;
	
	public Properties(String className){
		try{
			propertyFilename = className+".xml";
			
			prop = new java.util.Properties();
			FileInputStream fileInput = new FileInputStream(new File(propertyFilename));
			prop.loadFromXML(fileInput);	
		}catch(FileNotFoundException ex){
			 logger.error("File <"+propertyFilename+"> not found", ex);
		}catch(IOException ex){
			logger.error("IOException when reading file <"+propertyFilename+">", ex);
		}
		
		logger.debug("Finished reading file <"+propertyFilename+">");	
	}
	
		
	public String getValueOfProperty(String propertyName){
		return prop.getProperty(propertyName);
	}
}
