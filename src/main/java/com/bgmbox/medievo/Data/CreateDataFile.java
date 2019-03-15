package com.bgmbox.medievo.Data;

import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.bgmbox.medievo.Medievo.instance;

public class CreateDataFile {

    public static void initializeData() {
        try {
            createPlayersDataXML();
        } catch (ParserConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void  createPlayersDataXML() throws ParserConfigurationException, IOException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document xmlDocument = documentBuilder.newDocument();

        Element rootElement = xmlDocument.createElement("players");
        Element mainElement = xmlDocument.createElement("player");

        mainElement.setAttribute("uuid", "4cb7aa21-96ba-4237-9469-56a43a781bb6");
        mainElement.setAttribute("staff", "false");

        OutputFormat outputFormat = new OutputFormat(xmlDocument);
        outputFormat.setIndenting(true);

        File xmlFile = new File(instance.getDataFolder()+"/data.xml");
        FileOutputStream outputStream = new FileOutputStream(xmlFile);

        XMLSerializer serializer = new XMLSerializer(outputStream, outputFormat);

        serializer.serialize(xmlDocument);
    }
}
