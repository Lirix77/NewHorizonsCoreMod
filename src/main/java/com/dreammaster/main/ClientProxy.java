package com.dreammaster.main;

import static com.dreammaster.main.ConfigHandler.CONFIG_HANDLER;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;

public class ClientProxy extends CommonProxy implements IResourceManagerReloadListener {

    @Override
    public void addTexturePage() {
        if (Textures.BlockIcons.casingTexturePages[8] == null) {
            Textures.BlockIcons.casingTexturePages[8] = new ITexture[128];
        }
    }

    @Override
    public void registerResourceReload() {
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(this);
    }

    @Override
    public void registerRenderInfo() {
        MinecraftForge.EVENT_BUS.register(CONFIG_HANDLER);
        MinecraftForge.EVENT_BUS.register(new DebugHandler());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public void onResourceManagerReload(IResourceManager p_110549_1_) {

    }
}
