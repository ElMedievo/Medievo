package org.elmedievo.medievo.Queues;

import java.util.HashMap;

public class CreateQueues {

    public static HashMap<String, String> inviteQueue;
    public static HashMap<String, String> chatQueue;

    public static void createQueues() {
        inviteQueue = new HashMap<>();
        chatQueue = new HashMap<>();
    }
}
