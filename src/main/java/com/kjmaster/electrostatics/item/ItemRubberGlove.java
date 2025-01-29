package com.kjmaster.electrostatics.item;

import com.google.common.collect.Sets;
import com.kjmaster.electrostatics.Electrostatics;
import com.kjmaster.electrostatics.block.tile.EnergyStorage;
import com.kjmaster.electrostatics.block.tile.TileStaticGenerator;
import com.kjmaster.electrostatics.config.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.Set;

public class ItemRubberGlove extends ItemTool {
    static Set<Block> effectiveBlocks = Sets.newHashSet();
    private String name;

    ItemRubberGlove(ToolMaterial materialIn, Set<Block> effectiveBlocksIn, String name, int stackSize, CreativeTabs tab) {
        super(materialIn, effectiveBlocksIn);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setMaxStackSize(stackSize);
        this.name = name;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target instanceof EntityOcelot)
        {
            EntityOcelot entityOcelot = (EntityOcelot)target;

            if (entityOcelot.isEntityAlive())
            {
                if (playerIn.getCooldownTracker().hasCooldown(this)) return false;

                playerIn.swingArm(EnumHand.MAIN_HAND);
                entityOcelot.world.playSound(playerIn, entityOcelot.posX, entityOcelot.posY, entityOcelot.posZ, SoundEvents.ENTITY_CAT_AMBIENT, SoundCategory.NEUTRAL, 0.5F, 1.0F);

                Random random = new Random();
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                entityOcelot.world.spawnParticle(EnumParticleTypes.HEART, entityOcelot.posX + (double)(random.nextFloat() * entityOcelot.width * 2.0F) - (double)entityOcelot.width, entityOcelot.posY + 0.5D + (double)(random.nextFloat() * entityOcelot.height), entityOcelot.posZ + (double)(random.nextFloat() * entityOcelot.width * 2.0F) - (double)entityOcelot.width, d0, d1, d2);

                receiveEnergy(entityOcelot.world, (int) entityOcelot.posX, (int) entityOcelot.posY, (int) entityOcelot.posZ);

                if (ConfigHandler.CoolingTickPerTime > 0) playerIn.getCooldownTracker().setCooldown(this, ConfigHandler.CoolingTickPerTime);

                if (ConfigHandler.pettingBurn) pettingBurn(entityOcelot);
                if (entityOcelot.getEntityData().hasKey("pettingBurn")) {
                    System.out.println(entityOcelot.getEntityData().getInteger("pettingBurn"));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private void pettingBurn(EntityOcelot entityOcelot) {
        if (entityOcelot.getEntityData() != null && !entityOcelot.world.isRemote) {
            NBTTagCompound nbt = entityOcelot.getEntityData();
            if (nbt.hasKey("pettingBurn")) {
                int pettingTime = nbt.getInteger("pettingBurn");

                pettingTime++;

                nbt.setInteger("pettingBurn", pettingTime);
            } else {
                nbt.setInteger("pettingBurn", 1);
            }
        }
    }

    private void receiveEnergy(World world, int x, int y, int z) {
        for (int i = x; i < x + 4; i++) {
            for (int j = y; j < y + 4; j++) {
                for (int n = z; n < z + 4; n++) {
                    BlockPos pos = new BlockPos(i - 2, j - 2, n - 2);
                    TileEntity tile = world.getTileEntity(pos);
                    if (tile != null) {
                        if (tile instanceof TileStaticGenerator) {
                            TileStaticGenerator tileStaticGenerator = (TileStaticGenerator) tile;
                            EnergyStorage storage = tileStaticGenerator.getStorage();
                            if (storage.getEnergyStored() < storage.getMaxEnergyStored())
                                storage.receiveEnergy(ConfigHandler.ElectrostaticRfPerTick, false);
                        }
                    }
                }
            }
        }
    }

    void registerItemModel() {
        Electrostatics.proxy.registerItemRenderer(this, 0, name);
    }
}
