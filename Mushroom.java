
public class Mushroom {
	
	//structure of the data:
	//each begins with a p or e, which is poisonous or not, this will be stored in the boolean variable
	//after this, there are 22 attributes - stored in an array 
	char[] attributes = new char[22];
	boolean edible;
	
	public Mushroom(String m)
	{
		char c = m.charAt(0);
		if(c =='e')
		{
			edible=true;
		}
		else
			edible = false;
		StringBuilder sb = new StringBuilder(m);
		sb.deleteCharAt(0);
		//deletes all the ,
		m = m.replace(",", "");
		
		attributes = m.toCharArray();
	}
	//with this constructor-> it will determine poisonous, then store the attributes
	
	public void  setCategory(char k)
	{
		if(k=='e'){
			edible=true;
		}
		else
			edible=false;
	}
	
	public char getAttribute(int m){
		
		return attributes[m];
	}
	
	public boolean getCategory()
	{
		return edible;
	}
	
	
	

}
