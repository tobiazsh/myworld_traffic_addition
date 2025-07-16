package at.tobiazsh.myworld.traffic_addition.utils;

import net.minecraft.util.math.Direction;

public class MinecraftRenderUtils {

    /**
     * Calculates the rotation angle based on the facing direction.
     */
    public static int getFacingRotation(Direction facingDirection) {
        switch (facingDirection) {
            case SOUTH -> { return 180; }
            case WEST -> { return 90; }
            case EAST -> { return 270; }
            default -> { return 0; }
        }
    }
}
