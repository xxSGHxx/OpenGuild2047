/**
 * The MIT License
 *
 * Copyright 2014 Grzegorz.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package pl.grzegorz2047.openguild2047.commands.arguments;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import pl.grzegorz2047.openguild2047.OpenGuild;

public class VersionArg {

    public static boolean execute(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_GRAY + " -------------------- " + ChatColor.GOLD + "OpenGuild2047" + ChatColor.DARK_GRAY + " -------------------- ");
        sender.sendMessage(ChatColor.DARK_GRAY + "Wersja: " + ChatColor.GOLD + OpenGuild.get().getDescription().getVersion());
        sender.sendMessage(ChatColor.DARK_GRAY + "Autor: " + ChatColor.GOLD + "Lider projektu: grzegorz2047, pomocnik: TheMolkaPL");
        sender.sendMessage(ChatColor.DARK_GRAY + "GitHub: " + ChatColor.GOLD + "https://github.com/grzegorz2047/OpenGuild2047");
        return true;
    }

}
