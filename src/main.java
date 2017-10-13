
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

/*TO DO: 
 * 	-I think i finished keygen, might need to clean it up a little but basic functionality is there.
	-Need to start working on the actual Encryption process	
*/
public class main
{
    /**
     * Constructor for objects of class main
     */
	public final static int MAX_BITS = 8;
    public static void main(String[] args)
    {
        String p1,testKey;
        
        p1 = "poop";
        
//        k1 = "IEOFIT#1";
//        k1 = handleKeyInput(k1,0);
//        System.out.print(k1);
//        
        testKey = "133457799BBCDFF1"; //<---- online example
//        testKey = "0f1571c947d9e859"; //<---Textbook example
        testKey = handleKeyInput(testKey,1);
        //System.out.print(testKey);
        
        DES des = new DES(p1,testKey);
    }
    
    public static String handleKeyInput(String in, int flag)
    {
		String binaryString = "";
    		if(flag==0) //char
    		{
    			if(in.length()<8)
    				in = PadInput(in,8);
    			
    			//System.out.println(in + " " + in.length()) ;
        		for(int i=0;i<in.length();i++)
        		{
        			String temp = Integer.toBinaryString((int)in.charAt(i));
        			temp = to8Bits(temp);
        			binaryString += temp;
        		}
    		}
    		else if(flag==1) //hex
    		{
    			if(in.length()<16)
    				in = PadInput(in,16);
    			
    			while(in.length()>0)
    			{
    				//System.out.println(in);
    				String temp = in.charAt(0)+"";
    				temp+=in.charAt(1);
    				int num = Integer.parseInt(temp, 16);
   				
    				String temp2 = Integer.toBinaryString(num);
    				temp2 = to8Bits(temp2);
    				
    				//System.out.println("Hex: " + temp + " -> Binary: " + temp2);
    				
    				binaryString += temp2;
    				in = in.substring(2, in.length());
    			}
    		}
    			
    		return binaryString;
    }
   public static String PadInput(String in, int count)
   {
	   while(in.length()<count)
		   in+=0;
	   return in;
   }
   
   public static String to8Bits(String in)
   {
      String out = "";
      // number of bits to add to string = MAX_BITS (8) - current number of bits
      for(int i = 0; i < MAX_BITS - in.length() ; i++)
      {
          out = out + "0";
      }
      return out+in;
   }
}
