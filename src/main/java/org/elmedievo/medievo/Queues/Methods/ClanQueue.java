package org.elmedievo.medievo.Queues.Methods;

import org.elmedievo.medievo.Queues.CreateQueues;

public class ClanQueue {

    public static boolean isAlreadyBeingInvited(String player_name) {
        return CreateQueues.inviteQueue.containsKey(player_name);
    }

    public static void destroyPendantInvitation(String player_name) {
        CreateQueues.inviteQueue.remove(player_name);
    }
}
