package thaumicenergistics.wireless;

import java.util.ArrayList;
import java.util.List;

import appeng.api.implementations.tiles.IWirelessAccessPoint;
import appeng.core.localization.PlayerMessages;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.util.FakePlayer;
import thaumicenergistics.api.wireless.IEssentiaTermWirelessHandler;
import thaumicenergistics.api.wireless.IThEWirelessHandler;
import thaumicenergistics.api.wireless.IThEWirelessObject;
import thaumicenergistics.client.gui.GuiHandler;
import thaumicenergistics.init.ModGUIs;
import thaumicenergistics.util.ThELog;

/**
 * @author BrockWS
 */
public class EssentiaTermWirelessHandler implements IEssentiaTermWirelessHandler {

    private List<IThEWirelessObject> registeredObjects;

    public EssentiaTermWirelessHandler() {
        this.registeredObjects = new ArrayList<>();
    }

    @Override
    public void openGUI(Object obj, EntityPlayer player) {
        // GuiHandler.openGUI(this.getGUI(), player, this.getPos());ModGUIs.ESSENTIA_TERMINAL;
        ThELog.info("Opening GUI");
        GuiHandler.openGUI(ModGUIs.ESSENTIA_TERMINAL, player);
    }

    @Override
    public List<IThEWirelessObject> getRegisteredObjects() {
        return this.registeredObjects;
    }

//    public void openWirelessTerminalGui( final EntityPlayer player )
//    {
//        // Valid player?
//        if( ( player == null ) || ( player instanceof FakePlayer) )
//        {
//            return;
//        }
//
//        // Ignored client side
//        if( player.worldObj.isRemote )
//        {
//            return;
//        }
//
//        // Get the item the player is holding.
//        ItemStack wirelessTerminal = player.getHeldItem(EnumHand.MAIN_HAND);
//
//        // Ensure the stack is valid
//        if( ( wirelessTerminal == null ) )
//        {
//            // Invalid terminal
//            return;
//        }
//
//        // Ensure the stack's item implements the wireless interface
//        if( !( wirelessTerminal.getItem() instanceof IThEWirelessObject ) )
//        {
//            // Invalid item.
//            return;
//        }
//
//        // Get the interface
//        IThEWirelessObject terminalInterface = (IThEWirelessObject)wirelessTerminal.getItem();
//
//        // Ensure the terminal has power
//        if( terminalInterface.getAECurrentPower( wirelessTerminal ) == 0 )
//        {
//            // Terminal is dead
//            player.addChatMessage( PlayerMessages.DeviceNotPowered.get() );
//            return;
//        }
//
//        // Ensure the terminal is linked
//        if( !IEssentiaTermWirelessHandler.isTerminalLinked( terminalInterface, wirelessTerminal ) )
//        {
//            // Unlinked terminal
//            player.addChatMessage( PlayerMessages.CommunicationError.get() );
//            return;
//        }
//
//        // Get the encryption key
//        String encKey = terminalInterface.getEncryptionKey( wirelessTerminal );
//
//        // Are any AP's in range?
//        ArrayList<IWirelessAccessPoint> accessPoints = WirelessAELink.locateAPsInRangeOfPlayer( player, encKey );
//
//        // Error occured
//        if( accessPoints == null )
//        {
//            player.addChatMessage( PlayerMessages.CommunicationError.get() );
//        }
//        // None in range
//        else if( accessPoints.isEmpty() )
//        {
//            player.addChatMessage( PlayerMessages.OutOfRange.get() );
//        }
//        // Launch the gui
//        else
//        {
//            ThEGuiHandler.launchGui( ThEGuiHandler.WIRELESS_TERMINAL_ID, player, player.worldObj, (int)player.posX, (int)player.posY,
//                    (int)player.posZ, new Object[] { new HandlerWirelessEssentiaTerminal( player, encKey, terminalInterface, wirelessTerminal ) } );
//        }
//
//    }
}