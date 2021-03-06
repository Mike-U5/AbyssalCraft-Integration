package com.shinoow.acintegration.integrations.tinkers;

import static com.shinoow.acintegration.integrations.tinkers.ACTiCon.*;

import com.shinoow.abyssalcraft.api.item.ACItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.smeltery.block.BlockTinkerFluid;

/**
 * This class exists to move fields from the main plugin class that can trigger a NoClassDefFoundError when the<br>
 * IntegrationHandler scans through the plugin. You don't need do to this if the plugin is directly present in<br>
 * your mod, as the classes for your stuff will be present.
 */
public class ACTiConMisc {

	public static Material abyssalnite, coralium, dreadium;

	public static final AbstractTrait dread_plague = new TraitDreadPlague();
	public static final AbstractTrait coralium_plague = new TraitCoraliumPlague();
	public static final AbstractTrait dread_purity = new TraitDreadPurity();

	public static void preInit(){
		fluid_molten_abyssalnite = new FluidMolten("moltenAbyssalnite", 0x4a1c89);
		fluid_molten_abyssalnite.setTemperature(700);
		FluidRegistry.registerFluid(fluid_molten_abyssalnite);
		FluidRegistry.addBucketForFluid(fluid_molten_abyssalnite);
		fluid_molten_coralium = new FluidMolten("moltenRefinedCoralium", 0x169265);
		fluid_molten_coralium.setTemperature(800);
		FluidRegistry.registerFluid(fluid_molten_coralium);
		FluidRegistry.addBucketForFluid(fluid_molten_coralium);
		fluid_molten_dreadium = new FluidMolten("moltenDreadium", 0x880101);
		fluid_molten_dreadium.setTemperature(900);
		FluidRegistry.registerFluid(fluid_molten_dreadium);
		FluidRegistry.addBucketForFluid(fluid_molten_dreadium);

		moltenAbyssalnite = new BlockTinkerFluid(fluid_molten_abyssalnite, net.minecraft.block.material.Material.LAVA);
		moltenCoralium = new BlockTinkerFluid(fluid_molten_coralium, net.minecraft.block.material.Material.LAVA);
		moltenDreadium = new BlockTinkerFluid(fluid_molten_dreadium, net.minecraft.block.material.Material.LAVA);

		abyssalnite = new Material("abyssalnite", 0x4a1c89);
		coralium = new Material("refined_coralium", 0x169265);
		dreadium = new Material("dreadium", 0x880101);

		TinkerRegistry.addMaterialStats(abyssalnite,
				new HeadMaterialStats(630, 10.00f, 6.00f, 4),
				new HandleMaterialStats(0.90f, 60),
				new ExtraMaterialStats(100),
				new BowMaterialStats(0.85f, 1.1f, 1.5f));
		TinkerRegistry.integrate(abyssalnite, fluid_molten_abyssalnite, "Abyssalnite").toolforge().preInit();

		TinkerRegistry.addMaterialStats(coralium,
				new HeadMaterialStats(900, 12.00f, 7.00f, 5),
				new HandleMaterialStats(0.90f, 60),
				new ExtraMaterialStats(100),
				new BowMaterialStats(0.75f, 1.2f, 2.5f));
		TinkerRegistry.integrate(coralium, fluid_molten_coralium, "LiquifiedCoralium").toolforge().preInit();

		TinkerRegistry.addMaterialStats(dreadium,
				new HeadMaterialStats(1150, 14.00f, 8.00f, 6),
				new HandleMaterialStats(0.90f, 60),
				new ExtraMaterialStats(100),
				new BowMaterialStats(0.65f, 1.3f, 3.5f));
		TinkerRegistry.integrate(dreadium, fluid_molten_dreadium, "Dreadium").toolforge().preInit();
	}

	public static void init(){
		abyssalnite.addCommonItems("Abyssalnite");
		abyssalnite.setRepresentativeItem(new ItemStack(ACItems.abyssalnite_ingot));
		abyssalnite.addTrait(dread_purity);

		coralium.addCommonItems("LiquifiedCoralium");
		coralium.setRepresentativeItem(new ItemStack(ACItems.refined_coralium_ingot));
		coralium.addTrait(coralium_plague);

		dreadium.addCommonItems("Dreadium");
		dreadium.setRepresentativeItem(new ItemStack(ACItems.dreadium_ingot));
		dreadium.addTrait(dread_plague);
	}
}