package com.kjmaster.electrostatics.block;

import com.kjmaster.electrostatics.Electrostatics;
import com.kjmaster.electrostatics.block.tile.TileJumpGenerator;
import com.kjmaster.electrostatics.block.tile.TileStaticGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockJumpGenerator extends Block implements ITileEntityProvider
{
    protected static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB((double) 1 / 16, 0.0D, (double) 1 / 16, (double) 15 / 16, (double) 1 / 16, (double) 15 / 16);

    protected BlockJumpGenerator()
    {
        super(Material.WOOD);
        this.setTickRandomly(true);
        this.setCreativeTab(Electrostatics.electrostaticTab);
        this.setTranslationKey(Electrostatics.MODID + ".jump_generator");
        this.setRegistryName("jump_generator");
    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CARPET_AABB;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileJumpGenerator();
    }


    void registerItemModel(Item itemBlock) {
        Electrostatics.proxy.registerItemRenderer(itemBlock, 0, "jump_generator");
    }

    Item createItemBlock() {
        return new ItemBlock(this).setRegistryName("jump_generator").setTranslationKey(Electrostatics.MODID + "." + "jump_generator");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileJumpGenerator();
    }
}