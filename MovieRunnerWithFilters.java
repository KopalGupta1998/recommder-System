/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;
public class MovieRunnerWithFilters 
{
public void printAverageRatings () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmoviesfull.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    System.out.println(tr.getRaterSize());
    System.out.println(MovieDatabase.size());
    ArrayList<Rating> ratings=tr.getAverageRatings(35);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    System.out.println(rate);
    sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
    System.out.println(rate.size());
}
public void sort(ArrayList<Rating> r)
{
    for(int i=0;i<r.size()-1;i++)
    {
        Rating r1= r.get(i);
        
        for(int j=i+1;j<r.size();j++)
        {
            Rating r2=r.get(j);
             //System.out.println(r2);
            if(r1.compareTo(r2)>0)
            {
                Collections.swap(r, j, i);
            }
        }
    }
}
public void printAverageRatingsByYear () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmovies_short.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    YearAfterFilter f=new YearAfterFilter(2000);
    ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,f);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    System.out.println(rate);
    sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
    System.out.println(rate.size());
}
public void printAverageRatingsByGenre () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmovies_short.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    GenreFilter f=new GenreFilter("Crime");
    ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,f);
    System.out.println(ratings);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    sort(rate);
    //System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
    System.out.println(rate.size());
}
public void printAverageRatingsByMinutes () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmovies_short.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    MinutesFilter f=new MinutesFilter(110,170);
    ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,f);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    sort(rate);
    //System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
    System.out.println(rate.size());
}
public void printAverageRatingsByDirector () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmovies_short.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    DirectorsFilter f=new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
    ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,f);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
   // System.out.println(rate);
    sort(rate);
    //System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
    System.out.println(rate.size());
}
public void printAverageRatingsByYearAfterAndGenre () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmovies_short.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    AllFilters f=new AllFilters();
    GenreFilter f1=new GenreFilter("Romance");
    YearAfterFilter f2=new YearAfterFilter(1980);
    f.addFilter(f1);
    f.addFilter(f2);
    ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,f);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    //System.out.println(rate);
    sort(rate);
   // System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
     System.out.println(rate.size());
}
public void printAverageRatingsByDirctorsAfterAndMinuts () throws IOException {
    ThirdRatings tr =new ThirdRatings("/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
    CSVParser parser=fr.getCSVParser();
    MovieDatabase.initialize("/Users/kopal/Desktop/data/ratedmovies_short.csv");
    ArrayList<String> moviesID=new ArrayList<String>();
    AllFilters f=new AllFilters();
    DirectorsFilter f1=new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
    MinutesFilter f2=new MinutesFilter(30,170);
    f.addFilter(f1);
    f.addFilter(f2);
    ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,f);
   for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
   ArrayList<Rating> rate = new ArrayList<Rating>();
    
    for(Rating r:ratings)
    {
        if(r.getValue()!=0.0 && moviesID.contains(r.getItem()) )
        {
        rate.add(r); 
        moviesID.remove(r.getItem());
        }
    }
    System.out.println(rate);
    sort(rate);
    System.out.println(rate);
    for(Rating r:rate)
    {
       String title=MovieDatabase.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
    System.out.println(rate.size());
}
}
