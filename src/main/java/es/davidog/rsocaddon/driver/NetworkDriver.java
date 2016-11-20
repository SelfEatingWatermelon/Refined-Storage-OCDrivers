package es.davidog.rsocaddon.driver;

import com.raoulvdberge.refinedstorage.api.network.INetworkMaster;
import com.raoulvdberge.refinedstorage.api.network.INetworkNode;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.stream.Collectors;

/**
 * Created by David on 17/11/2016.
 */
public class NetworkDriver extends DriverSidedTileEntity {
    @Override
    public Class<?> getTileEntityClass() {
        return INetworkNode.class;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        return new Environment((INetworkNode) world.getTileEntity(pos));
    }

    public class Environment extends li.cil.oc.api.prefab.ManagedEnvironment {
        protected final INetworkNode rsNode;
        protected final INetworkMaster rsNetwork;

        public Environment(INetworkNode rsNode) {
            this.rsNode = rsNode;
            this.rsNetwork = rsNode.getNetwork();
            setNode(Network.newNode(this, Visibility.Network).withComponent("rs_network", Visibility.Network).create());
        }

        @Callback(doc = "function():table -- Add to the filter the specified item")
        public Object[] listItems(Context context, Arguments arguments) {
            return new Object[] {rsNetwork.getItemStorageCache().getList().getStacks()};
        }
    }
}
