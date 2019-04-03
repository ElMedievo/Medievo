package com.bgmbox.medievo.Queues.Methods;

import static com.bgmbox.medievo.Queues.CreateQueues.inviteQueue;

public class ClanQueue {

    public static boolean isAlreadyBeingInvited(String player_name) {
        return inviteQueue.containsKey(player_name);
    }

    public static void destroyPendantInvitation(String player_name) {
        inviteQueue.remove(player_name);
    }
}
