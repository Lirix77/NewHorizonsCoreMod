package com.dreammaster.scripts;

import static gregtech.api.enums.Mods.ExtraUtilities;
import static gregtech.api.enums.Mods.TinkerConstruct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.EnumChatFormatting;

import com.dreammaster.main.MainRegistry;

public class ScriptLoader {

    public static void run() {
        IScriptLoader.missing.setStackDisplayName(EnumChatFormatting.RED + "Missing item! Please report it on github!");

        List<IScriptLoader> scripts = new ArrayList<>(
                Arrays.asList(
                        new ScriptAdvancedSolarPanel(),
                        new ScriptAE2FC(),
                        new ScriptAFSU(),
                        new ScriptAmunRa(),
                        new ScriptAppliedEnergistics2(),
                        new ScriptBinniesCore(),
                        new ScriptBinniesMods(),
                        new ScriptBiomesOPlenty(),
                        new ScriptCarpentersBlocks(),
                        new ScriptChisel(),
                        new ScriptCoreMod(),
                        new ScriptDraconicEvolution(),
                        new ScriptExtraTrees(),
                        new ScriptForestry(),
                        new ScriptForgeMultipart(),
                        new ScriptGalacticraft(),
                        new ScriptGendustry(),
                        new ScriptGraviSuite(),
                        new ScriptGregtech(),
                        new ScriptGregtechPlusPlus(),
                        new ScriptIndustrialCraft(),
                        new ScriptSuperSolarPanels(),
                        new ScriptTinkersConstruct()));

        ArrayList<String> errored = new ArrayList<>();
        final long totalTimeStart = System.currentTimeMillis();
        for (IScriptLoader script : scripts) {
            if (script.isScriptLoadable()) {
                try {
                    final long timeStart = System.currentTimeMillis();
                    script.loadRecipes();
                    final long timeToLoad = System.currentTimeMillis() - timeStart;
                    MainRegistry.Logger.info("Loaded " + script.getScriptName() + " script in " + timeToLoad + " ms.");
                } catch (Exception ex) {
                    errored.add(script.getScriptName());
                    MainRegistry.Logger.error(
                            "There was an error while loading " + script.getScriptName() + "! Printing stacktrace:");
                    ex.printStackTrace();
                }
            } else {
                MainRegistry.Logger.info(
                        "Missing dependencies to load " + script.getScriptName() + " script. It won't be loaded.");
            }
        }
        final long totalTimeToLoad = System.currentTimeMillis() - totalTimeStart;
        MainRegistry.Logger.info("Script loader took " + totalTimeToLoad + " ms.");
        if (!errored.isEmpty()) throw new RuntimeException(
                "Scripts " + errored + " thrown an exception! Scroll up the log to see the stacktrace!");
    }
}
