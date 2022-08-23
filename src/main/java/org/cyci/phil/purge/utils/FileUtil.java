package org.cyci.phil.purge.utils;

/**
 * @author - Phil
 * @project - mc-purge
 * @website - https://cyci.org
 * @email - staff@cyci.org
 * @created Fri - 19/Aug/2022 - 1:54 PM
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileUtil {
    private static final String DIRECTORY = "plugins/Purge/";

    public static FileConfiguration getFile(String fileName) {
        try {
            return (FileConfiguration)YamlConfiguration.loadConfiguration(getFile(fileName, true));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getFile(String fileName, boolean createIfNotExist) throws IOException {
        File file = new File(DIRECTORY, fileName);
        if (createIfNotExist && !file.exists()) {
            boolean success = file.createNewFile();
            if (!success)
                throw new IOException("File could not be created");
        }
        return file;
    }

    public static List<File> getAllFiletype(String ending) {
        List<File> files = new ArrayList<>();
        File dir = new File(DIRECTORY);
        try {
            File[] listFiles;
            for (int length = (Objects.requireNonNull(listFiles = dir.listFiles())).length, i = 0; i < length; i++) {
                File file = listFiles[i];
                if (file.getName().endsWith(ending))
                    files.add(file);
            }
        } catch (NullPointerException ex) {
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(DIRECTORY.split("/")[1])));
            Bukkit.getLogger().severe("No files found. Now disabling.");
            ex.printStackTrace();
        }
        return files;
    }

    public static String getDirectory() {
        return DIRECTORY;
    }

    public static void saveFile(FileConfiguration file, String name) {
        try {
            file.save(getFile(name, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

