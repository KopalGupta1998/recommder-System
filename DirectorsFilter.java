
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter
{
  private String directors;
  public DirectorsFilter(String d)
  {
     directors=d; 
  }
  public boolean satisfies(String id)
  {
   String dir=MovieDatabase.getDirector(id);
   String []s1=dir.split(",");
   String []s2=directors.split(",");
   for(int i=0;i<s1.length;i++)
   {
     for(int j=0;j<s2.length;j++)
     {
         if(s1[i].equals(s2[j]))
         {
             return true;
            }
     }
   }
   return false;
  }
}
