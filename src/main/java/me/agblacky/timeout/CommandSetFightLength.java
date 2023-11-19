package me.agblacky.timeout;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.agblacky.timeout.SchedulerTimer.fightTime;

public class CommandSetFightLength implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Return false if command sender is a player without operator permissions
        if (commandSender instanceof Player player && !player.isOp()) {
            return false;
        }
        try {
            fightTime = Integer.parseInt(strings[0]);
        } catch (NumberFormatException ex) {
            // Log
            return false;
        }
        return true;
    }
}