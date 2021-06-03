package at.shadowbot.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BdoParser {
    public static String getNextBosses(String server) {
        String url = "https://mmotimer.com/bdo/?server=" + server;
        try {
            Document doc = Jsoup.connect(url).data("query", "Java").userAgent("Mozilla").get();
            Elements nextNameElements = doc.getElementsByClass("next-boss-title");
            Elements nextTimeElements = doc.getElementsByClass("next-boss-timer countdown");
            Elements lastTimeElements = doc.getElementsByClass("previous-boss-timer countup");
            int nextNameIndex = lastTimeElements.size();
            String nextName = nextNameElements.size() >= nextNameIndex ? nextNameElements.get(nextNameIndex).ownText() : "-";
            String nextTime = nextTimeElements.size() > 0 ? nextTimeElements.get(0).ownText() : "-";
            String followName = nextNameElements.size() >= (nextNameIndex + 1) ? nextNameElements.get(nextNameIndex + 1).ownText() : "-";
            String followTime = nextTimeElements.size() > 1 ? nextTimeElements.get(1).ownText() : "-";
            return nextName + "/" + nextTime + "/" + followName + "/" + followTime;
        } catch (IOException e) {
            return "Error!";
        }
    }
}
