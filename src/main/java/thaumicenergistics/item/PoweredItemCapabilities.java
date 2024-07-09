package thaumicenergistics.item;

import appeng.api.config.Actionable;
import appeng.api.config.PowerUnits;
import appeng.api.implementations.items.IAEItemPowerStorage;
import appeng.capabilities.Capabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;


import javax.annotation.Nullable;

class PoweredItemCapabilities implements ICapabilityProvider, IEnergyStorage {

    private final ItemStack is;

    private final IAEItemPowerStorage item;

    private final Object teslaAdapter;

    PoweredItemCapabilities(ItemStack is, IAEItemPowerStorage item) {
        this.is = is;
        this.item = item;
        this.teslaAdapter = null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == Capabilities.FORGE_ENERGY; //|| capability == Capabilities.TESLA_CONSUMER || capability == Capabilities.TESLA_HOLDER;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == Capabilities.FORGE_ENERGY) {
            return (T) this;
        } // else if (capability == Capabilities.TESLA_CONSUMER || capability == Capabilities.TESLA_HOLDER) {
            // return (T) this.teslaAdapter;
        //}
        return null;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        final double convertedOffer = PowerUnits.RF.convertTo(PowerUnits.AE, maxReceive);
        final double overflow = this.item.injectAEPower(this.is, convertedOffer, simulate ? Actionable.SIMULATE : Actionable.MODULATE);

        return maxReceive - (int) PowerUnits.AE.convertTo(PowerUnits.RF, overflow);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return (int) PowerUnits.AE.convertTo(PowerUnits.RF, this.item.getAECurrentPower(this.is));
    }

    @Override
    public int getMaxEnergyStored() {
        return (int) PowerUnits.AE.convertTo(PowerUnits.RF, this.item.getAEMaxPower(this.is));
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}