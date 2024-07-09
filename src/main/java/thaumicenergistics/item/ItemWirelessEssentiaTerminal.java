package thaumicenergistics.item;

import javax.annotation.Nonnull;

import appeng.api.config.PowerMultiplier;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import net.minecraftforge.client.model.ModelLoader;

import org.dv.minecraft.thaumicenergistics.thaumicenergistics.Reference;
import thaumicenergistics.api.ThEApi;
import thaumicenergistics.api.wireless.IEssentiaTermWirelessHandler;
import thaumicenergistics.api.wireless.IThEWirelessHandler;
import thaumicenergistics.api.wireless.IThEWirelessObject;
import thaumicenergistics.client.render.IThEModel;

/**
 * @author BrockWS
 */
public class ItemWirelessEssentiaTerminal extends PoweredItemBase implements IThEWirelessObject, IThEModel {

    /**
     * NBT keys
     */
    private static final String NBT_AE_SOURCE_KEY = "SourceKey";

    /**
     * Amount of power the wireless terminal can store.
     */
    private static final int POWER_STORAGE = 1600000;

    /**
     * Used during power calculations.
     */
    public static double GLOBAL_POWER_MULTIPLIER = PowerMultiplier.CONFIG.multiplier;

    public ItemWirelessEssentiaTerminal(String id) {
        super(POWER_STORAGE, id);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ThEApi.instance().wireless().openGUI(player.getHeldItem(hand), player);
        // GuiHandler.openGUI(this.getGUI(), player, this.getPos());ModGUIs.ESSENTIA_TERMINAL;
        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public boolean isHandledBy(IThEWirelessHandler handler, Object obj, EntityPlayer player) {
        return handler == ThEApi.instance().wireless().getWirelessHandler(IEssentiaTermWirelessHandler.class);
    }

    @Override
    public boolean hasPower(double amount) {
        return false;
    }

    @Override
    public boolean usePower(double amount) {
        return false;
    }

    private NBTTagCompound getOrCreateCompoundTag(final ItemStack wirelessTerminal ) {
        NBTTagCompound dataTag;
        // Ensure the terminal has a tag
        if( !wirelessTerminal.hasTagCompound() ) {
            // Create a new tag.
            wirelessTerminal.setTagCompound( ( dataTag = new NBTTagCompound() ) );
        } else {
            // Get the tag
            dataTag = wirelessTerminal.getTagCompound();
        }
        return dataTag;
    }

    /**
     * Gets the encryption, or source, key for the specified terminal.
     *
     * @param wirelessTerminal
     * @return
     */
    @Override
    public String getEncryptionKey( final ItemStack wirelessTerminal ) {
        // Ensure the terminal has a tag
        if( wirelessTerminal.hasTagCompound() ) {
            // Get the security terminal source key
            String sourceKey = wirelessTerminal.getTagCompound().getString( ItemWirelessEssentiaTerminal.NBT_AE_SOURCE_KEY );
            // Ensure the source is not empty nor null
            if( ( sourceKey != null ) && ( !sourceKey.isEmpty() ) ) {
                // The terminal is linked.
                return sourceKey;
            }
        }
        // Terminal is unlinked.
        return "";
    }
//    @Override
//    public String getUnlocalizedName()
//    {
//        return ThEStrings.Item_WirelessEssentiaTerminal.getUnlocalized();
//    }
//
//    @Override
//    public String getUnlocalizedName( final ItemStack itemStack )
//    {
//        return ThEStrings.Item_WirelessEssentiaTerminal.getUnlocalized();
//    }

    /**
     * Gets the data tag used to save the terminal settings a power level.
     */
    @Override
    public NBTTagCompound getWETerminalTag( final ItemStack wirelessTerminal )
    {
        return this.getOrCreateCompoundTag( wirelessTerminal );
    }

    @Override
    public void setEncryptionKey( final ItemStack wirelessTerminal, final String sourceKey, final String name ) {
        // Set the key
        this.getOrCreateCompoundTag( wirelessTerminal ).setString( ItemWirelessEssentiaTerminal.NBT_AE_SOURCE_KEY, sourceKey );
    }

    @Override
    public boolean showDurabilityBar( final ItemStack wirelessTerminal ) {
        return true;
    }
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Reference.MOD_ID + ":wireless_essentia_terminal", "inventory"));
    }
}