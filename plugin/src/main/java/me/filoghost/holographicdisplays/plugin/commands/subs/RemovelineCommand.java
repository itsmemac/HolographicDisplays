/*
 * Copyright (C) filoghost and contributors
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package me.filoghost.holographicdisplays.plugin.commands.subs;

import me.filoghost.fcommons.command.sub.SubCommandContext;
import me.filoghost.fcommons.command.validation.CommandException;
import me.filoghost.fcommons.command.validation.CommandValidate;
import me.filoghost.holographicdisplays.plugin.Colors;
import me.filoghost.holographicdisplays.plugin.commands.HologramCommandManager;
import me.filoghost.holographicdisplays.plugin.commands.HologramCommandValidate;
import me.filoghost.holographicdisplays.plugin.disk.ConfigManager;
import me.filoghost.holographicdisplays.plugin.event.InternalHologramEditEvent;
import me.filoghost.holographicdisplays.plugin.hologram.internal.InternalHologram;
import me.filoghost.holographicdisplays.plugin.hologram.internal.InternalHologramManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class RemovelineCommand extends LineEditingCommand implements QuickEditCommand {

    private final HologramCommandManager commandManager;
    private final InternalHologramManager internalHologramManager;
    private final ConfigManager configManager;

    public RemovelineCommand(
            HologramCommandManager commandManager,
            InternalHologramManager internalHologramManager,
            ConfigManager configManager) {
        super("removeline");
        setMinArgs(2);
        setUsageArgs("<hologram> <lineNumber>");
        setDescription("Removes a line from a hologram.");

        this.commandManager = commandManager;
        this.internalHologramManager = internalHologramManager;
        this.configManager = configManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args, SubCommandContext context) throws CommandException {
        InternalHologram hologram = HologramCommandValidate.getInternalHologram(internalHologramManager, args[0]);
        
        int lineNumber = CommandValidate.parseInteger(args[1]);

        CommandValidate.check(lineNumber >= 1 && lineNumber <= hologram.getLineCount(), 
                "The line number must be between 1 and " + hologram.getLineCount() + ".");
        int index = lineNumber - 1;
        
        CommandValidate.check(hologram.getLineCount() > 1, 
                "The hologram should have at least 1 line. If you want to delete it, use /" + context.getRootLabel() + " delete.");

        hologram.removeLine(index);
        
        configManager.saveHologramDatabase(internalHologramManager);
        Bukkit.getPluginManager().callEvent(new InternalHologramEditEvent(hologram));
        
        sender.sendMessage(Colors.PRIMARY + "Line " + lineNumber + " removed.");
        commandManager.sendQuickEditCommands(context, hologram);
    }

    @Override
    public String getActionName() {
        return "Remove";
    }

}