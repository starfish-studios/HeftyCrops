package crispytwig.heftycrops;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HeftyCrops.MOD_ID)
public class HeftyCrops {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "HeftyCrops";

    public HeftyCrops() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
    }

    ;
}