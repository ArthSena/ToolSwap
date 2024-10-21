package xyz.artsna.toolswap.bukkit.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.artsna.toolswap.bukkit.Config;
import xyz.artsna.toolswap.bukkit.SwapData;
import xyz.artsna.toolswap.bukkit.ToolSwapPlugin;
import xyz.artsna.toolswap.bukkit.views.SettingsView;
import xyz.artsna.toolswap.core.command.Arguments;
import xyz.artsna.toolswap.core.command.Command;
import xyz.artsna.toolswap.core.command.JavaCommand;
import xyz.artsna.toolswap.core.command.SenderType;

import java.util.List;

@Command(name = "toolswap", senderType = SenderType.PLAYER)
public class ToolSwapCommand extends JavaCommand {

    private final Config config = ToolSwapPlugin.instance.getConfig();
    private final SwapData data = ToolSwapPlugin.instance.getSwapData();

    public ToolSwapCommand() {
        if(config.getUsePermission() != null && !config.getUsePermission().equalsIgnoreCase("none"))
            setPermission(config.getUsePermission());
    }

    @Override
    public boolean perform(CommandSender sender, Arguments args) {
        new SettingsView(config, data).open((Player) sender);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, Arguments args) {
        return List.of();
    }

}
