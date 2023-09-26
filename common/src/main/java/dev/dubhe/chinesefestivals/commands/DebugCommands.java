package dev.dubhe.chinesefestivals.commands;

import com.mojang.brigadier.CommandDispatcher;
import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class DebugCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("festivals").then(
                Commands.literal("set").then(
                        Commands.literal("spring").executes(context -> {
                            ChineseFestivals.debugFestival = "spring";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("new_year").executes(context -> {
                            ChineseFestivals.debugFestival = "new_year";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("lantern").executes(context -> {
                            ChineseFestivals.debugFestival = "lantern";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("dragon_boat").executes(context -> {
                            ChineseFestivals.debugFestival = "dragon_boat";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("qixi").executes(context -> {
                            ChineseFestivals.debugFestival = "qixi";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("moon").executes(context -> {
                            ChineseFestivals.debugFestival = "moon";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("double_ninth").executes(context -> {
                            ChineseFestivals.debugFestival = "double_ninth";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        })).then(
                        Commands.literal("laba").executes(context -> {
                            ChineseFestivals.debugFestival = "laba";
                            context.getSource().sendSuccess(() -> Component.literal("Festivals have been set as " + ChineseFestivals.debugFestival), false);
                            return 0;
                        }))
        ));
    }
}
