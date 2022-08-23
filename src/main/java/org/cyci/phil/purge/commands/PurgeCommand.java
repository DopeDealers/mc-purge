package org.cyci.phil.purge.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.ericdebouwer.regionclaimplus.RegionClaimPlus;
import org.bukkit.Bukkit;
import org.cyci.phil.purge.config.Lang;
import org.cyci.phil.purge.utils.C;
import org.cyci.phil.purge.utils.PurgeHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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
    public void onTest(Player sender) {
        //PurgeHandler.startCountDown(sender.getWorld());
        //new ActionBar().sendTitle(Objects.requireNonNull(sender.getPlayer()), C.c("hehe"), C.c("hehet"));
        PurgeHandler.startCountDown();

    }
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
            sender.sendMessage(" ");
            sender.sendMessage(C.c("&7&m*---&c&m+&7&m--------------------&c&m+&7&m---*"));
        }
    }
}
