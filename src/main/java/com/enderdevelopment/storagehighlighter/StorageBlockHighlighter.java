package com.enderdevelopment.storagehighlighter;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StorageBlockHighlighter {
    public static void init() {
        WorldRenderEvents.BLOCK_OUTLINE.register((context, hitResult) -> {
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = hitResult.getBlockPos();
                World world = context.world();
                BlockEntity blockEntity = world.getBlockEntity(pos);

                if (blockEntity != null && isStorageBlock(blockEntity.getType())) {
                    if (containsItems(blockEntity)) {
                        // Highlight the block
                        highlightBlock(context.matrixStack(), context.consumers(), pos, StorageHighlighterConfig.getHighlightColor());
                    }
                }
            }
        });
    }

    private static boolean isStorageBlock(BlockEntityType<?> type) {
        // Add all storage block entity types here
        return type == BlockEntityType.CHEST ||
               type == BlockEntityType.BARREL ||
               type == BlockEntityType.SHULKER_BOX;
    }

    private static boolean containsItems(BlockEntity blockEntity) {
        // Implement logic to check if the block contains items
        // This will depend on the specific block entity type
        return true; // Placeholder
    }

    private static void highlightBlock(MatrixStack matrices, VertexConsumerProvider vertexConsumers, BlockPos pos, int color) {
        WorldRenderer.drawBox(matrices, vertexConsumers.getBuffer(RenderLayer.getLines()), pos, 1.0F, 1.0F, 1.0F, color);
    }
}