package se.gory_moon.you_died.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Style;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.BooleanSupplier;

public class DeathScreenWrapper extends DeathScreen {

    private final DeathScreen deathScreen;
    private float alpha;

    protected BooleanSupplier condition = () -> true;

    public DeathScreenWrapper(DeathScreen deathScreen) {
        super(null, deathScreen.hardcore);
        this.deathScreen = deathScreen;
    }

    @Override
    protected void init() {
        //noinspection ConstantConditions
        deathScreen.init(minecraft, width, height);
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        int alphaColor = Mth.ceil(this.alpha * 255.0F) << 24;

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0x60500000, 0xa0803030);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(2.0F, 2.0F, 2.0F);
        guiGraphics.drawCenteredString(this.font, deathScreen.getTitle(), this.width / 2 / 2, 30, 0xffffff | alphaColor);
        guiGraphics.pose().popPose();
        if (deathScreen.causeOfDeath != null) {
            guiGraphics.drawCenteredString(this.font, deathScreen.causeOfDeath, this.width / 2, 85, 0xffffff | alphaColor);
        }

        guiGraphics.drawCenteredString(this.font, deathScreen.deathScore, this.width / 2, 100, 0xffffff | alphaColor);
        if (deathScreen.causeOfDeath != null && pMouseY > 85 && pMouseY < 85 + 9) {
            Style style = getClickedComponentStyleAt(pMouseX);
            guiGraphics.renderComponentHoverEffect(this.font, style, pMouseX, pMouseY);
        }

        // Sets the alpha on all widgets
        for (GuiEventListener guieventlistener : deathScreen.children()) {
            if (guieventlistener instanceof AbstractWidget) {
                ((AbstractWidget) guieventlistener).setAlpha(alpha);
            }
        }

        // Renders all renderables without calling super
        for(Renderable renderable : deathScreen.renderables) {
            renderable.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        }
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (condition.getAsBoolean())
            return deathScreen.mouseClicked(pMouseX, pMouseY, pButton);
        return false;
    }

    @Override
    public void tick() {
        if (condition.getAsBoolean())
            deathScreen.tick();
    }

    @Override
    public void removed() {
        if (condition.getAsBoolean())
            deathScreen.removed();
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (condition.getAsBoolean())
            return deathScreen.mouseReleased(pMouseX, pMouseY, pButton);
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (condition.getAsBoolean())
            return deathScreen.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pScrollX, double pScrollY) {
        if (condition.getAsBoolean())
            return deathScreen.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
        return super.mouseScrolled(pMouseX, pMouseY, pScrollX, pScrollY);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (condition.getAsBoolean())
            return deathScreen.keyPressed(pKeyCode, pScanCode, pModifiers);
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void afterMouseMove() {
        if (condition.getAsBoolean())
            deathScreen.afterMouseMove();
    }

    @Override
    public void afterMouseAction() {
        if (condition.getAsBoolean())
            deathScreen.afterMouseAction();
    }

    @Override
    public void afterKeyboardAction() {
        if (condition.getAsBoolean())
            deathScreen.afterKeyboardAction();
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (condition.getAsBoolean())
            return deathScreen.keyReleased(pKeyCode, pScanCode, pModifiers);
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        if (condition.getAsBoolean())
            deathScreen.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public @NotNull Optional<GuiEventListener> getChildAt(double pMouseX, double pMouseY) {
        if (condition.getAsBoolean())
            return deathScreen.getChildAt(pMouseX, pMouseY);
        return super.getChildAt(pMouseX, pMouseY);
    }
}
