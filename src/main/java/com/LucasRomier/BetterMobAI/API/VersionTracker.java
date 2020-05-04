package com.LucasRomier.BetterMobAI.API;

import com.LucasRomier.BetterMobAI.MobAI;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class VersionTracker {
    public VersionTracker() {
        if (MobAI.settings.configuration.getBoolean("VersionTracking")) {
            URL url;
            try {
                url = new URL("https://dev.bukkit.org/projects/better-mob-ai");
            } catch (MalformedURLException e) {
                return;
            }
            InputStream inputStream;
            try {
                inputStream = url.openStream();
            } catch (IOException e) {
                System.err.println("/============================================================\\");
                System.err.println("|[Better MobAI] Could not retrieve last Better MobAI version:|");
                System.err.println("|[Better MobAI] You are not connected to the internet!       |");
                System.err.println("\\============================================================/");
                return;
            }
            try {
                String version = MobAI.getInstance().getDescription().getVersion().replace("version: ", "").replace(" ", "");
                double currentVersion = Double.parseDouble(version);

                Scanner scanner = new Scanner(inputStream, "UTF-8");
                scanner.useDelimiter("\\n");
                boolean found = false;
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.contains("Current plugin version:")) {
                        found = true;
                        double latestVersion = Double.parseDouble(line.replaceAll("[^0-9.]", ""));

                        if (latestVersion > currentVersion) {
                            System.out.println("/============================================================================================================\\");
                            System.out.println("|[Better MobAI] There is a new version of Better MobAI availaible!                                           |");
                            System.out.println("|[Better MobAI] Current version: " + currentVersion + "                                                                            ".substring(0, 76 - String.valueOf(currentVersion).length()) + "|");
                            System.out.println("|[Better MobAI] Latest version: " + latestVersion + "                                                                             ".substring(0, 77 - String.valueOf(latestVersion).length()) + "|");
                            System.out.println("|[Better MobAI] Download now: http://dev.bukkit.org/bukkit-plugins/better-mob-ai/#w-installation-and-updating|");
                            System.out.println("\\============================================================================================================/");
                            break;
                        }
                        System.out.println("/==========================================\\");
                        System.out.println("|[Better MobAI] Better MobAI is up to date!|");
                        System.out.println("|[Better MobAI] Current version: " + currentVersion + "!" + "         ".substring(0, 9 - String.valueOf(currentVersion).length()) + "|");
                        System.out.println("\\==========================================/");

                        break;
                    }
                }
                scanner.close();
                inputStream.close();
                if (!found) {
                    System.err.println("/==================================================================\\");
                    System.err.println("|[Better MobAI] Could not retrieve last Better MobAI version:      |");
                    System.err.println("|[Better MobAI] Our team of highly trained monkey is working on it!|");
                    System.err.println("\\==================================================================/");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}





