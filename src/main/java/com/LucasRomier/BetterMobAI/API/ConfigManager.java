package com.LucasRomier.BetterMobAI.API;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.List;
import java.util.Set;


public class ConfigManager {
    private JavaPlugin plugin;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    public SimpleConfig getNewConfig(String filePath, String[] header) {
        File file = getConfigFile(filePath);

        assert file != null;
        if (!file.exists()) {
            prepareFile(filePath);

            if (header != null && header.length != 0) {
                setHeader(file, header);
            }
        }


        return new SimpleConfig(file, getCommentsNum(file), this.plugin);
    }


    public SimpleConfig getNewConfig(String filePath) {
        return getNewConfig(filePath, null);
    }


    private File getConfigFile(String file) {
        File configFile;
        if (file.isEmpty()) {
            return null;
        }


        if (file.contains("/")) {

            if (file.startsWith("/")) {
                configFile = new File(this.plugin.getDataFolder() + file.replace("/", File.separator));
            } else {
                configFile = new File(this.plugin.getDataFolder() + File.separator + file.replace("/", File.separator));
            }
        } else {

            configFile = new File(this.plugin.getDataFolder(), file);
        }

        return configFile;
    }


    public void prepareFile(String filePath, String resource) {
        File file = getConfigFile(filePath);

        assert file != null;
        if (file.exists()) {
            return;
        }

        try {
            if (file.getParentFile().mkdirs()) {
                if (file.createNewFile()) {
                    if (resource != null && !resource.isEmpty()) {
                        copyResource(this.plugin.getResource(resource), file);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void prepareFile(String filePath) {
        prepareFile(filePath, null);
    }


    public void setHeader(File file, String[] header) {
        if (!file.exists()) {
            return;
        }


        try {
            StringBuilder config = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                config.append(currentLine).append("\n");
            }

            reader.close();
            config.append("# +----------------------------------------------------+ #\n");

            for (String line : header) {

                if (line.length() <= 50) {


                    int lenght = (50 - line.length()) / 2;
                    StringBuilder finalLine = new StringBuilder(line);

                    for (int i = 0; i < lenght; i++) {
                        finalLine.append(" ");
                        finalLine.reverse();
                        finalLine.append(" ");
                        finalLine.reverse();
                    }

                    if (line.length() % 2 != 0) {
                        finalLine.append(" ");
                    }

                    config.append("# < ").append(finalLine.toString()).append(" > #\n");
                }
            }

            config.append("# +----------------------------------------------------+ #");

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(prepareConfigString(config.toString()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int getCommentsNum(File file) {
        if (!file.exists()) {
            return 0;
        }

        try {
            int comments = 0;


            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {

                if (currentLine.startsWith("#")) {
                    comments++;
                }
            }


            reader.close();
            return comments;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


    private String prepareConfigString(String configString) {
        int lastLine = 0;
        int headerLine = 0;

        String[] lines = configString.split("\n");
        StringBuilder config = new StringBuilder();

        for (String line : lines) {

            if (line.startsWith(getPluginName() + "_COMMENT")) {
                String comment = "#" + line.trim().substring(line.indexOf(":") + 1);

                if (comment.startsWith("# +-")) {
                    if (headerLine == 0) {
                        config.append(comment).append("\n");

                        lastLine = 0;
                        headerLine = 1;
                    } else {
                        config.append(comment).append("\n\n");

                        lastLine = 0;
                        headerLine = 0;
                    }
                } else {
                    String normalComment;


                    if (comment.startsWith("# ' ")) {
                        normalComment = comment.substring(0, comment.length() - 1).replaceFirst("# ' ", "# ");
                    } else {
                        normalComment = comment;
                    }

                    if (lastLine == 0) {
                        config.append(normalComment).append("\n");
                    } else {
                        config.append("\n").append(normalComment).append("\n");
                    }

                    lastLine = 0;
                }

            } else {

                config.append(line).append("\n");
                lastLine = 1;
            }
        }


        return config.toString();
    }


    public void saveConfig(String configString, File file) {
        String configuration = prepareConfigString(configString);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(configuration);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getPluginName() {
        return this.plugin.getDescription().getName();
    }


    private void copyResource(InputStream resource, File file) {
        try {
            OutputStream out = new FileOutputStream(file);


            byte[] buf = new byte[1024];
            int lenght;
            while ((lenght = resource.read(buf)) > 0) {
                out.write(buf, 0, lenght);
            }

            out.close();
            resource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class SimpleConfig {
        private int comments;
        private ConfigManager manager;
        private File file;
        private FileConfiguration config;

        public SimpleConfig(File configFile, int comments, JavaPlugin plugin) {
            this.comments = comments;
            this.manager = new ConfigManager(plugin);

            this.file = configFile;
            this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(configFile);
        }

        public Object get(String path) {
            return this.config.get(path);
        }

        public Object get(String path, Object def) {
            return this.config.get(path, def);
        }

        public String getString(String path) {
            return this.config.getString(path);
        }

        public String getString(String path, String def) {
            return this.config.getString(path, def);
        }

        public int getInt(String path) {
            return this.config.getInt(path);
        }

        public int getInt(String path, int def) {
            return this.config.getInt(path, def);
        }

        public boolean getBoolean(String path) {
            return this.config.getBoolean(path);
        }

        public boolean getBoolean(String path, boolean def) {
            return this.config.getBoolean(path, def);
        }

        public void createSection(String path) {
            this.config.createSection(path);
        }

        public ConfigurationSection getConfigurationSection(String path) {
            return this.config.getConfigurationSection(path);
        }

        public double getDouble(String path) {
            return this.config.getDouble(path);
        }

        public double getDouble(String path, double def) {
            return this.config.getDouble(path, def);
        }

        public List<?> getList(String path) {
            return this.config.getList(path);
        }

        public List<?> getList(String path, List<?> def) {
            return this.config.getList(path, def);
        }

        public List<String> getStringList(String path) {
            return this.config.getStringList(path);
        }

        public boolean contains(String path) {
            return this.config.contains(path);
        }

        public void removeKey(String path) {
            this.config.set(path, null);
        }

        public void set(String path, Object value) {
            this.config.set(path, value);
        }

        public void set(String path, Object value, String comment) {
            if (!this.config.contains(path)) {
                this.config.set(this.manager.getPluginName() + "_COMMENT_" + this.comments, " " + comment);
                this.comments++;
            }

            this.config.set(path, value);
        }


        public void set(String path, Object value, String[] comment) {
            for (String comm : comment) {

                if (!this.config.contains(path)) {
                    this.config.set(this.manager.getPluginName() + "_COMMENT_" + this.comments, " " + comm);
                    this.comments++;
                }
            }


            this.config.set(path, value);
        }


        public void setHeader(String[] header) {
            this.manager.setHeader(this.file, header);
            this.comments = header.length + 2;
            reloadConfig();
        }

        public void reloadConfig() {
            this.config = YamlConfiguration.loadConfiguration(this.file);
        }

        public void saveConfig() {
            String config = this.config.saveToString();
            this.manager.saveConfig(config, this.file);
        }


        public Set<String> getKeys() {
            return this.config.getKeys(false);
        }
    }
}





