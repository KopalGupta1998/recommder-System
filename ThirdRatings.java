/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
   
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this( "/Users/kopal/Desktop/data/ratings_short.csv");
    }
    public ThirdRatings(String ratingsfile)
    {
       FirstRatings frs=new FirstRatings();
       myRaters =frs.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
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
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters,Filter  filterCriteria )
    {
        ArrayList <Rating> r=new ArrayList<Rating>();
        ArrayList<String> movieIDs=MovieDatabase.filterBy( filterCriteria);
        for(String s:movieIDs)
        {
          double avg=getAverageByID(s, minimalRaters); 
          Rating rate= new Rating(s,avg);
          r.add(rate);
        }
        return r;
    }
}
