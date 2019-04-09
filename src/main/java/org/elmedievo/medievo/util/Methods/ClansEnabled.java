package org.elmedievo.medievo.util.Methods;

import org.elmedievo.medievo.Medievo;

public class ClansEnabled {
    public static boolean clansAreEnabled() {
        return Medievo.instance.getConfig().getBoolean("clans");
    }
}
