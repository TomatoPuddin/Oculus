package net.coderbot.iris.mixin.texture;

import net.coderbot.iris.texture.format.TextureFormatLoader;
import net.coderbot.iris.texture.pbr.PBRTextureManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Executor;

@Mixin(TextureManager.class)
public class MixinTextureManager {
	@Inject(method = "m_118482_", at = @At("TAIL"))
	private void iris$onTailReloadLambda(ResourceManager resourceManager, Executor applyExecutor, Void void1, CallbackInfo ci) {
		TextureFormatLoader.reload(resourceManager);
		PBRTextureManager.INSTANCE.clear();
	}

	@Inject(method = "close()V", at = @At("TAIL"), remap = false)
	private void iris$onTailClose(CallbackInfo ci) {
		PBRTextureManager.INSTANCE.close();
	}
}
