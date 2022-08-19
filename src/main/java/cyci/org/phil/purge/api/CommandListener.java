package cyci.org.phil.purge.api;

import org.bukkit.command.CommandSender;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Thu - 18/Aug/2022 - 11:40 PM
 */
public abstract class CommandListener {
    private final String name;

    private final String permission;

    private final String[] arguments;

    public CommandListener(String name, String permission, String[] arguments) {
        this.name = name;
        this.permission = permission;
        this.arguments = arguments;
    }

    public CommandListener(String name, String[] arguments) {
        this.name = name;
        this.arguments = arguments;
        this.permission = "";
    }

    public String getName() {
        return this.name;
    }

    public String getPermission() {
        return this.permission;
    }

    public String[] getArgs() {
        return this.arguments;
    }

    public abstract void execute(CommandSender paramCommandSender, String[] paramArrayOfString);
}
