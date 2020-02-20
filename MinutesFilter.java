
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter
{
private int maxMin;
private int minMin;	
	public MinutesFilter(int x,int y) {
		minMin=x;
		maxMin=y;
	}
	
	@Override
	public boolean satisfies(String id) 
	{
		int minutes=MovieDatabase.getMinutes(id);
		if(minutes>=minMin && minutes <=maxMin)
		{
		  return true;  
		  }
		  return false;
		}
}
