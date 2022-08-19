package cyci.org.phil.purge.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Thu - 18/Aug/2022 - 11:41 PM
 */
public class CommandRegistry {
    private static List<CommandListener> commands;

    public CommandRegistry() {
        if (commands == null)
            commands = new ArrayList<>();
//        commands.add(new TestCMD());
//        commands.add(new SoundCMD());
//        commands.add(new SizeCMD());
//        commands.add(new EatCMD());
//        commands.add(new SleepCMD());
//        commands.add(new TerroristCMD());
//        commands.add(new BcCMD());
//        commands.add(new UserInfoCMD());
//        commands.add(new SetSpawnCMD());
//        commands.add(new DeathBedCMD());
    }

    public static CommandListener getCommand(String name) {
        for (CommandListener commands : getCommands()) {
            if (commands.getName().equalsIgnoreCase(name))
                return commands;
        }
        return null;
    }

    public static List<CommandListener> getCommands() {
        return commands;
    }
}