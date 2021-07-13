package me.isaiah.common.mixin;

import static me.isaiah.common.ICommonMod.LOGGER;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.fabric.FabricServer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.dynamic.RegistryOps;

@Mixin(MinecraftDedicatedServer.class)
public class MixinMinecraftServer {

    @Inject(at = @At(value = "JUMP", ordinal = 8), method = "setupServer()Z")
    void onServerStart(CallbackInfoReturnable<Boolean> callbackInfo) {
        LOGGER.info("Setting IServer instance..");
        ICommonMod.set( new FabricServer((MinecraftServer)(Object)this) );
        RegistryOps.of(null, (ResourceManager)null, null);
    }

}
