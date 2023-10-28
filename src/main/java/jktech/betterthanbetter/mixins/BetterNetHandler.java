package jktech.betterthanbetter.mixins;


import jktech.betterthanbetter.packets.packet144EntityData;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NetHandler.class)
public class BetterNetHandler{

	public void handleTileEntityData(packet144EntityData packet) {
		handleInvalidPacket(packet);
	}
	public boolean isServerHandler() {
		return false;
	}

	public void handleInvalidPacket(Packet packet) {
	}
}
