package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TOSAgeCommand extends Command {
    public TOSAgeCommand() {
        super("TOSAge", CommandCategory.ADMIN);
        setDescription("TOSAGE (DE)");
        setDisplayEmoji(":shield:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage(" Es ist in Deutschland ab 16. Das liegt an Artikel 8 der DSGVO:  1  1 Gilt Artikel 6 Absatz 1 Buchstabe a bei einem Angebot von Diensten der Informationsgesellschaft, das einem Kind direkt gemacht wird, so ist die Verarbeitung der personenbezogenen Daten des Kindes rechtmaessig, wenn das Kind das sechzehnte Lebensjahr vollendet hat.  2 Hat das Kind noch nicht das sechzehnte Lebensjahr vollendet, so ist diese Verarbeitung nur rechtmaessig, sofern und soweit diese Einwilligung durch den Traeger der elterlichen Verantwortung fuer das Kind oder mit dessen Zustimmung erteilt wird.  3 Die Mitgliedstaaten koennen durch Rechtsvorschriften zu diesen Zwecken eine niedrigere Altersgrenze vorsehen, die jedoch nicht unter dem vollendeten dreizehnten Lebensjahr liegen darf.  Absatz 2 ist nicht nachpruefbar, daher ist es fuer Discord irrelevant.  https://support.discord.com/hc/en-us/articles/360040724612  ").queue();
    }
}
