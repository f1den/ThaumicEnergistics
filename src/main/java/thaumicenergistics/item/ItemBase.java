package thaumicenergistics.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.dv.minecraft.thaumicenergistics.thaumicenergistics.Reference;
import thaumicenergistics.init.ModGlobals;

/**
 * @author BrockWS
 */
public abstract class ItemBase extends Item {

    public ItemBase(String id) {
        this(id, true);
    }

    public ItemBase(String id, boolean setCreativeTab) {
        this.setRegistryName(id);
        this.setTranslationKey(Reference.MOD_ID + "." + id);
        if (setCreativeTab)
            this.setCreativeTab(ModGlobals.CREATIVE_TAB);
    }


    public void getSubItems(final CreativeTabs creativeTab, final NonNullList<ItemStack> itemStacks) {
        if (this.isInCreativeTab(creativeTab)) {
            this.getCheckedSubItems(creativeTab, itemStacks);
        }
    }
    protected void getCheckedSubItems(final CreativeTabs creativeTab, final NonNullList<ItemStack> itemStacks) {
        super.getSubItems(creativeTab, itemStacks);
    }
}
