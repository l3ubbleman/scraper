import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class MangaStream {

    public static ArrayList<String> getReleases(UserAgent userAgent, String mangaSite, String searchQuery){
        try
        {
            userAgent.visit(mangaSite);                        //visit a url
            String html = userAgent.doc.innerHTML();
            String[] s = html.split("<");
            ArrayList<String> newTitles = new ArrayList<String>();

            for (int i = 0; i < s.length; i++) {
                if (s[i].toLowerCase().contains(searchQuery.toLowerCase())) {
                    i++; //to avoid hitting current div
                    while (!s[i].toLowerCase().contains("div"))
                    {
                        newTitles.add(trim(s[i]));
                        i++;
                    }

                }
            }
            return newTitles;
        }
        catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
            System.err.println(e);
            return null;
        }
    }

    public static String trim(String manga){
        String [] content = manga.split(">"); //essentially splitting by both < and > leaving only content
        if (content.length > 1){
            if(isEmpty(content[1].trim())) return "&"; // Should only ever be 2 items
            else return content[1].trim();
        }
        else return "&";
    }

    public static boolean isEmpty (String word){
        if(word.length() < 1) return true;
        else return false;
    }
}