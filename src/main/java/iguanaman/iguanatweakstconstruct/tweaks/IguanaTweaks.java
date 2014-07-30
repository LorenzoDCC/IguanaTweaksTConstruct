package iguanaman.iguanatweakstconstruct.tweaks;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import iguanaman.iguanatweakstconstruct.reference.Config;
import iguanaman.iguanatweakstconstruct.reference.Reference;
import iguanaman.iguanatweakstconstruct.tweaks.handler.FlintHandler;
import iguanaman.iguanatweakstconstruct.util.Log;
import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 * Various Tweaks for Tinkers Construct and Vanilla Minecraft. See Config.
 */

@Pulse(id = Reference.PULSE_TWEAKS, description = "Various Tweaks for vanilla Minecraft and Tinker's Construct. See Config.")
public class IguanaTweaks {

    @Handler
    public void postInit(FMLPostInitializationEvent event)
    {
        FlintTweaks();
    }

    private void FlintTweaks()
    {
        if(Config.removeFlintDrop) {
            Log.info("Removing Flint drops from Gravel");
            MinecraftForge.EVENT_BUS.register(new FlintHandler());
        }

        if(Config.addFlintRecipe) {
            Log.info("Adding shapeless Flint recipe from " + Config.recipeGravelPerFlint + " Gravel");
            // create recipe
            ItemStack[] recipe = new ItemStack[Config.recipeGravelPerFlint];
            for(int i = 0; i < Config.recipeGravelPerFlint; i++)
                recipe[i] = new ItemStack(Blocks.gravel);

            // add recipe
            GameRegistry.addShapelessRecipe(new ItemStack(Items.flint), recipe);
        }
    }
}