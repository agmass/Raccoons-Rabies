package net.cordicus.raccoons.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.cordicus.raccoons.entity.RREntityTypes;
import net.cordicus.raccoons.entity.custom.RaccoonEntity;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
//? if >=1.21.11
//import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SpawnRaccoonCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(
                Commands.literal("spawnraccoon").requires(
                        (source) -> {
                            //? if <1.21.11
                            return source.hasPermission(2);
                            //? if >=1.21.11
                            //return source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER);
                        }) // requires permissions
                        .then(Commands.argument("type", StringArgumentType.string())
                                .executes(context -> {
                                    CommandSourceStack source = context.getSource();
                                    ServerPlayer player = source.getPlayer();
                                    if (player == null) {
                                        return 0;
                                    }
                                    String type = StringArgumentType.getString(context, "type");
                                    spawnRaccoon(player.level
                                            //? if >1.19
                                            ()
                                            , player.blockPosition(), type, player);
                                    //? if <26.1
                                    player.displayClientMessage(Component.literal("Spawned a " + type + " raccoon!"), false);
                                    //? if >=26.2
                                    //player.sendSystemMessage(Component.literal("Spawned a " + type + " raccoon!"), false);
                                    return 1;
                                })
                        )
        );
    }

    private static void spawnRaccoon(Level world, BlockPos pos, String type, Player player) {
        int raccoonType = switch (type.toLowerCase()) {
            case "albino" -> 2;
            case "amethyst" -> 1;
            default -> 0;
        };
        RaccoonEntity raccoon = new RaccoonEntity(RREntityTypes.RACCOON, world);
        raccoon.setPos(pos.getX(), pos.getY(), pos.getZ());
        raccoon.setRaccoonType(raccoonType);
        world.addFreshEntity(raccoon);
    }
}
