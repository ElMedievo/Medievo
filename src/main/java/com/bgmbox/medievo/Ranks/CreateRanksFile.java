package com.bgmbox.medievo.Ranks;

import com.bgmbox.medievo.Medievo;
import org.bukkit.ChatColor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.bgmbox.medievo.util.Generic.getMedievoFolder;
import static com.bgmbox.medievo.util.Methods.ConsoleAlert.sendConsoleAlert;

public class CreateRanksFile {

    public static void createRanksXMLFile() {
        try {
            if (!new File(Medievo.instance.getDataFolder() + "/ranks.xml").exists()) {
                sendConsoleAlert(ChatColor.GREEN + "Ranks document not found. Creating one...");
                generateRanksXML();
            } else {
                sendConsoleAlert(ChatColor.GREEN + "Reading ranks document...");
            }
        } catch (IOException exception) {
            sendConsoleAlert(ChatColor.RED + "An internal error has occurred while generating ranks.xml file");
            exception.printStackTrace();
        }
    }

    private static void generateRanksXML() throws IOException {
        Document ranksXMLDocument = new Document();

        Element ranks = new Element("ranks");
        ranksXMLDocument.setRootElement(ranks);

        Element userRank = new Element("rank");
        userRank.setAttribute("name", "Default");

        Element adminRank = new Element("rank");
        Element adminPerms = new Element("permission");
        adminRank.setAttribute("name", "Administrator");
        adminRank.setAttribute("staff", "true");
        adminRank.setAttribute("flair", "'6❖");
        adminRank.setAttribute("parents", "Default");
        adminRank.addContent(adminPerms);
        adminPerms.setText("minecraft.command.op");

        ranks.addContent(userRank);
        ranks.addContent(adminRank);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        xmlOutputter.output(ranksXMLDocument, new FileOutputStream(getMedievoFolder() + "/ranks.xml"));
    }
}
