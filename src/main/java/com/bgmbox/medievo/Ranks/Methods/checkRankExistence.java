package com.bgmbox.medievo.Ranks.Methods;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

import static com.bgmbox.medievo.util.Generic.getMedievoFolder;

public class checkRankExistence {

    public static boolean rankExists(String rank) {
        try {
            if (rankWasFoundInXML(rank)) {
                return true;
            }
        } catch (JDOMException | IOException excpetion) {
            excpetion.printStackTrace();
        }
        return false;
    }

    private static boolean rankWasFoundInXML(String inputRank) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document readRanksXML = builder.build(new File(getMedievoFolder() + "/ranks.xml"));
        Element root = readRanksXML.getRootElement();

        for (Element rankInstance : root.getChildren("rank")) {
            String rankInstanceName = rankInstance.getAttributeValue("name");
            if (rankInstanceName.equals(inputRank)) {
                return true;
            }
        }
        return false;
    }
}
