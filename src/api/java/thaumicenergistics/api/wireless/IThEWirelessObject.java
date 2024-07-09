package thaumicenergistics.api.wireless;

import appeng.api.features.INetworkEncodable;
import appeng.api.implementations.items.IAEItemPowerStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

/**
 * @author BrockWS
 */
public interface IThEWirelessObject extends INetworkEncodable, IAEItemPowerStorage {

    @Nonnull
    NBTTagCompound getWETerminalTag(@Nonnull ItemStack terminalItemstack );

    default boolean isHandledBy(IThEWirelessHandler handler, Object obj, EntityPlayer player) {
        return true;
    }

    boolean hasPower(double amount);

    boolean usePower(double amount);

    String getEncryptionKey(ItemStack wirelessTerminal);

    void setEncryptionKey(ItemStack wirelessTerminal, String sourceKey, String name);
}
