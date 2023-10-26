package jktech.betterthanbetter.packets;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.net.packet.Packet140TileEntityData;
import jktech.betterthanbetter.entities.TilePaint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class packet144EntityData extends Packet {
	public CompoundTag tag;

	public packet144EntityData() {
		this.isChunkDataPacket = true;
	}

	public packet144EntityData(TilePaint entity) {
		this();
		this.tag = new CompoundTag();
		entity.writeToNBT(this.tag);
	}

	@Override
	public void readPacketData(DataInputStream dis) throws IOException {
		this.tag = Packet140TileEntityData.readCompressedCompoundTag(dis);
	}

	@Override
	public void writePacketData(DataOutputStream dos) throws IOException {
		Packet140TileEntityData.writeCompressedCompoundTag(this.tag, dos);
	}

	@Override
	public void processPacket(NetHandler netHandler) {
		((BetterNetHandler)(netHandler)).handleTileEntityData(this);
	}

	@Override
	public int getPacketSize() {
		return 0;
	}
}
