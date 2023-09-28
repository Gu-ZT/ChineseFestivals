package dev.dubhe.chinesefestivals.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import dev.dubhe.chinesefestivals.festivals.Festivals;
import dev.dubhe.chinesefestivals.festivals.IFestival;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class DebugCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> command = Commands.literal("festivals").requires(commandSourceStack -> commandSourceStack.hasPermission(3)).then(DebugCommands.genCommands());
        dispatcher.register(Commands.literal("festivals").requires(commandSourceStack -> commandSourceStack.hasPermission(3)).then(command));
    }

    public static ArgumentBuilder<CommandSourceStack, LiteralArgumentBuilder<CommandSourceStack>> genCommands() {
        ArgumentBuilder<CommandSourceStack, LiteralArgumentBuilder<CommandSourceStack>> node = Commands.literal("set");
        node.then(Commands.literal("none").executes(context -> {
            ChineseFestivals.debugFestival = "none";
            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
            return 0;
        }));
        for (IFestival festival : Festivals.FESTIVALS) {
            node.then(Commands.literal(festival.getId()).executes(context -> {
                ChineseFestivals.debugFestival = festival.getId();
                context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                return 0;
            }));
        }
        return node;
    }
}
