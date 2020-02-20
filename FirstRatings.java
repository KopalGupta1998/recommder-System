 


/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings 
{
public ArrayList<Movie> loadMovies(String fileName)
{
 ArrayList <Movie> movies=new ArrayList<Movie>();
 FileResource fr=new FileResource(fileName);
 CSVParser parser=fr.getCSVParser();
 for(CSVRecord rec :parser)
 {
     int min=Integer.parseInt(rec.get("minutes"));
     Movie mov= new Movie(rec.get("id"),rec.get("title"),rec.get("year"),rec.get("genre"),rec.get("director"),rec.get("country"),rec.get("poster"),min);
     movies.add(mov);
 }
 return movies;
}
public void testloadMovies()
{
    ArrayList<Movie> m=loadMovies("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    for(Movie mo:m)
    {
        System.out.println(mo);
    }
    System.out.println(m.size());
    int comedies=0;
    int longmovies=0;
    HashMap <String,ArrayList<String>> map=new HashMap <String,ArrayList<String>>();
    for(Movie mo:m)
    {
        String genres=mo.getGenres();
        int time=mo.getMinutes();
        if(time>150)
        {
            longmovies++;
        }
        int index=genres.indexOf("Comedy");
        if(index!=-1)
        {
        String genre=genres.substring(index,index+6);
        if(genre.equals("Comedy"))
        {
          comedies++;  
        }
       }
    }
    for(Movie mo:m)
    {
     String title=mo.getTitle();
     String director=mo.getDirector();
     String [] directors=director.split(",");
     for(int i =0;i<directors.length;i++)
     {
         ArrayList <String> titles= new ArrayList<String>();
         if(!map.containsKey(directors[i]))
         {
             titles.add(title);
             map.put(directors[i],titles);
         }
         else
         {
             titles=map.get(directors[i]);
             titles.add(title);
             map.put(directors[i],titles);
         }
     }
    }
    int maxMovies=0;
    int d=0;
    String director="";
    for(String key:map.keySet())
    {
       int size= map.get(key).size();
       if(size>maxMovies)
       {
           director=key;
           maxMovies=size;
        }
    }
    for(String key:map.keySet())
    {
       int size= map.get(key).size();
       if(size==maxMovies)
       {
           d++;
       }
    }
    System.out.println("maximum movies made by a director"+maxMovies);
    System.out.println("number of such directors"+ d);
    System.out.println("number of comedy movies"+comedies);
    System.out.println("number of long movies"+ longmovies);
    System.out.println(" the famous director"+director);
}
public ArrayList<Rater> loadRaters(String fileName)
{
ArrayList <Rater> raters= new ArrayList<Rater>();
FileResource fr=new FileResource(fileName);
CSVParser parser=fr.getCSVParser();

String idprev="0";
Rater rate=null;
for(CSVRecord rec :parser)
 {
    String id =rec.get("rater_id");
    //System.out.println(rec);
    if(!id.equals(idprev))
    {
        rate=new EfficientRater(id);
       double rating = Double.parseDouble(rec.get("rating"));
       rate.addRating(rec.get("movie_id"),rating);
       raters.add(rate);
    }
    else
    {
       double rating = Double.parseDouble(rec.get("rating"));
       rate.addRating(rec.get("movie_id"),rating);
       
    }
    idprev=id;
 }
return raters;
}
public void testLoadRaters()
{
    ArrayList<Rater> raters=loadRaters("/Users/kopal/Desktop/data/ratings.csv");
    String raterid="193";
    int maxRatings=0;
    int y=0;
   for(Rater r:raters)
   { 
     String id=r.getID();  
     int numrate=r.numRatings();
     System.out.println(id+" , "+numrate);
     ArrayList<String> list=r.getItemsRated();
     for(String s:list)
     {
         double rating = r.getRating(s);
         System.out.println("Movie: "+s+" Rating: "+rating);
         
     }
   }
    for(Rater r:raters)
   { 
     String id=r.getID();  
     if(id.equals(raterid))
     {
     int numrate=r.numRatings();
     System.out.println(id+" , "+numrate);
     }
   }
   String h="";
    for(Rater r:raters)
   { 
      int numrate=r.numRatings(); 
      String id=r.getID();
     if(numrate>maxRatings)
     {
      maxRatings=numrate;
      h=id;
     }
   }
   
   for(Rater r:raters)
   { 
      int numrate=r.numRatings(); 
     if(numrate==maxRatings)
     {
      y++;
     }
   }
   System.out.println("Maximum no of movies rated "+maxRatings);
   System.out.println("rater id "+h);
   System.out.println("no. of raters that rated that no of movies "+y);
   String movieID="1798709";
   int nor=0;
   ArrayList<String> moviesID=new ArrayList<String>();
   FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
   CSVParser parser=fr.getCSVParser();
   for(Rater r:raters)
   { 
     ArrayList<String> movies=r.getItemsRated(); 
     if(movies.contains(movieID))
     {
      nor++;
     }
   }
   System.out.println("no. of raters that rated the movie "+nor);
   for(CSVRecord rec :parser)
 {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
}
System.out.println("no. of movies that  were rated "+moviesID.size());
System.out.println("movies rated the movie "+ moviesID);
}
}
