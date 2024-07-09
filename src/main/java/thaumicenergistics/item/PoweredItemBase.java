package thaumicenergistics.item;

import appeng.api.config.AccessRestriction;
import appeng.api.config.Actionable;
import appeng.api.implementations.items.IAEItemPowerStorage;
import appeng.util.Platform;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class PoweredItemBase extends ItemBase implements IAEItemPowerStorage {
    private static final String CURRENT_POWER_NBT_KEY = "internalCurrentPower";
    private static final String MAX_POWER_NBT_KEY = "internalMaxPower";

    private final double powerCapacity;

    public PoweredItemBase(final double powerCapacity, String id) {
//        super(powerCapacity);
        super(id);
        this.setMaxStackSize(1);
        this.setMaxDamage(32);
        this.hasSubtypes = false;
        this.setFull3D();

        this.powerCapacity = powerCapacity;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    protected void getCheckedSubItems(final CreativeTabs creativeTab, final NonNullList<ItemStack> itemStacks) {
        super.getCheckedSubItems(creativeTab, itemStacks);

        final ItemStack charged = new ItemStack(this, 1);
        final NBTTagCompound tag = Platform.openNbtData(charged);
        tag.setDouble(CURRENT_POWER_NBT_KEY, this.getAEMaxPower(charged));
        tag.setDouble(MAX_POWER_NBT_KEY, this.getAEMaxPower(charged));

        itemStacks.add(charged);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(final ItemStack is) {
        return 1 - this.getAECurrentPower(is) / this.getAEMaxPower(is);
    }

    @Override
    public boolean isDamaged(final ItemStack stack) {
        return true;
    }

    @Override
    public void setDamage(final ItemStack stack, final int damage) {

    }

    @Override
    public double injectAEPower(final ItemStack is, final double amount, Actionable mode) {
        final double maxStorage = this.getAEMaxPower(is);
        final double currentStorage = this.getAECurrentPower(is);
        final double required = maxStorage - currentStorage;
        final double overflow = amount - required;

        if (mode == Actionable.MODULATE) {
            final NBTTagCompound data = Platform.openNbtData(is);
            final double toAdd = Math.min(amount, required);

            data.setDouble(CURRENT_POWER_NBT_KEY, currentStorage + toAdd);
        }

        return Math.max(0, overflow);
    }

    @Override
    public double extractAEPower(final ItemStack is, final double amount, Actionable mode) {
        final double currentStorage = this.getAECurrentPower(is);
        final double fulfillable = Math.min(amount, currentStorage);

        if (mode == Actionable.MODULATE) {
            final NBTTagCompound data = Platform.openNbtData(is);

            data.setDouble(CURRENT_POWER_NBT_KEY, currentStorage - fulfillable);
        }

        return fulfillable;
    }

    @Override
    public double getAEMaxPower(final ItemStack is) {
        return this.powerCapacity;
    }

    @Override
    public double getAECurrentPower(final ItemStack is) {
        final NBTTagCompound data = Platform.openNbtData(is);

        return data.getDouble(CURRENT_POWER_NBT_KEY);
    }

    @Override
    public AccessRestriction getPowerFlow(final ItemStack is) {
        return AccessRestriction.WRITE;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new PoweredItemCapabilities(stack, this);
    }

//    @Override
//    public double injectAEPower(ItemStack itemStack, double v, Actionable actionable) {
//        return 0;
//    }
//
//    @Override
//    public double extractAEPower(ItemStack itemStack, double v, Actionable actionable) {
//        return 0;
//    }
//
//    @Override
//    public double getAEMaxPower(ItemStack itemStack) {
//        return 0;
//    }
//
//    @Override
//    public double getAECurrentPower(ItemStack itemStack) {
//        return 0;
//    }
//
//    @Override
//    public AccessRestriction getPowerFlow(ItemStack itemStack) {
//        return null;
//    }
}
