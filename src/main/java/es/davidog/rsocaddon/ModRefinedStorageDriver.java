package es.davidog.rsocaddon;

import es.davidog.rsocaddon.driver.DiskDriveDriver;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ModRefinedStorageDriver.MODID,
        name = ModRefinedStorageDriver.NAME,
        version = ModRefinedStorageDriver.VERSION,
        dependencies = "required-after:OpenComputers@[1.6.0.4,)")
public class ModRefinedStorageDriver
{
    @Mod.Instance
    public static ModRefinedStorageDriver instance;

    public static final String MODID = "OpenComputers|example_block_driver";
    public static final String NAME = "OpenComputers Addon - Refined Storage";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        li.cil.oc.api.Driver.add(new DiskDriveDriver());
    }
}
