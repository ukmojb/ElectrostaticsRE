package com.kjmaster.electrostatics.event;

import com.kjmaster.electrostatics.config.ConfigHandler;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LivingEvent {

    @SubscribeEvent
    public static void onLivingUpdate(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event){
        if (ConfigHandler.pettingBurn) {
            if (event.getEntityLiving() instanceof EntityOcelot) {
                EntityOcelot entityOcelot = (EntityOcelot) event.getEntityLiving();
                if (entityOcelot.getEntityData() != null) {
                    NBTTagCompound nbt = entityOcelot.getEntityData();
                    if (entityOcelot.world.getTotalWorldTime() % 2 == 0) {
                        if (nbt.hasKey("pettingBurn")) {
                            int pettingTime = nbt.getInteger("pettingBurn");

                            if (pettingTime > ConfigHandler.pettingBurnTime) {
                                entityOcelot.setFire(5);
                            }

                            if (pettingTime > 1) pettingTime--;

                            nbt.setInteger("pettingBurn", pettingTime);
                        }
                    }
                }
            }
        }
    }
}
