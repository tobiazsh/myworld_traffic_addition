package at.tobiazsh.myworld.traffic_addition.components.CustomPayloads;

import at.tobiazsh.myworld.traffic_addition.MyWorldTrafficAddition;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record SignPoleRotationPayload(BlockPos pos, int rotation) implements CustomPayload {

    public static final CustomPayload.Id<SignPoleRotationPayload> Id = new CustomPayload.Id<>(Identifier.of((MyWorldTrafficAddition.MOD_ID + ".sign_pole_rotation")));
    public static final PacketCodec<RegistryByteBuf, SignPoleRotationPayload> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, SignPoleRotationPayload::pos,
            PacketCodecs.INTEGER, SignPoleRotationPayload::rotation,
            SignPoleRotationPayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return Id;
    }
}
