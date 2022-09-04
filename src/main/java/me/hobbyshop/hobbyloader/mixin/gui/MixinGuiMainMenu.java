package me.hobbyshop.hobbyloader.mixin.gui;

import me.hobbyshop.hobbyloader.ui.MainMenuExtension;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu {

    private final MainMenuExtension menu = new MainMenuExtension();

    @Inject(method = "initGui", at = @At("RETURN"))
    public void initGui(CallbackInfo ci) {
        menu.init();
    }

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V", shift = At.Shift.BEFORE))
    public void drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        menu.drawTextAndButtons(mouseX, mouseY);
    }

    @Inject(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;mouseClicked(III)V", shift = At.Shift.AFTER))
    public void mouseClicked(int mouseX, int mouseY, int mouseButton, CallbackInfo ci) {
        menu.mouseClicked(mouseX, mouseY);
    }

    // Removed realms hook so the realms gui doesn't open when mods button was clicked
    @Overwrite
    private void switchToRealms() {}

}
