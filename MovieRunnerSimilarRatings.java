
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;
public class MovieRunnerSimilarRatings 
{
public void printAverageRatings () throws ConcurrentModificationException  {
   FourthRatings tr =new FourthRatings("ratings.csv");
   MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
   ArrayList<String> moviesID=new ArrayList<String>();
   RaterDatabase.initialize("rating.csv");
   String raterid="65";
   ArrayList<Rating> ratings=tr.getSimilarRatings(raterid,20,5);
   System.out.println(ratings);
    ArrayList<Rating> rate = new ArrayList<Rating>();
    for (Rating d:ratings)
    {
     if(!moviesID.contains(d.getItem()))
     {
         moviesID.add(d.getItem());
     }
    }
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()))
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    Collections.sort(rate);
    //System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
}

public void printAverageRatingsByGenre ()  
{
    FourthRatings tr =new FourthRatings("ratings.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    RaterDatabase.initialize("ratings.csv");
    String raterid="964";
    for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
    ArrayList<Rating> rate = new ArrayList<Rating>();
    GenreFilter f= new GenreFilter("Mystery");
    ArrayList<Rating> ratings=tr.getSimilarRatingsByFilter(raterid,5,20,f);
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    Collections.sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
   
}
public void printAverageRatingsByDirector ()  
{
    FourthRatings tr =new FourthRatings("ratings.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    RaterDatabase.initialize("/Users/kopal/Desktop/data/ratings.csv");
    String raterid="120";
    for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
    ArrayList<Rating> rate = new ArrayList<Rating>();
    DirectorsFilter f= new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
    ArrayList<Rating> ratings=tr.getSimilarRatingsByFilter(raterid,2,10,f);
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    Collections.sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
   
}
public void printSimilarRatingsByGenreAndMinutes ()  
{
    FourthRatings tr =new FourthRatings("ratings.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    RaterDatabase.initialize("/Users/kopal/Desktop/data/ratings.csv");
    String raterid="168";
    for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
    ArrayList<Rating> rate = new ArrayList<Rating>();
    AllFilters f= new AllFilters();
    GenreFilter f1=new GenreFilter("Drama");
    MinutesFilter f2=new MinutesFilter(80,160);
    f.addFilter(f1);
    f.addFilter(f2);
    ArrayList<Rating> ratings=tr.getSimilarRatingsByFilter(raterid,3,10,f);
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    Collections.sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
   
}
public void printSimilarRatingsByYearAfterAndMinutes ()  
{
    FourthRatings tr =new FourthRatings("ratings.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    RaterDatabase.initialize("/Users/kopal/Desktop/data/ratings.csv");
    String raterid="314";
    for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
    ArrayList<Rating> rate = new ArrayList<Rating>();
    AllFilters f= new AllFilters();
    YearAfterFilter f1=new YearAfterFilter(1975);
    MinutesFilter f2=new MinutesFilter(70,200);
    f.addFilter(f1);
    f.addFilter(f2);
    ArrayList<Rating> ratings=tr.getSimilarRatingsByFilter(raterid,5,10,f);
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    Collections.sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
   
}
public void printSimilarRatings()  
{
    FourthRatings tr =new FourthRatings("ratings.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    RaterDatabase.initialize("ratings.csv");
    String raterid="65";
    for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
    ArrayList<Rating> rate = new ArrayList<Rating>();
    ArrayList<Rating> ratings=tr.getSimilarRatings(raterid,20,5);
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    Collections.sort(rate);
    //System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
   
}
}

