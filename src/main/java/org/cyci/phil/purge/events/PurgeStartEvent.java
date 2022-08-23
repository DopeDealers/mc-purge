package org.cyci.phil.purge.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.cyci.phil.purge.utils.CountdownTimer;
import org.cyci.phil.purge.utils.PurgeHandler;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Mon - 22/Aug/2022 - 3:26 AM
 */
public class PurgeStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public PurgeStartEvent(PurgeHandler e) {

    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
