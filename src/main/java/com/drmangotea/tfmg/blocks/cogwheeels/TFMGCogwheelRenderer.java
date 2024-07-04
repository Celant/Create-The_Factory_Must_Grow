package com.drmangotea.tfmg.blocks.cogwheeels;

import com.drmangotea.tfmg.registry.TFMGBlocks;
import com.drmangotea.tfmg.registry.TFMGPartialModels;
import com.jozufozu.flywheel.backend.Backend;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;

public class TFMGCogwheelRenderer extends KineticBlockEntityRenderer<SimpleKineticBlockEntity> {

	public TFMGCogwheelRenderer(Context context) {
		super(context);
	}

	@Override
	protected void renderSafe(SimpleKineticBlockEntity be, float partialTicks, PoseStack ms,
		MultiBufferSource buffer, int light, int overlay) {

		if (Backend.canUseInstancing(be.getLevel()))
			return;

		if (!AllBlocks.LARGE_COGWHEEL.has(be.getBlockState())) {
			super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
			return;
		}


		Axis axis = getRotationAxisOf(be);
		Direction facing = Direction.fromAxisAndDirection(axis, AxisDirection.POSITIVE);

		PartialModel model = be.getBlockState().is(TFMGBlocks.LARGE_ALUMINUM_COGWHEEL.get()) ? TFMGPartialModels.LARGE_ALUMINUM_COGHWEEL : TFMGPartialModels.LARGE_STEEL_COGHWEEL;


		renderRotatingBuffer(be,
			CachedBufferer.partialFacingVertical(model, be.getBlockState(), facing),
			ms, buffer.getBuffer(RenderType.cutoutMipped()), light);

		float angle = getAngleForLargeCogShaft(be, axis);
		SuperByteBuffer shaft =
			CachedBufferer.partialFacingVertical(AllPartialModels.COGWHEEL_SHAFT, be.getBlockState(), facing);
		kineticRotationTransform(shaft, be, axis, angle, light);
		shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));

	}

	public static float getAngleForLargeCogShaft(SimpleKineticBlockEntity be, Axis axis) {
		BlockPos pos = be.getBlockPos();
		float offset = getShaftAngleOffset(axis, pos);
		float time = AnimationTickHolder.getRenderTime(be.getLevel());
		float angle = ((time * be.getSpeed() * 3f / 10 + offset) % 360) / 180 * (float) Math.PI;
		return angle;
	}

	public static float getShaftAngleOffset(Axis axis, BlockPos pos) {
		float offset = 0;
		double d = (((axis == Axis.X) ? 0 : pos.getX()) + ((axis == Axis.Y) ? 0 : pos.getY())
			+ ((axis == Axis.Z) ? 0 : pos.getZ())) % 2;
		if (d == 0)
			offset = 22.5f;
		return offset;
	}

}