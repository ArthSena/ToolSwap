package xyz.artsna.toolswap.core.command;

import java.lang.reflect.InvocationTargetException;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public class CommandManager {
	
	private final JavaPlugin plugin;
	
	public CommandManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void registerCommands() {
		long time = System.currentTimeMillis();
		Bukkit.getConsoleSender().sendMessage(NamedTextColor.AQUA + "[" + plugin.getName() + "] Starting commands registration system...");
		Reflections ref = new Reflections("xyz.artsna");

		for(Class<?> c : ref.getTypesAnnotatedWith(Command.class)) {
			try {
				JavaCommand command = (JavaCommand) c.getConstructor().newInstance();
				Command annotation = c.getAnnotation(Command.class);
					
				command.setName(annotation.name());
				if(!annotation.permission().equalsIgnoreCase("null")) command.setPermission(annotation.permission());
				if(command.getPermissionMessage() == null) command.setPermissionMessage(NamedTextColor.RED + "You don't have permission.");
				command.setSenderType(annotation.senderType());
				if(command.getSenderTypeMessage() == null) command.setSenderTypeMessage(NamedTextColor.RED + "Only the " + annotation.senderType().toString().toLowerCase() + " can use this command.");
				
				PluginCommand pluginCommand = plugin.getCommand(command.getName());
				
				if(pluginCommand == null)
					throw new NullPointerException("you need to fill the plugin.yml with the command names ");

				pluginCommand.setExecutor(command);
				pluginCommand.setTabCompleter(command);
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "[" + plugin.getName() + "] Command " + c.getName() + " is not a instance of JavaCommand.");
			}
		}
		Bukkit.getConsoleSender().sendMessage(NamedTextColor.AQUA + "[" + plugin.getName() + "] Commands started. (" + (System.currentTimeMillis() - time) + "ms)");
	}
}
