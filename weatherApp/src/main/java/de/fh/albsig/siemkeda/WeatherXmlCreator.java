package de.fh.albsig.siemkeda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/** . */
public class WeatherXmlCreator {
  /** logger. */
  private Logger log = Logger.getLogger(WeatherXmlCreator.class.getName());

  /**
   * creates pretty printed xml file from Document, named
   * "weather_cityname_lastupdated.xml".
   *
   * @param xmlData weather data String in xml format
   * @return true, if correct xml data given and file can be created; else false
   */
  public final boolean createXmlFile(final String xmlData) {

    if (xmlData == null || xmlData == "") {
      return false;
    } else {
      // creating document
      try {
        log.debug("create Document");
        Document doc = null;
        try {
          DocumentBuilderFactory docFactory = DocumentBuilderFactory
              .newInstance();
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          doc = docBuilder.parse(new InputSource(new StringReader(xmlData)));
        } catch (ParserConfigurationException pce) {
          log.error(pce.getMessage(), pce);
        } catch (SAXException se) {
          log.error(se.getMessage(), se);
        } catch (IOException ioe) {
          log.error(ioe.getMessage(), ioe);
        } catch (NullPointerException npe) {
          log.error(npe.getMessage(), npe);
        }
        log.debug("Document created");

        // pretty print
        log.debug("pretty printing");
        TransformerFactory transformerFactory = TransformerFactory
            .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
            "{http://xml.apache.org/xslt}indent-amount", "2");

        // extract infos
        Element root = doc.getDocumentElement();
        NodeList nodel = root.getChildNodes();
        Node nameNode = nodel.item(0);
        String cityname = null;
        Node updateNode = nodel.item(nodel.getLength() - 1);
        String lastupdated = null;
        log.debug("extracting cityname");
        try {
          if (nameNode instanceof Element) {
            cityname = (String) ((Element) nameNode).getAttribute("name");
          }
          log.debug("cityname: " + cityname);

          log.debug("extracting lastupdated");
          if (updateNode instanceof Element) {
            lastupdated = ((Element) updateNode).getAttribute("value");
            lastupdated = lastupdated.replaceAll(":", "-");
          }
          log.debug("lastupdated: " + lastupdated);

          if (cityname == "" || lastupdated == "") {
            throw new FileNotFoundException("wrong input format");
          }
        } catch (FileNotFoundException nfe) {
          log.error(nfe.getMessage(), nfe);
          return false;
        }

        // create XML file
        log.debug("creating XML file");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(
            new File("./src/main/resources/weather_" + cityname + "_"
                + lastupdated + ".xml"));
        transformer.transform(source, result);
        log.debug("XML file \"weather_" + cityname + "_" + lastupdated
            + ".xml\" created in root folder of the project");
      } catch (TransformerException tfe) {
        log.error(tfe.getMessage(), tfe);
        return false;
      }
      return true;
    }
  }

}
