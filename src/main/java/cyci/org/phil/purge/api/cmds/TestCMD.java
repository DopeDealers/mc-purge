package cyci.org.phil.purge.api.cmds;

import cyci.org.phil.purge.api.CommandListener;
import cyci.org.phil.purge.config.Lang;
import cyci.org.phil.purge.utils.C;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Thu - 18/Aug/2022 - 11:43 PM
 */
public class TestCMD extends CommandListener {
    public TestCMD() {
        super("test", "purge.commands.test", new String[] { "fakejoin", "fakeleave", "say" });
    }

    @Deprecated
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player))
            return;
        if (args.length == 0) {
            sender.sendMessage(C.c(Lang.NO_ARG.getConfigValue(new String[]{"/tellr <fakejoin:fakeleave:say>", Lang.PREFIX.getConfigValue(null)})));
        }
        sender.sendMessage(C.c(Lang.PURGE_START.getConfigValue(new String[]{null, Lang.PREFIX.getConfigValue(null)})));
    }
}

