package com.shinoow.acintegration.integrations.minetweaker;

import net.minecraft.entity.EntityLivingBase;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import com.shinoow.abyssalcraft.api.entity.EntityUtil;
import com.shinoow.abyssalcraft.common.util.ACLogger;

import crafttweaker.IAction;

@ZenClass("mods.abyssalcraft.shoggoth")
public class Shoggoth {

	@ZenMethod
	public static void addShoggothFood(String clazz){
		try {
			ACMTMisc.ADDITIONS.add(new AddFood((Class<? extends EntityLivingBase>) Class.forName(clazz)));
		} catch (ClassNotFoundException e) {
			ACLogger.warning("Could not find Entity class %s", clazz);
		}
	}

	private static class AddFood implements IAction {

		private Class<? extends EntityLivingBase> clazz;

		public AddFood(Class<? extends EntityLivingBase> clazz){
			this.clazz = clazz;
		}

		@Override
		public void apply() {

			EntityUtil.addShoggothFood(clazz);
		}

		@Override
		public String describe() {

			return "Adding Entity Class " + clazz.getCanonicalName() + " to the Lesser Shoggoth food list";
		}
	}
}