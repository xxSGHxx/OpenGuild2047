/*
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package pl.grzegorz2047.openguild2047.commands.arguments;

import ca.wacos.nametagedit.NametagAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.openguild2047.Data;
import pl.grzegorz2047.openguild2047.GenConf;
import pl.grzegorz2047.openguild2047.SimpleGuild;
import pl.grzegorz2047.openguild2047.SimplePlayerGuild;
import pl.grzegorz2047.openguild2047.managers.MsgManager;

/**
 *
 * @author Grzegorz
 */
public class JoinArg {
    
        public static boolean execute(CommandSender sender, String[] args){
            if(!(sender instanceof Player)){
                sender.sendMessage(MsgManager.cmdonlyforplayer);
                return false;
            }
            Player p = (Player) sender;
            if(args.length>=2){
                if(!Data.getInstance().isPlayerInGuild(p.getName())){
                    //TODO: System zapraszania do gildii
                    String tag = args[1];
                    if(Data.getInstance().guilds.containsKey(tag)){
                        SimpleGuild sg = Data.getInstance().guilds.get(tag);
                        if(sg.getInvitedPlayers().contains(p.getName())){
                            SimplePlayerGuild spg = new SimplePlayerGuild(p.getName(),sg.getTag(),true);
                            Data.getInstance().guilds.put(sg.getTag(), sg);
                            Data.getInstance().ClansTag.add(sg.getTag());
                            Data.getInstance().guildsplayers.put(p.getName(), spg);
                            sg.addMember(p.getName());
                            if(GenConf.playerprefixenabled){
                                if(NametagAPI.hasCustomNametag(p.getName())){
                                    NametagAPI.resetNametag(p.getName());
                                }
                                NametagAPI.setPrefix(p.getName(), GenConf.colortagu + spg.getClanTag() +  "§r ");
                                p.sendMessage(GenConf.prefix+MsgManager.guildjoinsuccess);
                            }
                            return true;
                        }else{
                            p.sendMessage(GenConf.prefix+MsgManager.playernotinvited);
                            Player leader = Bukkit.getPlayer(sg.getLeader());
                            if(leader!=null){
                                if(leader.isOnline()){
                                    leader.sendMessage(GenConf.prefix+MsgManager.askforinvite+" "+p.getName());
                                    return false;//Mozna tu wiele dodac np. dodawanie do listy oczekujacych
                                    //albo dodawanie blokowanych osob, ktore spamia zaproszeniami
                                    //to moglby wykonywac lider
                                    //Lista oczekujacych wyswietlana przy wejsciu na serwer
                                    
                                }
                            }
                            p.sendMessage("Aktualnie nie ma lidera na serwerze!");
                            return false;
                        }
                    }else{
                        return false;
                    }
                    
                }else{
                    p.sendMessage(GenConf.prefix+MsgManager.alreadyinguild);
                    return false;
                } 
            }else{
                p.sendMessage(GenConf.prefix+MsgManager.wrongcmdargument);
                return false;
            }

        }
    
}
