/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlegame;

/**
 *
 * @author Muhammad Amin
 */
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	public abstract class EasyIn
	{

	  public static String getString()
	  { 
	     Scanner input = new Scanner(System.in);
             String s = input.nextLine(); 
             return s;
	   }

	   public static int getInt()
	   {
	      int i = 0;
	      boolean ok = false;
	      String s ;
	      while(!ok)
	      {
	         byte[] b = new byte[512];
	         try
	         {
	            System.in.read(b);
	            s = new String(b);
	            i = Integer.parseInt(s.trim());
	            ok = true;
	         }
	         catch(NumberFormatException e)
	         {
	            System.out.println("Make sure you enter an integer");
	         }
	         catch(IOException e)
	         {
	             System.out.println(e.getMessage());
	         }
	     }
	     return i;
	 }

	 public static byte getByte()
	 {
	     byte i = 0;
	     boolean ok = false;
	     String s ;
	     while(!ok)
	     {
	        byte[] b = new byte[512];
	        try
	        {
	            System.in.read(b);
	            s = new String(b);
	            i = Byte.parseByte(s.trim());
	            ok = true;
	        }
	        catch(NumberFormatException e)
	        {
	            System.out.println("Make sure you enter a byte");
	        }
	        catch(IOException e)
	        {
	             System.out.println(e.getMessage());
	        }
	     }
	     return i;
	 }

	 public static short getShort()
	 {
	     short i = 0;
	     boolean ok = false;
	     String s ;
	     while(!ok)
	     {
	        byte[] b = new byte[512];
	        try
	        {
	            System.in.read(b);
	            s = new String(b);
	            i = Short.parseShort(s.trim());
	            ok = true;
	        }
	        catch(NumberFormatException e)
	        {
	            System.out.println("Make sure you enter a short integer");
	        }
	        catch(IOException e)
	        {
	             System.out.println(e.getMessage());
	        }
	     }
	     return i;
	 }


	 public static long getLong()
	 {
	    long l = 0;
	    boolean ok = false;
	    String s ;
	    while(!ok)
	    {
	       byte[] b = new byte[512];
	       try
	       {
	           System.in.read(b);
	           s = new String(b);
	           l = Long.parseLong(s.trim());
	           ok = true;
	       }
	       catch(NumberFormatException e)
	       {
	           System.out.println("Make sure you enter a long integer");
	       }

	       catch(IOException e)
	       {
	            System.out.println(e.getMessage());
	       }
	    }
	    return l;
	 }


	 public static double getDouble()
	 {
	    double d = 0;
	    boolean ok = false;
	    String s ;
	     while(!ok)
	    {
	        byte[] b = new byte[512];
	        try
	        {
	             System.in.read(b);
	             s = new String(b);
	             d = Double.parseDouble(s.trim());
	             ok = true;
	        }
	        catch(NumberFormatException e)
	        {
	             System.out.println("Make sure you enter a decimal number");
	        }
	        catch(IOException e)
	        {
	           System.out.println(e.getMessage());
	       }
	    }
	    return d;
	 }

	 public static float getFloat()
	 {
	     float f = 0;
	     boolean ok = false;
	     String s;
	     while(!ok)
	     {
	        byte[] b = new byte[512];
	        try
	        {
	            System.in.read(b);
	            s = new String(b);
	            f = Float.parseFloat(s.trim());
	            ok = true;
	        }
	        catch(NumberFormatException e)
	        {
	            System.out.println("Make sure you enter a decimal number");
	        }
	        catch(IOException e)
	        {
	            System.out.println(e.getMessage());
	        }
	     }
		return f;
	  }

	  public static char getChar()
	  {
	     char c = ' ';
	     boolean ok = false;
	     String s;
	     while(!ok)
	     {
	        byte[] b = new byte[512];
	        try
	        {
	          System.in.read(b);
	          s = new String(b);
	           if(s.trim().length()!=1)
	           {
	             System.out.println("Make sure you enter a single character");
	           }
	           else
	           {
	                c = s.trim().charAt(0);
	                ok = true;
	           }
	        }
	        catch(IOException e)
	        {
	            System.out.println(e.getMessage());
	        }
	     }
	     return c;
	  }


	  public static void pause()
	  {
	     boolean ok = false;
	     while(!ok)
	     {
	         byte[] b = new byte[512];
	         try
	         {
	             System.in.read(b);
	             ok = true;
	         }
	         catch(IOException e)
	         {
	              System.out.println(e.getMessage());
	         }
	     }
	  }

	  public static void pause(String messageIn)
	  {
	     boolean ok = false;
	     while(!ok)
	     {
	         byte[] b = new byte[512];
	         try
	         {
	              System.out.print(messageIn);
	              System.in.read(b);
	              ok = true;
	         }
	         catch(IOException e)
	        {
	              System.out.println(e.getMessage());
	        }
	    }
	}
}
