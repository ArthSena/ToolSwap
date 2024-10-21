package xyz.artsna.toolswap.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.artsna.toolswap.core.command.CommandManager;
import xyz.artsna.toolswap.core.inventory.ViewListener;

public final class ToolSwapPlugin extends JavaPlugin {

    public static ToolSwapPlugin instance;

    public ToolSwapPlugin() { instance = this; }

    private CommandManager commandManager;
    private final Config config = new Config(this);

    private final SwapHandler swapHandler = new SwapHandler(this);
    private final SwapData swapData = new SwapData(this);

    @Override
    public void onLoad() {
         commandManager = new CommandManager(this);
    }

    @Override
    public void onEnable() {
        commandManager.registerCommands();

        ViewListener.Register(this);

        Bukkit.getPluginManager().registerEvents(new SwapListener(this), this);
    }

    public @NotNull Config getConfig() {
        return config;
    }

    public SwapData getSwapData() {
        return swapData;
    }

    public SwapHandler getSwapHandler() {
        return swapHandler;
    }
}
