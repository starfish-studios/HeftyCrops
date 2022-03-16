package crispytwig.heftycrops.registry;

import crispytwig.heftycrops.HeftyCrops;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = HeftyCrops.MOD_ID)
public class Config {
    public static final ForgeConfigSpec.IntValue HEFTY_CROP_WEIGHT;
    public static ForgeConfigSpec COMMON_CONFIG;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        HEFTY_CROP_WEIGHT = COMMON_BUILDER.comment("Chance of hefty crop spawning. Larger number = rarer. A value of 1 will guarantee a hefty crop spawns.").defineInRange("weight", 25, 1, 100);
        Config.COMMON_CONFIG = COMMON_BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}
