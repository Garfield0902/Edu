package com.edu.cache.constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Constants {
	private static final Log log = LogFactory.getLog(Constants.class);
	public static String CacheXml="cacheManage.xml";
    
    /**
     * 配置xml文件路劲
     */
    public static String cacheXmlPath = null;
	
    static{
        if (null == Constants.class.getClassLoader().getResource("/")) {
            cacheXmlPath= Constants.class.getClassLoader().getResource("").getPath()+CacheXml;
        } else {
            cacheXmlPath= Constants.class.getClassLoader().getResource("/").getPath()+CacheXml;
        }
    	if(cacheXmlPath!=null&&!"".equals(cacheXmlPath)){
    	    String os = System.getProperty("os.name");  
    	    if(os.toLowerCase().startsWith("win") && cacheXmlPath.startsWith("/")){  
    	        cacheXmlPath = cacheXmlPath.substring(1);
    	    }
    	}
    	log.info("cacheXmlPath "+cacheXmlPath);
    }
    
}

