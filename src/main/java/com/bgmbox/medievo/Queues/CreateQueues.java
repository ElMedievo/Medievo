package com.bgmbox.medievo.Queues;

import java.util.HashMap;

public class CreateQueues {

    public static HashMap<String, String> inviteQueue;
    public static HashMap<String, Integer> inviteTimeQueue;

    public static void createQueues() {
        inviteQueue = new HashMap<>();
        inviteTimeQueue = new HashMap<>();
    }
}
