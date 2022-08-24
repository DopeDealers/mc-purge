package org.cyci.phil.purge.listeners;

import org.cyci.phil.purge.config.Lang;
import org.cyci.phil.purge.events.PurgeStartEvent;
import org.cyci.phil.purge.player.PurgePlayerHandler;
import org.cyci.phil.purge.utils.C;
import org.cyci.phil.purge.utils.PurgeHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 1:42 PM
 */
public class PurgeDamageListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void purgeDamagePlayer(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER)) {
            if (!PurgeHandler.purgeStarted) {
                e.setCancelled(true);
                e.getDamager().sendMessage(C.c(Lang.PURGE_HIT_PLAYER.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})));
            }
        }

    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void purgeKillPlayer(PlayerDeathEvent e) {
        if (PurgeHandler.purgeStarted) {
            PurgePlayerHandler purge = new PurgePlayerHandler(e.getPlayer());
            if (!purge.isMurderer() && purge.getKills() == 0) {
                purge.setMurderer(true);
                purge.addKills();
            }
        }
    }
}
