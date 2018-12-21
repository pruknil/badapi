import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
       // Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = "http://www.thaibadminton.com/main/modules/newbb_plus/viewforum.php?forum=3";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
//
//        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }
//
//        print("\nImports: (%d)", imports.size());
//        for (Element link : imports) {
//            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
//        }
//
//        print("\nLinks: (%d)", links.size());
//        for (Element link : links) {
//            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
//        }
      print("\nLinks: (%d)", links.size());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

      LocalDate localDate = LocalDate.now();
      for (Element link : links) {
    	 
    	  String str = link.text();
    	  if(str.matches(".*\\d{2}:\\d{2}$")) {
    		  if(str.startsWith("เมื่อวาน")) {
    			 str = localDate.minusDays(1).atTime(0, 0).format(formatter);
    		  }else if(str.startsWith("วันนี้")) {
    			  str = localDate.atTime(0, 0).format(formatter);
    		  }

    		  if(str.length() ==16) {
    			  Node bb = link.parent().parent().childNode(2);
    			  System.out.println(bb.childNode(2).childNode(0).toString());
    	    		 LocalDate txtDate = LocalDate.parse(str, formatter);
    	    		 //System.out.println(localDate.until(txtDate));
//    	    		 Period period = Period.between(localDate, txtDate);
//    	    	        System.out.print(period.getYears() + " years,");
//    	    	        System.out.print(period.getMonths() + " months,");
//    	    	        System.out.print(period.getDays() + " days");
    	    	        long months = ChronoUnit.MONTHS.between(localDate, txtDate);
    	    	      System.out.println(months);
    	    		  print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(str, 350));
    		  }

    	  }
          
      }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}