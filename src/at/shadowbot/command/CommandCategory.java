package at.shadowbot.command;

public enum CommandCategory {
    ADMIN("Administration", ":cloud_lightning:"),
    FUN("Fun", ":game_die:"),
    MISC("Misc", ":french_bread:"),
    UTILITY("Utility", ":tools:"),
    NSFW("NSFW", ":underage:");

    private String name;

    private String displayEmoji;

    CommandCategory(String name, String displayEmoji) {
        this.name = name;
        this.displayEmoji = displayEmoji;
    }

    public String getName() {
        return name;
    }

    public String getDisplayEmoji() {
        return displayEmoji;
    }

    public static CommandCategory parseCategory(String s) {
        for (CommandCategory category : CommandCategory.values())
            if (category.getName().equalsIgnoreCase(s))
                return category;
        return null;
    }
}
