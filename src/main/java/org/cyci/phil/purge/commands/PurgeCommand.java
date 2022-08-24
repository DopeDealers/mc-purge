package org.cyci.phil.purge.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.ericdebouwer.regionclaimplus.RegionClaimPlus;
import org.bukkit.Bukkit;
import org.cyci.phil.purge.Purge;
import org.cyci.phil.purge.config.Lang;
import org.cyci.phil.purge.utils.C;
import org.cyci.phil.purge.utils.PurgeHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.cyci.phil.purge.utils.PurgeHourlyTimer;
import org.cyci.phil.purge.utils.Utils;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 9:56 PM
 */
@CommandAlias("purge|prg")
public class PurgeCommand extends BaseCommand {

    @Default
    public void onDefault(CommandSender sender) {
        sender.sendMessage(C.c(Lang.PREFIX.getConfigValue(null) + " &7The purge gamemode. created by &6DopeDealers.&7 type /purge help for more info"));
    }
    @Subcommand("test")
    @CommandPermission("purge.commands.test")
    public void onTest(Player sender) {
        //PurgeHandler.startCountDown(sender.getWorld());
        //new ActionBar().sendTitle(Objects.requireNonNull(sender.getPlayer()), C.c("hehe"), C.c("hehet"));
        PurgeHandler.startCountDown();

    }
    @Subcommand("timer")
    @CommandPermission("purge.commands.timer")
    public void onTimer(Player sender) {
        if (!PurgeHandler.purgeStarted) {
            sender.sendMessage(C.c("&aThe purge has not begun yet! relax and enjoy"));
        } else {
            int[] test = Utils.splitToComponentTimes(BigDecimal.valueOf(Purge.getInstance().countdownHolder));
            sender.sendMessage(C.c(Lang.PURGE_ON_GOING.getConfigValue(new String[]{"", Lang.PREFIX.getConfigValue(null), String.valueOf(test[0]), String.valueOf(test[1]), String.valueOf(test[2])})));
        }
    }
    @CommandPermission("purge.commands.test")
    @Subcommand("help")
    public void onHelp(Player sender) {
        if (sender instanceof ConsoleCommandSender) return;
        else {
            sender.sendMessage(C.c("&7&m*---&c&m+&7&m--------------------&c&m+&7&m---*"));
            sender.sendMessage(" ");
            sender.sendMessage(C.c("   &c&m+&7&m---&c&m+&r &4&lPurge &chelp page&r &c&m+&7&m---&c&m+"));
            sender.sendMessage(" ");
            sender.sendMessage(C.c("&7Help:&r Brings up this page"));
            sender.sendMessage(C.c("&7Test:&r Run the test command to see if the purge works!"));
            sender.sendMessage(C.c("&7Timer: Check the current timer if the purge is running"));
            sender.sendMessage(" ");
            sender.sendMessage(C.c("&7&m*---&c&m+&7&m--------------------&c&m+&7&m---*"));
        }
    }
}
