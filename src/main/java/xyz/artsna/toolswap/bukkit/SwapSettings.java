package xyz.artsna.toolswap.bukkit;

public class SwapSettings {

    private boolean enabled;
    private boolean preferSilk;

    public SwapSettings(boolean enabled, boolean preferSilk) {
        this.enabled = enabled;
        this.preferSilk = preferSilk;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPreferSilk() {
        return preferSilk;
    }

    public void setPreferSilk(boolean preferSilk) {
        this.preferSilk = preferSilk;
    }

}
