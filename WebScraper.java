import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class WebScraper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter topic to search on: ");
            String topic = scanner.nextLine().replace(" ","_");

            String url = "https://quotes.toscrape.com/" + topic;
            
            Document doc = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
            .get();

            Element paragraphs = doc.selectFirst("div.mw-parser-output > p");

            System.out.println("\n=======WEBPAGE INFORMATION HERE=======");

            boolean found = false;

            for (Element p : paragraphs) {
                String text = p.text().trim();

                if (!text.isEmpty() && text.length() >50) {
                    System.out.println(text);
                    found = true;
                    break;
                }
            }

            if(!found) {
                System.out.println("No relevant information found for the topic: " + topic);
            }
        }
        catch (Exception e) {
            System.out.println("An error occurred while fetching the webpage: " + e.getMessage());
        }
        scanner.close();
    }
}