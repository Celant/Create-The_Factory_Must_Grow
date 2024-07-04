package com.drmangotea.tfmg.blocks.machines.oil_processing.pumpjack.hammer.parts.large;


import com.drmangotea.tfmg.blocks.machines.oil_processing.pumpjack.hammer.parts.PumpjackHammerHeadBlock;
import com.drmangotea.tfmg.registry.TFMGShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LargePumpjackHammerHeadBlock extends PumpjackHammerHeadBlock {

    public LargePumpjackHammerHeadBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter worldIn, BlockPos pos, CollisionContext context) {

        return TFMGShapes.FULL;
    }

}
