package cyci.org.phil.purge.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import cyci.org.phil.purge.config.Lang;
import cyci.org.phil.purge.utils.ActionBar;
import cyci.org.phil.purge.utils.C;
import cyci.org.phil.purge.utils.PurgeHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

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
        PurgeHandler.startCountDown(sender.getWorld());
    }
}
