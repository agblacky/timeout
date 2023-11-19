package me.agblacky.timeout;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import static me.agblacky.timeout.Timeout.timerActive;

public class CommandSetTimer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String param = strings[0];
        // Return if command sender is a player without operator permissions
        if (commandSender instanceof Player player && !player.isOp()) {
            return false;
        }
        if (Objects.equals(param, "on")) {
            timerActive = TRUE;
        } else if (Objects.equals(param, "off")) {
            timerActive = FALSE;
        } else {
            return false;
        }
        return true;
    }
}
