package com.edu.cache.util;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.edu.cache.service.impl.PullDataToCacheImpl;

public class XmlFileOpe {
	private static Document doc;
	private static final Log log = LogFactory.getLog(XmlFileOpe.class);
	
	public static Document loadFileByContent(String fileContent)
			throws DocumentException {
		doc = DocumentHelper.parseText(fileContent);
		return doc;
	}

	public static Document loadFileByPath(String FilePath)
			throws DocumentException {
		File file = new File(FilePath);
		if(file.exists()){
			SAXReader reader = new SAXReader();
			doc = reader.read(file); // 读取一个xml的文件
		}else{
			log.info(FilePath+ " xml文件不存在!");
		}
		return doc;
	}

	public static Document loadFile(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		doc = reader.read(file); // 读取一个xml的文件
		return doc;
	}

	public static List selectNodes(Node node, String tiaojian) {
		List<Node> nodelist = node.selectNodes(tiaojian);
		return nodelist;
	}

	public static Element rootElement(Document doc) {
		Element ele = doc.getRootElement();
		return ele;
	}

	public static Element element(Element m, String name) {
		Element ele = m.element(name);
		return ele;
	}

	public static List<Element> elements(Element m, String name) {
		List<Element> elist = m.elements(name);
		return elist;
	}

	public static List<Element> elements(Element m) {
		List<Element> elist = m.elements();
		return elist;
	}

	public static String attributeValue(Element m, String attriName) {
		String val = m.attributeValue(attriName);
		return val;
	}

	public static List<String> attributeValues(Element m) {
		List<String> vals = m.attributes();
		return vals;
	}

	public static void main(String[] args) {
		try {
			Document doc = XmlFileOpe
					.loadFileByPath("E:\\workspace_work\\netbox-doc\\BCP\\导出实例\\145-748836377-410100-410100-1462938002-10000\\GAB_ZIP_INDEX.xml");
			Element e = XmlFileOpe.rootElement(doc);
			// List<Element> list = XmlFileOpe.elements(e);
			Element node = (Element) e
					.selectSingleNode("/MESSAGE/DATASET/DATA/DATASET[@name='WA_COMMON_010013']/DATA");
			// List<Element> list = XmlFileOpe.selectNodes(doc,
			// "/MESSAGE/DATASET/DATA/DATASET[@name='WA_COMMON_010013']");
			System.out.println(((Element) node
					.selectSingleNode("ITEM[@key='I010032']"))
					.attributeValue("key"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
