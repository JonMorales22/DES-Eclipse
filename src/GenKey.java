import java.util.*;
/**
 * Write a description of class GenKey here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GenKey
{
    private String keyString;
    private List<char[]> key;
    private char key2[];
    private final int MAX_BITS = 8;
    
    public GenKey(String in)
    {
        //key = new ArrayList();
        key2 = new char[64];
        keyString = in;
        
        for(int i=0;i<in.length();i++)
        {
        		key2[i] = in.charAt(i);
        }
    }
    
    public void GenerateKey()
    {
        System.out.println("Key before initial perm: ");
        printKey(key2);
        
        char[] initialPerm = initPerm();
        
        System.out.println("\nKey after inital perm: ");
        
    		char[] initalPerm = initPerm();
    		printKey(initialPerm);
    		
    		String c0 = createC(initialPerm);
    		String d0 = createD(initalPerm);
    		
    		System.out.println("\n\nGenerating subkeys:");
    		System.out.println("c0: " + c0);
    		System.out.println("d0: " + d0);
    	
    		List<String> c_list = createSubKey(c0);
    		List<String> d_list = createSubKey(d0);
//    		List<String> c_List = new ArrayList<String>();
//    		List<String> d_List = new ArrayList<String>();
//    		
//    		//============================ can probably move the following lines to a separate method
//    		c_List.add(leftShift(c0));
//    		d_List.add(leftShift(d0));
//    		
//    		System.out.println();
//    		System.out.println("c1: " + c_List.get(0));
//    		System.out.println("d1: " + d_List.get(0));
//    		
//    		for(int i=0;i<15;i++)
//    		{
//    			if(i==0||i==1||i==8||i==15)
//    			{
//    				String tempC = leftShift(c_List.get(i));
//    				String tempD = leftShift(d_List.get(i));
//    	    			System.out.println("\nc" + (i+2) + ": " + tempC);
//    	    			System.out.println("d" + (i+2) + ": " + tempD);
//    				c_List.add(tempC);
//    				d_List.add(tempD);
//    			}
//    			else
//    			{
//    				//do 2 shifts
//    				String tempC = leftShift(c_List.get(i));
//    				tempC = leftShift(tempC);
//    				String tempD = leftShift(d_List.get(i));
//    				tempD = leftShift(tempD);
//    				System.out.println("\nc" + (i+2) + ": " + tempC);
//    				System.out.println("d" + (i+2) + ": " + tempD);
//    				c_List.add(tempC);
//    				d_List.add(tempD);
//    			}
//    		}
//    		//======================================================== ^ can probably put all lines enclosed in this thingie into a separate function
    		List<char[]>keyList = createKeyList(c_list,d_list);
    		key = keyList;
    }
    
    private List<String> createSubKey(String s1)
    {
    		List <String> list = new ArrayList<String>();
    		
    		list.add(leftShift(s1));
    		
    		for(int i=0;i<16;i++)
    		{
    			if(i==0||i==1||i==8||i==15)
    			{
    				String tempC = leftShift(list.get(i));
    	    			System.out.println("\nc" + (i+1) + ": " + tempC);
    				list.add(tempC);
    			}
    			else
    			{
    				//do 2 shifts
    				String tempC = leftShift(list.get(i));
    				tempC = leftShift(tempC);
    				System.out.println("\nc" + (i+1) + ": " + tempC);
    				list.add(tempC);
    			}
    		}	
    		return list; 
    }
    
    private List<char[]> createKeyList(List<String> c_List,List<String> d_List)
    {
    		List<char[]> temp = new ArrayList<char[]>();
    		for(int i=0;i<16;i++)
    		{
    			String str = c_List.get(i) + d_List.get(i);
    			char[] foo = PC_2(str);
    			System.out.print("k" + (i+1) + ":");
    			printKey(foo,6);
    			System.out.println();
    			temp.add(PC_2(str));
    		}
    		return temp;
    }
    
    private char[] initPerm()
    {
        int map[] = {
        		57,49,41,33,25,17,9,
        		1,58,50,42,34,26,18,
        		10,2,59,51,43,35,27,
        		19,11,3,60,52,44,36,
        		63,55,47,39,31,23,15,
        		7,62,54,46,38,30,22,
        		14,6,61,53,45,37,29,
        		21,13,5,28,20,12,4};
        char temp[] = new char[64];
        for(int i=0;i<map.length;i++)
        {
            temp[i] = key2[map[i]-1];
        }
        return temp;
    }
    

    private char[] PC_2(String s1)
    {
    		char[] temp = new char[56];
    		char[] strArr = s1.toCharArray();
    		int[] map = {14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
    		
    		for(int i=0;i<map.length;i++)
    		{
    			temp[i] = strArr[map[i]-1];
    		}
    		return temp;
    }
    
    private String createC(char[]arr)
    {
    		String temp = "";
    		for(int i=0;i<28;i++)
    		{
    			temp += arr[i];
    		}
    		return temp;
    }
    
    private String createD(char[] arr)
    {
		String temp = "";
		for(int i=28;i<56;i++)
		{
			temp += arr[i];
		}
		return temp;
    }
    
    private String leftShift(String str)
    {
    		String temp = "";
    		
    		temp = str.substring(1,str.length());
    		temp +=str.charAt(0);
    		
    		return temp;
    }
    
    //##################### Utility functions #######################//
    
    //this prints out binary string in b bit chunks
    public void printKey(char[] arr)
    {
    		String temp = "";
    		
        for(int i=0;i<arr.length;i++)
        {
        		if(i%8==0&&i!=0)
        			System.out.print("   ");
        		
            System.out.print(arr[i]);
        }
    }
    
    //prints out binary string in chunks specified by num
    public void printKey(char[] arr, int num)
    {
    		String temp = "";
    		
        for(int i=0;i<arr.length;i++)
        {
        		if(i%num==0&&i!=0)
        			System.out.print("   ");
        		
            System.out.print(arr[i]);
        }
    }
    
    //####################### Getter functions ######################//
    public List<char[]> getKey()
    {
    		return key;
    }
}
