package com.bgmbox.medievo.Queues;

import static com.bgmbox.medievo.Queues.CreateQueues.inviteQueue;

public class ClanQueueMethods {

    public static boolean isAlreadyBeingInvited(String player_name) {
        return inviteQueue.containsKey(player_name);
    }
}
