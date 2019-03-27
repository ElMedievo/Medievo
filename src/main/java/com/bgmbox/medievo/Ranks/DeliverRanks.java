package com.bgmbox.medievo.Ranks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bgmbox.medievo.Ranks.CreateRanksData.getRanksData;
import static com.bgmbox.medievo.util.Generic.getMedievoFolder;

public class DeliverRanks {

    public static void deliverRanks(Player player) {
        try {
            getRanksForDelivery(player);
        } catch (JDOMException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void getRanksForDelivery(Player player) throws JDOMException, IOException {
        String player_name = player.getName();
        String player_uuid = player.getUniqueId().toString();

        List<String> ranksList = getRanksData().getStringList("players." + player_uuid + ".ranks");
        StringBuilder builtFlair = new StringBuilder();

        for (String rankInYML : ranksList) {
            SAXBuilder builder = new SAXBuilder();
            Document readRanksXML = builder.build(new File(getMedievoFolder() + "/ranks.xml"));
            Element root = readRanksXML.getRootElement();
            if (root != null) {
                for (Element rankInXML : root.getChildren("rank")) {
                    String rankInXMLName = rankInXML.getAttributeValue("name");
                    if (rankInYML.equalsIgnoreCase(rankInXMLName)) {
                        String rawFlair = rankInXML.getAttributeValue("flair");
                        if (rawFlair != null) {
                            String coloredFlair = (ChatColor.translateAlternateColorCodes ('$', rawFlair));
                            builtFlair.append(coloredFlair);
                        }
                    }
                }
            }
        }
        builtFlair.append(ChatColor.YELLOW + "");
        String finalFlair = builtFlair.toString();
        player.setDisplayName(finalFlair + player_name);
        player.setPlayerListName(finalFlair + player_name);
    }
}
