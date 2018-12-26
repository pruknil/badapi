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
    	for(int i=0;i<5;i++) {
        	fetch("2",i*50);
    	}

    	//fetch("3",0);
    }

    private static void fetch(String forum,int start) throws IOException {
        String url = "http://www.thaibadminton.com/main/modules/newbb_plus/viewforum.php?start="+start+"&forum="+forum;
       // print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

      //print("\nLinks: (%d)", links.size());
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
    			  Node bb = link.parent().parent().childNode(2).childNode(2).childNode(0);
 	    		 LocalDate txtDate = LocalDate.parse(str, formatter);
  	    	  long months = ChronoUnit.MONTHS.between(localDate, txtDate);
  	    	// System.out.println(months);
  	    	  if(!bb.toString().contains("หา") && !bb.toString().contains("รับสมัคร")&& !bb.toString().contains("ไหมครับ")) {
  	    		  
    			  print("<%s>  (%s) {%s}", link.parent().parent().childNode(2).childNode(2).attr("abs:href"), bb,months);  
  	    	  }


    	    		 //System.out.println(localDate.until(txtDate));
//    	    		 Period period = Period.between(localDate, txtDate);
//    	    	        System.out.print(period.getYears() + " years,");
//    	    	        System.out.print(period.getMonths() + " months,");
//    	    	        System.out.print(period.getDays() + " days");
    	    	    //    long months = ChronoUnit.MONTHS.between(localDate, txtDate);
    	    	    //  System.out.println(months);
    	    		 // print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(str, 350));
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