package jktech.betterthanbetter.packets;


import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NetHandler.class)
public class BetterNetHandler extends NetHandler{

	public void handleTileEntityData(packet144EntityData packet) {
		this.handleInvalidPacket(packet);
	}

	@Override
	public boolean isServerHandler() {
		return false;
	}

	public void handleInvalidPacket(Packet packet) {
	}
}
