import java.util.*;
/**
 * Write a description of class DES here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class DES
{
    ArrayList key;
    public DES(String p1, String k1)
    {

    		GenKey genKey = new GenKey(k1);
    		genKey.GenerateKey();
        //key = genKey.getKey();
    }
}
