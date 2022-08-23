package org.cyci.phil.purge.utils;

import com.ericdebouwer.regionclaimplus.RegionClaimPlus;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.cyci.phil.purge.Purge;
import org.cyci.phil.purge.config.Lang;

import java.util.Objects;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Tue - 23/Aug/2022 - 1:29 PM
 */
public class PurgeHourlyTimer extends BukkitRunnable {

    private final int interval = 300;

    int time;

    public PurgeHourlyTimer() {
        this.time = 0;
    }

    public void run() {
        PurgeHandler.startCountDown();
        Bukkit.getLogger().info("Starting countdown");
        Plugin region = Bukkit.getPluginManager().getPlugin("RegionClaimPlus");
        CountdownTimer timer = new CountdownTimer(Purge.getInstance(), 3600,
                () -> Bukkit.getLogger().info("Timer beginning"),
                () -> {
                    Bukkit.getWorlds().forEach(world -> {
                        world.getPlayers().forEach(message -> {
                            message.playSound(Objects.requireNonNull(message.getPlayer()).getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, Float.MAX_VALUE, 1.0f);
                            message.sendMessage(C.c(Lang.PURGE_END.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})));
                            ActionBar ab = new ActionBar();
                            ab.sendTitle(message.getPlayer(), C.c(Lang.PURGE_END.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})), C.c("&a"));
                            PurgeHandler.purgeStarted = false;
                            try {
                                Bukkit.getPluginManager().disablePlugin(region);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    });
                },
                (t) -> {
                    if (t.getSecondsLeft() == 10) {
                        Bukkit.getWorlds().forEach(world -> {
                            world.getPlayers().forEach(message -> {
                                message.sendMessage(C.c(Lang.PREFIX.getConfigValue(null) + " &7The purge will end in.. &r" + t.getSecondsLeft()));
                            });
                        });
                        //purgeStarted = true;
                    }
                });
    }
}
