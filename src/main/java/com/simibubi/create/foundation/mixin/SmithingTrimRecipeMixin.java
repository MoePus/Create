package com.simibubi.create.foundation.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.simibubi.create.AllTags.AllItemTags;

import net.neoforged.neoforge.common.crafting.DifferenceIngredient;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;

@Mixin(SmithingTrimRecipe.class)
public class SmithingTrimRecipeMixin {
	@Unique
	private static final Ingredient NON_TRIMMABLE_ARMOR = Ingredient.of(AllItemTags.DIVING_ARMOR.tag);

	@ModifyVariable(method = "<init>", at = @At("CTOR_HEAD"), argsOnly = true, ordinal = 1)
	private Ingredient create$preventTrimmingDivingArmor(Ingredient base) {
		return new DifferenceIngredient(base, NON_TRIMMABLE_ARMOR).toVanilla();
	}
}
