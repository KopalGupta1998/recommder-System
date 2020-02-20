 



/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmovies_short.csv", "ratings_short.csv");
    }
    public SecondRatings(String moviesfile,String ratingsfile)
    {
       FirstRatings frs=new FirstRatings();
       myMovies =frs.loadMovies(moviesfile);
       myRaters =frs.loadRaters(ratingsfile);
    }
    public int getMovieSize()
    {
        return myMovies.size();
    }
    public int getRaterSize()
    {
       return myRaters.size();  
    }
    public double getAverageByID(String id,int minimalRaters)
    {
        
        double sum=0.0;
        double n=0.0;
        for(Rater r:myRaters)
        {
          ArrayList<String> list = r.getItemsRated(); 
          for(String s:list)
          {
            if(s.equals(id))
            {
              sum+=r.getRating(s);
              n++;
            }
          }
        }
        double average=0.0;
        if(n>=minimalRaters)
        {
            average = sum/n;
            return average;
        }
        return average;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> r=new ArrayList<Rating>();
        for(Rater re:myRaters)
        {
             ArrayList<String> list = re.getItemsRated(); 
          for(String s:list)
          {
             double avg= getAverageByID(s, minimalRaters);
             Rating rate=new Rating (s,avg);
             r.add(rate);
          }
        }
        return r;
    }
    public String getTitle(String id)
    {
        int l=id.length();
        int o= 7-l;
        for(int i=0;i<o;i++)
        {
            id="0"+id;
        }
        for(Movie m:myMovies)
        {
            String myid=m.getID();
            //System.out.println(myid);
            if(id.equals(myid))
            {
                return m.getTitle();
            }
            
        }
        return "NOT FOUND";
    }
    public String getID(String title)
    {
        for(Movie m:myMovies)
        {
            String mytitle=m.getTitle();
            if(title.equals(mytitle))
            {
                return m.getID();
            }
        }
        return "NO SUCH TITLE FOUND";
    }
}