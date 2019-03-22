package com.bgmbox.medievo.Ranks;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.bgmbox.medievo.util.Generic.RANKS_FILE_FOUND;
import static com.bgmbox.medievo.util.Generic.RANKS_FILE_NOT_FOUND;
import static com.bgmbox.medievo.util.Generic.RANKS_CANNOT_CREATE;
import static com.bgmbox.medievo.util.Generic.getMedievoFolder;
import static com.bgmbox.medievo.util.Methods.ConsoleAlerts.sendConsoleAlert;

public class CreateRanksFile {

    public static void createRanksXMLFile() {
        try {
            if (!new File(getMedievoFolder() + "/ranks.xml").exists()) {
                sendConsoleAlert(RANKS_FILE_NOT_FOUND);
                generateRanksXML();
            } else {
                sendConsoleAlert(RANKS_FILE_FOUND);
            }
        } catch (IOException exception) {
            sendConsoleAlert(RANKS_CANNOT_CREATE);
            exception.printStackTrace();
        }
    }

    private static void generateRanksXML() throws IOException {
        Document ranksXMLDocument = new Document();

        Element ranks = new Element("ranks");
        ranksXMLDocument.setRootElement(ranks);

        Element userRank = new Element("rank");
        userRank.setAttribute("name", "Default");
        userRank.setAttribute("priority", "0");

        Element adminRank = new Element("rank");
        Element adminPerms = new Element("permission");
        adminRank.setAttribute("name", "Administrator");
        adminRank.setAttribute("staff", "true");
        adminRank.setAttribute("flair", "'6❖");
        adminRank.setAttribute("parents", "Default");
        adminRank.setAttribute("priority", "100");
        adminRank.addContent(adminPerms);
        adminPerms.setText("minecraft.command.op");

        ranks.addContent(userRank);
        ranks.addContent(adminRank);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(ranksXMLDocument, new FileOutputStream(getMedievoFolder() + "/ranks.xml"));
    }
}
