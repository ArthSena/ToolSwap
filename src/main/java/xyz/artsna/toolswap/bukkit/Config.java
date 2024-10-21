package xyz.artsna.toolswap.bukkit;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import xyz.artsna.toolswap.bukkit.exceptions.InvalidConfigException;
import xyz.artsna.toolswap.core.file.YamlFile;

import java.util.List;
import java.util.stream.Collectors;

public class Config extends YamlFile {

    public Config(Plugin plugin) {
        super(plugin.getDataFolder(), "config.yml");

        this.saveDefaultConfig(plugin);
    }

    public String getUsePermission() {
        return getString("permissions.use");
    }

    public String getPreferSilkPermission() {
        return getString("permissions.prefer-silk");
    }

    public List<Material> getPickaxeInteractions() throws InvalidConfigException {
        if(!contains("interactions.pickaxe"))
            throw new InvalidConfigException("You must specify 'interactions.pickaxe' blocks in config.yml");

        var interactions = getStringList("interactions.pickaxe");

        if(interactions.isEmpty())
            throw new InvalidConfigException("'interactions.pickaxe' must contain at least one block");

        return interactions.stream().map(Material::valueOf).collect(Collectors.toList());
    }

    public List<Material> getShovelInteractions() throws InvalidConfigException {
        if(!contains("interactions.shovel"))
            throw new InvalidConfigException("You must specify 'interactions.shovel' blocks in config.yml");

        var interactions = getStringList("interactions.shovel");

        if(interactions.isEmpty())
            throw new InvalidConfigException("'interactions.shovel' must contain at least one block");

        return interactions.stream().map(Material::valueOf).collect(Collectors.toList());
    }

    public List<Material> getAxeInteractions() throws InvalidConfigException {
        if(!contains("interactions.axe"))
            throw new InvalidConfigException("You must specify 'interactions.axe' blocks in config.yml");

        var interactions = getStringList("interactions.axe");

        if(interactions.isEmpty())
            throw new InvalidConfigException("'interactions.axe' must contain at least one block");

        return interactions.stream().map(Material::valueOf).collect(Collectors.toList());
    }

    public List<Material> getHoeInteractions() throws InvalidConfigException {
        if(!contains("interactions.hoe"))
            throw new InvalidConfigException("You must specify 'interactions.hoe' blocks in config.yml");

        var interactions = getStringList("interactions.hoe");

        if(interactions.isEmpty())
            throw new InvalidConfigException("'interactions.hoe' must contain at least one block");

        return interactions.stream().map(Material::valueOf).collect(Collectors.toList());
    }

    public List<Material> getShearsInteractions() throws InvalidConfigException {
        if(!contains("interactions.shears"))
            throw new InvalidConfigException("You must specify 'interactions.shears' blocks in config.yml");

        var interactions = getStringList("interactions.shears");

        if(interactions.isEmpty())
            throw new InvalidConfigException("'interactions.shears' must contain at least one block");

        return interactions.stream().map(Material::valueOf).collect(Collectors.toList());
    }

}
