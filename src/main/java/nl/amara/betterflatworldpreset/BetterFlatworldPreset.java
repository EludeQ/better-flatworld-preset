package nl.amara.betterflatworldpreset;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.PresetFlatWorldScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Set;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("betterflatworldpreset")
public class BetterFlatworldPreset
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterFlatworldPreset()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {
        var presets = PresetFlatWorldScreen.PRESETS.stream().toList();
        PresetFlatWorldScreen.PRESETS.clear();
        PresetFlatWorldScreen.preset(new TranslatableComponent("betterflatworldpreset.createWorld.customize.preset.better_default"), Blocks.GRASS_BLOCK, Biomes.PLAINS, Set.of(), false, false,
                new FlatLayerInfo(1, Blocks.GRASS_BLOCK),
                new FlatLayerInfo(5, Blocks.DIRT),
                new FlatLayerInfo(64, Blocks.STONE),
                new FlatLayerInfo(64, Blocks.DEEPSLATE),
                new FlatLayerInfo(1, Blocks.BEDROCK));

        PresetFlatWorldScreen.preset(new TranslatableComponent("betterflatworldpreset.createWorld.customize.preset.better_default_decorated"), Blocks.GRASS_BLOCK, Biomes.PLAINS, Set.of(BuiltinStructureSets.VILLAGES), true, true,
                new FlatLayerInfo(1, Blocks.GRASS_BLOCK),
                new FlatLayerInfo(5, Blocks.DIRT),
                new FlatLayerInfo(64, Blocks.STONE),
                new FlatLayerInfo(64, Blocks.DEEPSLATE),
                new FlatLayerInfo(1, Blocks.BEDROCK));

        PresetFlatWorldScreen.PRESETS.addAll(presets);
    }
}
