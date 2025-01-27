package com.kjmaster.electrostatics.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ServerMotionPacket implements IMessage {

    int motionX;
    int motionY;
    boolean isglass;

    public ServerMotionPacket() {}

    public ServerMotionPacket(int motionX, int motionY, boolean isglass) {
        this.motionX = motionX;
        this.motionY = motionY;
        this.isglass = isglass;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        motionX = buf.readInt();
        motionY = buf.readInt();
        isglass = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(motionX);
        buf.writeInt(motionY);
        buf.writeBoolean(isglass);
    }
}
