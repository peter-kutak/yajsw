/*******************************************************************************
 * Copyright  2015 rzorzorzo@users.sf.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.rzo.yajsw.config.jnlp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.vfs2.FileObject;
import org.rzo.yajsw.config.FilePropertiesConfiguration;
import org.rzo.yajsw.util.Utils;
import org.rzo.yajsw.util.VFSUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JnlpSupport
{
	Document _doc = null;

	public JnlpSupport(String file) throws ParserConfigurationException,
			SAXException, IOException
	{
		FileObject fo = VFSUtils.resolveFile(".", file);
		if (fo == null || !fo.exists())
			throw new FileNotFoundException(file);
		InputStream in = fo.getContent().getInputStream();
		_doc = parseJnlp(in);
		in.close();
	}

	public FilePropertiesConfiguration toConfiguration(String defaultsFile)
			throws ConfigurationException, IOException
	{
		int i = 1;
		FilePropertiesConfiguration jnlpConf = new FilePropertiesConfiguration();

		List jars = getJars(_doc);
		for (Iterator it = jars.listIterator(); it.hasNext();)
		{
			jnlpConf.setProperty("wrapper.java.classpath." + i++, it.next());
		}

		jnlpConf.setProperty("wrapper.base", getCodebase(_doc));

		jnlpConf.setProperty("wrapper.java.app.mainclass", getMainClass(_doc));

		i = 1;
		for (Iterator it = getArguments(_doc).listIterator(); it.hasNext();)
		{
			jnlpConf.setProperty("wrapper.app.parameter." + i++, it.next());
		}
		i = 1;
		List props = getResourceProperties(_doc);
		for (Iterator it = props.listIterator(); it.hasNext();)
		{
			jnlpConf.setProperty("wrapper.java.additional." + i++, it.next());
		}

		i = 1;
		List resources = getResources(_doc);
		for (Iterator it = resources.listIterator(); it.hasNext();)
		{
			jnlpConf.setProperty("wrapper.resource." + i++, it.next());
		}

		if (defaultsFile == null || "".equals(defaultsFile))
			return jnlpConf;

		// jnlpConf.addProperty("include", defaultsFile);

		if (defaultsFile != null)
		{
			PropertiesConfiguration defaultsConf = new PropertiesConfiguration();
			FileObject fo = VFSUtils.resolveFile(".", defaultsFile);
			InputStreamReader in = new InputStreamReader(fo.getContent().getInputStream());
			defaultsConf.read(in);
			in.close();
			for (Iterator it = defaultsConf.getKeys(); it.hasNext();)
			{
				String key = (String) it.next();
				if (jnlpConf.containsKey(key))
					System.out.println("configuration conflict: " + key);
				else
					jnlpConf.addProperty(key, defaultsConf.getProperty(key));
			}
		}

		return jnlpConf;

	}

	private Document parseJnlp(InputStream in)
			throws ParserConfigurationException, SAXException, IOException
	{
		Document doc = null;
		// Get Document Builder Factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// Turn off validation, and turn off namespaces
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		
		secureXml(factory);

		DocumentBuilder builder = factory.newDocumentBuilder();

		doc = builder.parse(in);

		return doc;
	}

	private void secureXml(DocumentBuilderFactory dbf) {
		String FEATURE = null;
		try {
		    // This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all
		    // XML entity attacks are prevented
		    // Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
		    FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
		    dbf.setFeature(FEATURE, true);

		    // If you can't completely disable DTDs, then at least do the following:
		    // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
		    // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
		    // JDK7+ - http://xml.org/sax/features/external-general-entities
		    //This feature has to be used together with the following one, otherwise it will not protect you from XXE for sure
		    FEATURE = "http://xml.org/sax/features/external-general-entities";
		    dbf.setFeature(FEATURE, false);

		    // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
		    // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
		    // JDK7+ - http://xml.org/sax/features/external-parameter-entities
		    //This feature has to be used together with the previous one, otherwise it will not protect you from XXE for sure
		    FEATURE = "http://xml.org/sax/features/external-parameter-entities";
		    dbf.setFeature(FEATURE, false);

		    // Disable external DTDs as well
		    FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
		    dbf.setFeature(FEATURE, false);

		    // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
		    dbf.setXIncludeAware(false);
		    dbf.setExpandEntityReferences(false);

		    // And, per Timothy Morgan: "If for some reason support for inline DOCTYPEs are a requirement, then
		    // ensure the entity settings are disabled (as shown above) and beware that SSRF attacks
		    // (http://cwe.mitre.org/data/definitions/918.html) and denial
		    // of service attacks (such as billion laughs or decompression bombs via "jar:") are a risk."

		} catch (ParserConfigurationException e) {
		    // This should catch a failed setFeature feature
		    System.out.println("ParserConfigurationException was thrown. The feature '" + FEATURE
		    + "' is probably not supported by your XML processor.");
		}
	}

	private static List getJars(Document doc)
	{
		ArrayList result = new ArrayList();
		NodeList args = doc.getElementsByTagName("jar");
		for (int i = 0; i < args.getLength(); i++)
		{
			result.add(args.item(i).getAttributes().getNamedItem("href")
					.getTextContent());
		}
		return result;
	}

	private static List getResources(Document doc)
	{
		ArrayList result = new ArrayList();
		NodeList args = doc.getElementsByTagName("icon");
		for (int i = 0; i < args.getLength(); i++)
		{
			result.add(args.item(i).getAttributes().getNamedItem("href")
					.getTextContent());
		}
		return result;
	}

	private static List getResourceProperties(Document doc)
	{
		ArrayList result = new ArrayList();
		NodeList args = doc.getElementsByTagName("property");
		for (int i = 0; i < args.getLength(); i++)
		{
			String key = args.item(i).getAttributes().getNamedItem("name")
					.getTextContent();
			String value = args.item(i).getAttributes().getNamedItem("value")
					.getTextContent();
			result.add(Utils.getDOption(key, value));
		}
		return result;
	}

	private static List getArguments(Document doc)
	{
		ArrayList result = new ArrayList();
		NodeList args = doc.getElementsByTagName("argument");
		for (int i = 0; i < args.getLength(); i++)
		{
			result.add(args.item(i).getTextContent());
		}
		return result;
	}

	private static Object getMainClass(Document doc)
	{
		return doc.getElementsByTagName("application-desc").item(0)
				.getAttributes().getNamedItem("main-class").getTextContent();
	}

	private static String getCodebase(Document doc)
	{
		return doc.getElementsByTagName("jnlp").item(0).getAttributes()
				.getNamedItem("codebase").getTextContent();
	}

}
