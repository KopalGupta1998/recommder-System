
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings 
{
public FourthRatings(String filename)
    {
        RaterDatabase.initialize(filename);
    }
public double getAverageByID(String id,int minimalRaters,ArrayList<Rater> myRaters,ArrayList<Rating> topraters)
    {
        double weight=0.0;
        double sum=0.0;
        double n=0.0;
        
        for(Rater r:myRaters)
        {
           for(int i=0;i<topraters.size();i++)
           {
               if(r.getID().equals(topraters.get(i).getItem()))
               {
                   weight= topraters.get(i).getValue();
                }
            }
          ArrayList<String> list = r.getItemsRated(); 
          for(String s:list)
          {
            if(s.equals(id))
            {
              sum=sum + weight*r.getRating(s);
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
    public ArrayList<Rating> getAverageRatings(int minimalRaters,ArrayList<Rater> myRaters,ArrayList<Rating> topraters)
    {
        ArrayList<Rating> r=new ArrayList<Rating>();
        for(Rater re:myRaters)
        {
             ArrayList<String> list = re.getItemsRated(); 
          for(String s:list)
          {
             double avg= getAverageByID(s, minimalRaters,myRaters,topraters);
             Rating rate=new Rating (s,avg);
             r.add(rate);
          }
        }
        return r;
    }
public ArrayList<Rating> getSimilarRatingsByFilter (String id,int minimalRaters,int numSimilarRaters,Filter  filterCriteria )
{
    ArrayList<Rating> weightedavg= new ArrayList<Rating>();
    ArrayList<Rating> similarities =getSimilarities(id);
    MovieDatabase.filterBy(filterCriteria );
    ArrayList<Rating> topraters=new ArrayList<Rating>();
    ArrayList<Rater> myRaters=RaterDatabase.myRaters();
    for(int i=0;i<numSimilarRaters;i++)
    {
        topraters.add(similarities.get(i));
    }
    weightedavg=getAverageRatingsByFilter(minimalRaters,myRaters,topraters,filterCriteria);
    
    return weightedavg;
}
    
    private double dotProduct(Rater me,Rater r)
{
     ArrayList<String> myMovies= me.getItemsRated();
     ArrayList<String> Movies= r.getItemsRated();
     HashMap<String,Double> myratings=new HashMap<String,Double>();
     HashMap<String,Double> otherratings=new HashMap<String,Double>();
     double product=0;
     for(String id:myMovies)
     {
         double x=me.getRating(id);
         x=x-5;
         myratings.put(id,x);
     }
     for(String id:Movies)
     {
         double y=r.getRating(id);
         y=y-5;
         otherratings.put(id,y);
     }
     for(String myId:myratings.keySet())
     {
         for(String id:otherratings.keySet())
         {
             if(myId.equals(id))
             {
                 product=product +myratings.get(myId)*otherratings.get(id);
                }
            }
        }
     return product;
}
private ArrayList<Rating> getSimilarities(String id)throws ConcurrentModificationException
{
    ArrayList<Rating> list= new ArrayList<Rating>();
    Rater me=RaterDatabase.getRater(id);
    for(Rater r:RaterDatabase.getRaters())
    {
        if(!me.getID().equals(r.getID()))
        {
        list.add(new Rating(r.getID(),dotProduct(me,r)));
       }
    }
    Collections.sort(list,Collections.reverseOrder());
    return list;
}
public ArrayList <Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters)throws ConcurrentModificationException
{
    ArrayList<Rating> weightedavg= new ArrayList<Rating>();
    ArrayList<Rating> similarities =getSimilarities(id);
    System.out.println(similarities);
    ArrayList<Rating> topraters=new ArrayList<Rating>();
    ArrayList<Rater> myRaters=RaterDatabase.myRaters();
    //System.out.println(myRaters);
    for(int i=0;i<numSimilarRaters;i++)
    {
        topraters.add(similarities.get(i));
    }
    weightedavg=getAverageRatings(minimalRaters,myRaters,topraters);
    //System.out.println(weightedavg);
    //System.out.println(topraters);
    return weightedavg;
}
public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,ArrayList<Rater> myRaters,ArrayList<Rating> topraters,Filter f)
    {
        ArrayList<Rating> r=new ArrayList<Rating>();
        ArrayList<String> movieIds=MovieDatabase.filterBy(f);
        for(Rater re:myRaters)
        {
        ArrayList<String> list = re.getItemsRated(); 
        for(String s:list)
        {
         if(movieIds.contains(s))
        {
         double avg= getAverageByID(s, minimalRaters,myRaters,topraters);
         Rating rate=new Rating (s,avg);
         r.add(rate);
        }
        }
        }
        return r;
    }
}


