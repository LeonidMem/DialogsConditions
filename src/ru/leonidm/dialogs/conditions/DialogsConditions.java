package ru.leonidm.dialogs.conditions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.leonidm.dialogs.api.DialogsAPI;

public class DialogsConditions extends JavaPlugin {

    private static DialogsConditions instance;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new DialogsEventsHandler(), this);
        DialogsAPI.registerDecorator(new DialogsConditionsChecker());

        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    public static DialogsConditions getInstance() {
        return instance;
    }
}
