package cyci.org.phil.purge.listeners;

import cyci.org.phil.purge.config.Lang;
import cyci.org.phil.purge.player.PurgePlayerHandler;
import cyci.org.phil.purge.utils.C;
import cyci.org.phil.purge.utils.PurgeHandler;
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
                ((Player) e.getDamager()).setHealth(((Player) e.getDamager()).getHealth() - 0.1);
                e.getDamager().sendMessage(C.c(Lang.PREFIX.getConfigValue(null) + " &7Please do not hit players the purge has not begun."));
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
