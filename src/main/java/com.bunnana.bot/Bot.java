package com.bunnana.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

public class Bot extends ListenerAdapter
{
    public static void main(String[] arguments) throws Exception
    {
        String BOT_TOKEN = "MzY5MjUxMDYwOTIzMTA1Mjky.DMXQUA.nk0Tsj4GXRvJTgEjtZdbMTMjrZU";

        JDA api = new JDABuilder(AccountType.BOT)
                .setToken(BOT_TOKEN)
                .addEventListener(new Bot())
                .buildAsync();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getRawContent();

        // getRawContent() is an atomic getter
        // getContent() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("Bread")) {
            String name = message.getAuthor().getId();
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Butter").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        if (content.equals("Role me")) {
            String name = message.getAuthor().getId();
            MessageChannel channel = event.getChannel();
            GuildController controller  =  event.getGuild().getController();
            controller.addRolesToMember(event.getMember(), event.getGuild().getRoleById("368984671100600321")).queue();
            channel.sendMessage("Assigned User: " + event.getMember().getUser().getName()
                    + " to " + event.getGuild().getRoleById("368984671100600321").getName()).queue();
        }
    }
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        //Declare who joined.
        String memberJoined = event.getMember().getAsMention();
        MessageChannel channel = event.getGuild().getTextChannelById("369011624063008768");
        channel.sendMessage(memberJoined + " has joined!").queue();

        //Assign RECRUIT to new member
        GuildController controller  =  event.getGuild().getController();
        controller.addRolesToMember(event.getMember(), event.getGuild().getRoleById("368984671100600321")).queue();
        channel.sendMessage("Assigned User: " + event.getMember().getUser().getName()
                + " to " + event.getGuild().getRoleById("368984671100600321").getName()).queue();

    }
}





