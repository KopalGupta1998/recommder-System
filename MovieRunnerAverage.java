 


/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class MovieRunnerAverage 
{
public void printAverageRatings ()
{
    SecondRatings sr =new SecondRatings("/Users/kopal/Desktop/data/ratedmovies_short.csv","/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
     CSVParser parser=fr.getCSVParser();
     ArrayList<String> moviesID=new ArrayList<String>();
     for(CSVRecord rec :parser)
    {
    String id =rec.get("movie_id");
    if(!moviesID.contains(id))
    {
        moviesID.add(id);
    }
    }
    ArrayList <Rating> ratings = sr.getAverageRatings(3);
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
       String title=sr.getTitle(r.getItem());
       System.out.println(title+": "+r.getValue() );
    }
}
public void getAverageRatingOneMovie()
{
 SecondRatings sr =new SecondRatings("/Users/kopal/Desktop/data/ratedmovies_short.csv","/Users/kopal/Desktop/data/ratings_short.csv");
  String title="The Godfather";
  String id=sr.getID(title);
  System.out.println(id);
 double average=sr.getAverageByID(id,3);
  System.out.println(average);
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
public void numberOfRaters()
{
    int numOfRatings=20;
    int m=0;
    int x=0;
    SecondRatings sr =new SecondRatings("/Users/kopal/Desktop/data/ratedmovies_short.csv","/Users/kopal/Desktop/data/ratings_short.csv");
    FileResource fr=new FileResource("/Users/kopal/Desktop/data/ratings_short.csv");
    CSVParser parser=fr.getCSVParser();
    HashMap<String,Integer> movies=new HashMap<String,Integer>();
    for(CSVRecord rec :parser)
    {
        String id=rec.get("movie_id");
        if (!movies.containsKey(id))
        {
            movies.put(id,1);
        }
        else
        {
            x=movies.get(id);
            movies.put(id,x+1);
        }
        
    }
    for(CSVRecord rec :parser)
    {
       String id=rec.get("movie_id"); 
       x=movies.get(id);
       System.out.println(id);
       movies.put(id,x+1);
    }
    for (String id:movies.keySet())
    {
        if (movies.get(id)>=numOfRatings)
        {
           m++; 
           System.out.println(id+","+movies.get(id));
        }
        
    }
    System.out.println(m);
}
}
