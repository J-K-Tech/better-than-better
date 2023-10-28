package jktech.betterthanbetter.entities;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import jktech.betterthanbetter.mixins.BetterWorld;
import jktech.betterthanbetter.packets.packet144EntityData;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.world.World;

import java.util.HashMap;
import java.util.Map;


public class TilePaint extends Entity {
	private static Map nameToClassMap = new HashMap();
	private static Map classToNameMap = new HashMap();
	public int direction = 0;
	public int xPosition;
	public int yPosition;
	public int zPosition;
	public final int CANVAS_WIDTH = 16;
	public final int CANVAS_HEIGHT = 16;
	public final byte[] paintColors = new byte[384];
	public ItemStack[] items = new ItemStack[3];
	public String owner = "";
	public BetterWorld worldObj;
	public int xCoord;
	public int yCoord;
	public int zCoord;
	protected boolean tileEntityInvalid;


	public TilePaint(World world) {
		super(world);
	}

	public void writeToNBT(CompoundTag nbttagcompound) {
		String s = (String)classToNameMap.get(this.getClass());
		if (s == null) {
			throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
		}
		nbttagcompound.putString("id", s);
		nbttagcompound.putInt("x", this.xCoord);
		nbttagcompound.putInt("y", this.yCoord);
		nbttagcompound.putInt("z", this.zCoord);
		this.writeFlagNBT(nbttagcompound);
	}

	public void writeFlagNBT(CompoundTag tag) {
		byte[] packedColors = this.packPaintColors(this.paintColors);
		tag.putByteArray("Colors", packedColors);
		tag.putString("Owner", this.owner);
		ListTag list = new ListTag();
		for (int i = 0; i < this.items.length; ++i) {
			if (this.items[i] == null) continue;
			CompoundTag compound = new CompoundTag();
			compound.putByte("Slot", (byte)i);
			this.items[i].writeToNBT(compound);
			list.addTag(compound);
		}
		tag.putList("Items", list);
	}private byte[] packPaintColors(byte[] unpacked) {
		byte[] packed = new byte[96];
		for (int i = 0; i < 96; ++i) {
			packed[i] = 0;
			int n = i;
			packed[n] = (byte)(packed[n] | (unpacked[i * 4 + 0] & 3) << 0);
			int n2 = i;
			packed[n2] = (byte)(packed[n2] | (unpacked[i * 4 + 1] & 3) << 2);
			int n3 = i;
			packed[n3] = (byte)(packed[n3] | (unpacked[i * 4 + 2] & 3) << 4);
			int n4 = i;
			packed[n4] = (byte)(packed[n4] | (unpacked[i * 4 + 3] & 3) << 6);
		}
		return packed;
	}
	public void setDirection(int direction) {
		this.direction = direction;
		this.yRotO = this.yRot = (float)(direction * 90);
		float sizeX = 16;
		float sizeY = 16;
		float sizeZ = 16;
		if (direction == 0 || direction == 2) {
			sizeZ = 0.5f;
		} else {
			sizeX = 0.5f;
		}
		sizeX /= 32.0f;
		sizeY /= 32.0f;
		sizeZ /= 32.0f;
		float centerX = (float)this.xPosition + 0.5f;
		float centerY = (float)this.yPosition + 0.5f;
		float centerZ = (float)this.zPosition + 0.5f;
		float offsetFromWall = 0.53f;
		if (direction == 0) {
			centerZ -= offsetFromWall;
		}
		if (direction == 1) {
			centerX -= offsetFromWall;
		}
		if (direction == 2) {
			centerZ += offsetFromWall;
		}
		if (direction == 3) {
			centerX += offsetFromWall;
		}
		if (direction == 0) {
			centerX -= this.offsetFromCenter(16);
		}
		if (direction == 1) {
			centerZ += this.offsetFromCenter(16);
		}
		if (direction == 2) {
			centerX += this.offsetFromCenter(16);
		}
		if (direction == 3) {
			centerZ -= this.offsetFromCenter(16);
		}
		this.setPos(centerX, centerY += this.offsetFromCenter(16), centerZ);
		float expand = -0.0f;
		this.bb.setBounds(centerX - sizeX - expand, centerY - sizeY - expand, centerZ - sizeZ - expand, centerX + sizeX + expand, centerY + sizeY + expand, centerZ + sizeZ + expand);
		if (direction == 0 || direction == 2) {
			this.bb.minZ -= (double)0.01f;
			this.bb.maxZ += (double)0.01f;
		}
		if (direction == 1 || direction == 3) {
			this.bb.minX -= (double)0.01f;
			this.bb.maxX += (double)0.01f;
		}
	}

	@Override
	protected void init() {

	}

	@Override
	protected void readAdditionalSaveData(CompoundTag compoundTag) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundTag compoundTag) {

	}
	private float offsetFromCenter(int i) {
		if (i == 32) {
			return 0.5f;
		}
		return i != 64 ? 0.0f : 0.5f;
	}


	private byte[] unpackFlagColors(byte[] packed) {
		byte[] unpacked = new byte[384];
		for (int i = 0; i < 96; ++i) {
			unpacked[i * 4 + 0] = (byte)((packed[i] & 3) >> 0);
			unpacked[i * 4 + 1] = (byte)((packed[i] & 0xC) >> 2);
			unpacked[i * 4 + 2] = (byte)((packed[i] & 0x30) >> 4);
			unpacked[i * 4 + 3] = (byte)((packed[i] & 0xC0) >> 6);
		}
		return unpacked;
	}

	public int getSizeInventory() {
		return 3;
	}

	public ItemStack getStackInSlot(int i) {
		if ((i -= 36) < 0 || i >= 3) {
			return null;
		}
		return this.items[i];
	}

	public ItemStack decrStackSize(int index, int numToTake) {
		if (this.items[index -= 36] != null) {
			if (this.items[index].stackSize <= numToTake) {
				ItemStack stack = this.items[index];
				this.items[index] = null;
				this.onInventoryChanged();
				return stack;
			}
			ItemStack splitStack = this.items[index].splitStack(numToTake);
			if (this.items[index].stackSize <= 0) {
				this.items[index] = null;
			}
			this.onInventoryChanged();
			return splitStack;
		}
		return null;
	}

	public Packet getDescriptionPacket() {
		return new packet144EntityData(this);
	}

	public void setInventorySlotContents(int index, ItemStack stack) {
		this.items[index -= 36] = stack;
		this.onInventoryChanged();
	}

	public void onInventoryChanged() {
		if (this.worldObj != null) {
			worldObj.updateEntityChunkAndSendToPlayer(this.xCoord, this.yCoord, this.zCoord, this);
		}
	}
	public String getInvName() {
		return "Flag";
	}

	public int getInventoryStackLimit() {
		return 1;
	}
}
