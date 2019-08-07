/**
 *  Thaumic Augmentation
 *  Copyright (c) 2019 TheCodex6824.
 *
 *  This file is part of Thaumic Augmentation.
 *
 *  Thaumic Augmentation is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Thaumic Augmentation is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Thaumic Augmentation.  If not, see <https://www.gnu.org/licenses/>.
 */

package thecodex6824.thaumicaugmentation.common.item;

import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import thecodex6824.thaumicaugmentation.api.TAItems;
import thecodex6824.thaumicaugmentation.api.item.CapabilityMorphicTool;
import thecodex6824.thaumicaugmentation.api.item.IMorphicTool;
import thecodex6824.thaumicaugmentation.common.capability.MorphicTool;
import thecodex6824.thaumicaugmentation.common.capability.SimpleCapabilityProvider;
import thecodex6824.thaumicaugmentation.common.item.prefab.ItemTABase;

public class ItemMorphicTool extends ItemTABase {

    public ItemMorphicTool() {
        super();
        setMaxStackSize(1);
    }
    
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new SimpleCapabilityProvider<>(new MorphicTool(), CapabilityMorphicTool.MORPHIC_TOOL);
    }
    
    private IMorphicTool getTool(ItemStack stack) {
        return stack.getCapability(CapabilityMorphicTool.MORPHIC_TOOL, null);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
    
    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        if (oldStack.hasCapability(CapabilityMorphicTool.MORPHIC_TOOL, null))
            oldStack = getTool(oldStack).getFunctionalStack();
        if (newStack.hasCapability(CapabilityMorphicTool.MORPHIC_TOOL, null))
            newStack = getTool(newStack).getFunctionalStack();
        
        return oldStack.getItem().canContinueUsing(oldStack, newStack);
    }
    
    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().canDestroyBlockInCreative(world, pos, func, player);
    }
    
    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity,
            EntityLivingBase attacker) {
        
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().canDisableShield(func, shield, entity, attacker);
    }
    
    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().canHarvestBlock(state, func);
    }
    
    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().doesSneakBypassUse(func, world, pos, player);
    }
    
    @Override
    public ImmutableMap<String, ITimeValue> getAnimationParameters(ItemStack stack, World world,
            EntityLivingBase entity) {
        
        ItemStack display = getTool(stack).getDisplayStack();
        return display.getItem().getAnimationParameters(display, world, entity);
    }
    
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getAttributeModifiers(slot, func);
    }
    
    @Override
    public int getDamage(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getDamage(func);
    }
    
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getDestroySpeed(func, state);
    }
    
    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getDurabilityForDisplay(func);
    }
    
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getHarvestLevel(func, toolClass, player, blockState);
    }
    
    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }
    
    @Override
    public int getItemBurnTime(ItemStack itemStack) {
        return 0;
    }
    
    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 0;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        ItemStack display = getTool(stack).getDisplayStack();
        if (display.isEmpty())
            return super.getItemStackDisplayName(stack);
        else
            return display.getItem().getItemStackDisplayName(display);
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        ItemStack display = getTool(stack).getDisplayStack();
        return display.getItem().getItemUseAction(display);
    }
    
    @Override
    public int getMaxDamage(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getMaxDamage(func);
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getMaxItemUseDuration(func);
    }
    
    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getRGBDurabilityForDisplay(func);
    }
    
    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().getToolClasses(func);
    }
    
    @Override
    public String getTranslationKey(ItemStack stack) {
        ItemStack display = getTool(stack).getDisplayStack();
        if (display.isEmpty())
            return super.getTranslationKey(stack);
        else
            return display.getItem().getTranslationKey(display);
    }
    
    @Override
    public String getUnlocalizedNameInefficiently(ItemStack stack) {
        ItemStack display = getTool(stack).getDisplayStack();
        if (display.isEmpty())
            return super.getUnlocalizedNameInefficiently(stack);
        else
            return display.getItem().getUnlocalizedNameInefficiently(display);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack) {
        ItemStack display = getTool(stack).getDisplayStack();
        return display.getItem().hasEffect(display);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().hitEntity(func, target, attacker);
    }
    
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }
    
    @Override
    public boolean isDamageable() {
        return true;
    }
    
    @Override
    public boolean isDamaged(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().isDamaged(func);
    }
    
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    
    @Override
    public boolean isFull3D() {
        return true;
    }
    
    @Override
    public boolean isRepairable() {
        return false;
    }
    
    @Override
    public boolean isShield(ItemStack stack, EntityLivingBase entity) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().isShield(func, entity);
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target,
            EnumHand hand) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().itemInteractionForEntity(func, playerIn, target, hand);
    }
    
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
            EntityLivingBase entityLiving) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().onBlockDestroyed(func, worldIn, state, pos, entityLiving);
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        ItemStack func = getTool(itemstack).getFunctionalStack();
        return func.getItem().onBlockStartBreak(func, pos, player);
    }
    
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        ItemStack func = getTool(item).getFunctionalStack();
        return func.getItem().onDroppedByPlayer(func, player);
    }
    
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().onEntitySwing(entityLiving, func);
    }
    
    private static void setStackWithoutAnnoyingNoise(EntityLivingBase entity, EnumHand hand, ItemStack stack) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            player.inventory.mainInventory.set(player.inventory.currentItem, stack);
        }
        else
            entity.setHeldItem(hand, stack);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack func = getTool(playerIn.getHeldItem(handIn)).getFunctionalStack();
        if (func.isEmpty())
            return super.onItemRightClick(worldIn, playerIn, handIn);
        else {
            // UGLY HACK TIME!
            // we can't actually pass a stack and instead just rely on the hand, so fake it for the forwarded call
            ItemStack old = playerIn.getHeldItem(handIn);
            setStackWithoutAnnoyingNoise(playerIn, handIn, func);
            ActionResult<ItemStack> result = new ActionResult<>(func.getItem().onItemRightClick(worldIn, playerIn, handIn).getType(),
                    old);
            setStackWithoutAnnoyingNoise(playerIn, handIn, old);
            return result;
        }
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
            EnumFacing facing, float hitX, float hitY, float hitZ) {
        
        ItemStack func = getTool(player.getHeldItem(hand)).getFunctionalStack();
        if (func.isEmpty())
            return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        else {
            ItemStack old = player.getHeldItem(hand);
            setStackWithoutAnnoyingNoise(player, hand, func);
            EnumActionResult result = func.getItem().onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
            setStackWithoutAnnoyingNoise(player, hand, old);
            return result;
        }
    }
   
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        ItemStack func = getTool(stack).getFunctionalStack();
        func.getItem().onItemUseFinish(func, worldIn, entityLiving);
        return stack;
    }
    
    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
            float hitY, float hitZ, EnumHand hand) {
        
        ItemStack func = getTool(player.getHeldItem(hand)).getFunctionalStack();
        if (func.isEmpty())
            return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
        else {
            ItemStack old = player.getHeldItem(hand);
            setStackWithoutAnnoyingNoise(player, hand, func);
            EnumActionResult result = func.getItem().onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
            setStackWithoutAnnoyingNoise(player, hand, old);
            return result;
        }
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        ItemStack func = getTool(stack).getFunctionalStack();
        func.getItem().onPlayerStoppedUsing(func, worldIn, entityLiving, timeLeft);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        ItemStack func = getTool(stack).getFunctionalStack();
        func.getItem().onUpdate(func, worldIn, entityIn, itemSlot, isSelected);
    }
    
    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        ItemStack func = getTool(stack).getFunctionalStack();
        func.getItem().onUsingTick(func, player, count);
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().onLeftClickEntity(func, player, entity);
    }
    
    @Override
    public void setDamage(ItemStack stack, int damage) {
        ItemStack func = getTool(stack).getFunctionalStack();
        func.getItem().setDamage(func, damage);
    }
    
    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        if (oldStack.hasCapability(CapabilityMorphicTool.MORPHIC_TOOL, null))
            oldStack = getTool(oldStack).getFunctionalStack();
        if (newStack.hasCapability(CapabilityMorphicTool.MORPHIC_TOOL, null))
            newStack = getTool(newStack).getFunctionalStack();
        
        return oldStack.getItem().shouldCauseBlockBreakReset(oldStack, newStack);
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (oldStack.hasCapability(CapabilityMorphicTool.MORPHIC_TOOL, null))
            oldStack = getTool(oldStack).getFunctionalStack();
        if (newStack.hasCapability(CapabilityMorphicTool.MORPHIC_TOOL, null))
            newStack = getTool(newStack).getFunctionalStack();
        
        return oldStack.getItem().shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }
    
    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        ItemStack func = getTool(stack).getFunctionalStack();
        return func.getItem().showDurabilityBar(func);
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == TAItems.CREATIVE_TAB || tab == CreativeTabs.SEARCH)
            items.add(new ItemStack(this));
    }
    
    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(
                "ta_special:morphic_tool", "inventory"));
    }
    
}