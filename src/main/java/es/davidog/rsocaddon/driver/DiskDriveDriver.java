package es.davidog.rsocaddon.driver;

import com.raoulvdberge.refinedstorage.api.network.INetworkNode;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by David on 17/11/2016.
 */
public class DiskDriveDriver extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return INetworkNode.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        return new Environment((INetworkNode) world.getTileEntity(pos));
    }

    public class Environment extends li.cil.oc.api.prefab.ManagedEnvironment {
        protected final INetworkNode network;

        public Environment(INetworkNode network) {
            this.network = network;
            setNode(Network.newNode(this, Visibility.Network).withComponent("rs_network").create());
        }

        @Callback
        public Object[] testProxy(Context context, Arguments arguments) {
            ItemStack stack = network.getNetwork().insertItem(new ItemStack(Items.DIAMOND), 20, false);
            if (stack == null) {
                return new Object[] {"Successfully added"};
            } else {
                return new Object[] {"Addition on diamons failed"};
            }
        }
    }
}
