package es.davidog.rsocaddon;

import com.raoulvdberge.refinedstorage.api.IRSAPI;
import com.raoulvdberge.refinedstorage.api.RSAPIInject;
import es.davidog.rsocaddon.driver.NetworkDriver;
import li.cil.oc.api.internal.Adapter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ModRefinedStorageDriver.MODID,
        name = ModRefinedStorageDriver.NAME,
        version = ModRefinedStorageDriver.VERSION,
        dependencies = "required-after:OpenComputers@[1.6.0.4,);required-after:refinedstorage@[1.2.4,)")
public class ModRefinedStorageDriver
{
    @Mod.Instance
    public static ModRefinedStorageDriver instance;

    @RSAPIInject
    public static IRSAPI RSAPI;

    public static final String MODID = "opencomputers|refinedstorage_driver";
    public static final String NAME = "OpenComputers Addon - Refined Storage";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        li.cil.oc.api.Driver.add(new NetworkDriver());
        RSAPI.getConnectableConditions().add(tile -> tile instanceof Adapter);
    }
}
