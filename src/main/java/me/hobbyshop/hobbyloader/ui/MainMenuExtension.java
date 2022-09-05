package me.hobbyshop.hobbyloader.ui;

import me.hobbyshop.hobbyloader.HobbyLoader;
import me.hobbyshop.hobbyloader.api.mod.HobbyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;

public class MainMenuExtension extends Gui {

    private final Minecraft mc = Minecraft.getMinecraft();
    private ScaledResolution res;

    private GuiButton modButton;

    public void init() {
        res = new ScaledResolution(mc);
        modButton = new GuiButton(3, res.getScaledWidth() / 2 - 100, res.getScaledHeight() / 4 + 96, "Mods");
    }

    public void drawTextAndButtons(int mouseX, int mouseY) {
        this.modButton.drawButton(mc, mouseX, mouseY);
        this.drawString(mc.fontRendererObj, HobbyLoader.getInstance().getModManager().getMods().size() + " mods loaded", 2, res.getScaledHeight() - 20, -1);
    }

    public void mouseClicked(int mouseX, int mouseY) {
        if (modButton.mousePressed(this.mc, mouseX, mouseY))
            mc.displayGuiScreen(new UiModScreen());
    }

}
