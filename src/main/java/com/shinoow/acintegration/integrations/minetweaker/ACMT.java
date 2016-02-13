package com.shinoow.acintegration.integrations.minetweaker;

import static minetweaker.api.minecraft.MineTweakerMC.getItemStack;

import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.item.IngredientStack;
import minetweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import com.shinoow.abyssalcraft.api.integration.IACPlugin;

public class ACMT implements IACPlugin {

	@Override
	public String getModName() {

		return "MineTweaker 3";
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		MineTweakerAPI.registerClass(Crystallizer.class);
		MineTweakerAPI.registerClass(Transmutator.class);
		MineTweakerAPI.registerClass(CreationRitual.class);
		MineTweakerAPI.registerClass(InfusionRitual.class);
		MineTweakerAPI.registerClass(InternalNecroData.class);
	}

	// Start of borrowed code from Immersive Engineering
	// https://github.com/BluSunrize/ImmersiveEngineering/blob/master/src/main/java/blusunrize/immersiveengineering/common/util/compat/minetweaker/MTHelper.java

	public static ItemStack toStack(IItemStack iStack)
	{
		return getItemStack(iStack);
	}

	public static Object toObject(IIngredient iStack)
	{
		if (iStack == null)
			return null;
		else
		{
			if(iStack instanceof IOreDictEntry)
				return ((IOreDictEntry)iStack).getName();
			else if(iStack instanceof IItemStack)
				return getItemStack((IItemStack) iStack);
			else if(iStack instanceof IngredientStack)
			{
				IIngredient ingr = ReflectionHelper.getPrivateValue(IngredientStack.class, (IngredientStack)iStack, "ingredient");
				return toObject(ingr);
			}
			else
				return null;
		}
	}
	public static Object[] toObjects(IIngredient[] iStacks)
	{
		Object[] oA = new Object[iStacks.length];
		for(int i=0; i<iStacks.length; i++)
			oA[i] = toObject(iStacks[i]);
		return oA;
	}
	
	// End of borrowed code
}