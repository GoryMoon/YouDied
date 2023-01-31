package se.gory_moon.you_died.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import se.gory_moon.you_died.YouDied;

public class DeathSplashScreen extends DeathScreenWrapper {
    private static final ResourceLocation TIMES_FONT = new ResourceLocation(YouDied.MOD_ID, "times");
    private static final Style ROOT_STYLE = Style.EMPTY.withFont(TIMES_FONT);
    private final ITextComponent deathTitle;
    private final DeathScreenWrapper deathScreen;

    private long fadeInStart;
    private long fadeOutStart;
    private long fadeInMenuStart;
    private boolean showingMenu;

    public DeathSplashScreen(DeathScreenWrapper deathScreen) {
        super(deathScreen);
        this.deathScreen = deathScreen;
        this.deathTitle = new TranslationTextComponent("you_died.death").setStyle(ROOT_STYLE);
        this.condition = () -> showingMenu;
    }

    @Override
    public void render(MatrixStack stack, int pMouseX, int pMouseY, float pPartialTick) {
        long now = Util.getMillis();

        if (fadeInStart == 0L) {
            fadeInStart = now;
        }
        if (fadeOutStart == 0L && fadeInStart + 4000 < now) {
            fadeOutStart = now;
        }
        if (fadeInMenuStart == 0L && fadeInStart + 5300 < now) {
            fadeInMenuStart = now;
            showingMenu = true;
        }

        float zoomIn = MathHelper.clamp((now - fadeInStart) / 5500.0F, 0.0F, 1.0F);

        float fadeIn = 0;
        float fadeInText = 0;

        if (fadeOutStart == 0L) {
            float fIn = (now - fadeInStart) / 1000.0F;
            fadeIn = MathHelper.clamp(fIn, 0.0F, 1.0F);
            fadeInText = MathHelper.clamp(fIn - 0.5F, 0.0F, 1.0F);
        }

        if (fadeOutStart > 0 && fadeInMenuStart == 0L) {
            float fOut = (now - fadeOutStart) / 1000.0F;
            fadeIn = MathHelper.clamp(1.0F - fOut, 0.0F, 1.0F);
            fadeInText = MathHelper.clamp(1.3F - fOut, 0.0F, 1.0F);
        }

        if (showingMenu) {
            float fOut = (now - fadeInMenuStart) / 1000.0F;
            fadeIn = MathHelper.clamp(fOut, 0.0F, 1.0F);
        }

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, fadeIn);

        for (IGuiEventListener eventListener : deathScreen.children()) {
            if (eventListener instanceof Widget) {
                ((Widget) eventListener).setAlpha(fadeIn);
            }
        }
        deathScreen.setAlpha(fadeIn);

        if (!showingMenu) {
            float centerY = this.height / 2f;
            int alpha = MathHelper.floor(MathHelper.lerp(fadeIn, 0F, 0xea)) << 24;
            this.fillGradient(stack, 0, (int) centerY - 45, this.width, (int) centerY - 25, 0x00000000, alpha);
            fill(stack, 0, (int) centerY - 25, this.width, (int) centerY + 25, alpha);
            this.fillGradient(stack, 0, (int) centerY + 25, this.width, (int) centerY + 45, alpha, 0x00000000);

            float w = font.getSplitter().stringWidth(deathTitle.getVisualOrderText()) - 1;
            float x = (this.width / 2f) - (w / 2f);
            float y = (this.height / 2f) + 2;

            float scaleZoom = MathHelper.lerp(zoomIn, 0F, 0.4F);
            float scale = 2.6F + scaleZoom;

            stack.pushPose();
            stack.translate(x + (w / 2f), y, 0);
            stack.scale(scale, scale, scale);

            int l = MathHelper.ceil(fadeInText * 255.0F) << 24;
            if ((l & 0xfc000000) != 0) {
                font.draw(stack, deathTitle, -(w/2f), -font.lineHeight, 0x008a0001 | l);
            }
            stack.popPose();
        } else {
            int l = MathHelper.ceil(fadeIn * 255.0F) << 24;
            if ((l & 0xfc000000) != 0) {
                deathScreen.render(stack, pMouseX, pMouseY, pPartialTick);
            }
        }
    }
}
