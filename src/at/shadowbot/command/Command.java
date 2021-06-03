package at.shadowbot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    private String name;

    private String description;

    private String displayEmoji;

    private String[] aliases;

    private String[] arguments;

    private Permission permission;

    private CommandCategory category;

    private boolean nsfw;

    public Command(String name, CommandCategory category) {
        this.name = name;
        this.description = null;
        this.displayEmoji = null;
        this.category = category;
        this.aliases = new String[0];
        this.arguments = new String[0];
        this.permission = null;
        this.nsfw = false;
    }

    public void setArguments(String... arguments) {
        this.arguments = arguments;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(CommandCategory category) {
        this.category = category;
    }

    public void setDisplayEmoji(String displayEmoji) {
        this.displayEmoji = displayEmoji;
    }

    public CommandCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayEmoji() {
        return displayEmoji;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public Permission getPermission() {
        return permission;
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
    }

    public boolean isValidSender(Member member) {
        return member.hasPermission(Permission.ADMINISTRATOR) || member.hasPermission(getPermission());
    }
}
