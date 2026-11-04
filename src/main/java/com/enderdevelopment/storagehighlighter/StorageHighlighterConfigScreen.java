package com.enderdevelopment.storagehighlighter;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class StorageHighlighterConfigScreen extends Screen {
    private TextFieldWidget colorField;

    protected StorageHighlighterConfigScreen() {
        super(Text.translatable("storagehighlighter.config.title"));
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Add color input field
        colorField = new TextFieldWidget(this.textRenderer, centerX - 100, centerY - 20, 200, 20, Text.empty());
        colorField.setText(String.format("%06X", StorageHighlighterConfig.getHighlightColor() & 0xFFFFFF));
        this.addDrawableChild(colorField);

        // Add save button
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("storagehighlighter.config.save"), button -> {
            try {
                int color = Integer.parseInt(colorField.getText(), 16);
                StorageHighlighterConfig.setHighlightColor(color);
                this.client.setScreen(null);
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        }).dimensions(centerX - 50, centerY + 10, 100, 20).build());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
    }
}