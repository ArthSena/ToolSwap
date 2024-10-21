package xyz.artsna.toolswap.core.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class JavaCommand implements CommandExecutor, TabExecutor, Listener {
	
	private String name;
	private String permission;
	private String permissionMessage;
	private SenderType senderType;
	private String senderTypeMessage;
	
	public JavaCommand() {}

    /**
     * Returns the name of this command
     *
     * @return Name of this command
     */
	public String getName() {
		return name;
	}
	
    /**
     * Sets the name of this command
     *
     * @param name Name of command
     */
	protected void setName(String name) {
		this.name = name;
	}
	
    /**
     * Gets the permission required by users to be able to perform this
     * command
     *
     * @return Permission name, or null if none
     */
	public String getPermission() {
		return permission;
	}
	
    /**
     * Sets the permission required by users to be able to perform this
     * command
     *
     * @param permission Permission name or null
     */
	protected void setPermission(String permission) {
		this.permission = permission;
	}
	

    /**
     * Sets the message sent when a permission check fails
     *
     * @param permissionMessage new permission message, null to indicate
     *     default message, or an empty string to indicate no message
     */
    protected void setPermissionMessage(String permissionMessage) {
        this.permissionMessage = ChatColor.translateAlternateColorCodes('&', permissionMessage);
    }
    
    public String getPermissionMessage() {
		return permissionMessage;
	}

    protected void setSenderType(SenderType senderType) {
		this.senderType = senderType;
	}
	
	public SenderType getSenderType() {
		return senderType;
	}
	
	protected void setSenderTypeMessage(String senderTypeMessage) {
        this.senderTypeMessage = ChatColor.translateAlternateColorCodes('&', senderTypeMessage);
	}
	
	public String getSenderTypeMessage() {
		return senderTypeMessage;
	}
	
    /**
     * Executes the given command, returning its success
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return true if a valid command, otherwise false
     */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase(name)) {
			
			if(getSenderType() == SenderType.PLAYER && !(sender instanceof Player) ||
					getSenderType() == SenderType.CONSOLE && !(sender instanceof ConsoleCommandSender)) {
				sender.sendMessage(senderTypeMessage);
				return true;
			}
			
			if((getPermission() != null) && !sender.hasPermission(getPermission())) {
				sender.sendMessage(permissionMessage);
				return true;
			}
			
			return perform(sender, new Arguments(args));
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return tabComplete(sender, new Arguments(args));
	}

	/**
     * Executes the command
     *
     * @param sender Source object which is executing this command
     * @param args All arguments passed to the command
     */
	public abstract boolean perform(CommandSender sender, Arguments args);
    public abstract List<String> tabComplete(CommandSender sender, Arguments args);

}
