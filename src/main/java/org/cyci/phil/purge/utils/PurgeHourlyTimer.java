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
    private final Plugin region = Bukkit.getPluginManager().getPlugin("RegionClaimPlus");
    public CountdownTimer timer = new CountdownTimer(Purge.getInstance(), 3600,
            () -> Bukkit.getLogger().info("Purge: Timer beginning 1hr"),
            () -> {
                Bukkit.getWorlds().forEach(world -> {
                    world.getPlayers().forEach(message -> {
                        message.playSound(Objects.requireNonNull(message.getPlayer()).getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, Float.MAX_VALUE, 1.0f);
                        message.sendMessage(C.c(Lang.PURGE_END.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})));
                        if (Lang.PURGE_ACTION_BARS.getBoolean()) {
                            ActionBar ab = new ActionBar();
                            ab.sendTitle(message.getPlayer(), C.c(Lang.PURGE_END.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null)})), C.c("&a"));
                        }
                        PurgeHandler.purgeStarted = false;
                        try {
                            assert region != null;
                            Bukkit.getPluginManager().enablePlugin(region);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                });
            },
            (t) -> {
                Purge.getInstance().countdownHolder = t.getSecondsLeft();
                if (t.getSecondsLeft() == 10) {
                    Bukkit.getWorlds().forEach(world -> {
                        world.getPlayers().forEach(message -> {
                            message.sendMessage(C.c(Lang.PURGE_COUNTDOWN_MESSAGE_END.getConfigValue(new String[]{String.valueOf(t.getSecondsLeft()), Lang.PREFIX.getConfigValue(null)})));
                        });
                    });
                    //purgeStarted = true;
                }
            });
    int time;

    public PurgeHourlyTimer() {
        this.time = 0;
    }

    public void run() {
        PurgeHandler.startCountDown();
        Bukkit.getLogger().info("Starting countdown");
    }
}
