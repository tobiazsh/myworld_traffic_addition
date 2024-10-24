package at.tobiazsh.myworld.traffic_addition.components.CustomPayloads;

import at.tobiazsh.myworld.traffic_addition.MyWorldTrafficAddition;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record OpenSignPoleRotationScreenPayload(BlockPos pos) implements CustomPayload {

    public static final CustomPayload.Id<OpenSignPoleRotationScreenPayload> Id = new CustomPayload.Id<>(Identifier.of(MyWorldTrafficAddition.MOD_ID, "open_sign_pole_rotation_screen"));
    public static final PacketCodec<RegistryByteBuf, OpenSignPoleRotationScreenPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, OpenSignPoleRotationScreenPayload::pos, OpenSignPoleRotationScreenPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return Id;
    }
}