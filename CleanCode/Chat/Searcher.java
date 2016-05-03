package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by User on 05.04.2016.
 */

public class Searcher
{
    private  Loger log;
    private ArrayList<MakeMail> history;
    private BufferedReader reader;

    Searcher(Loger l, ArrayList<MakeMail> h)
    {
        log = l;
        history = h;
    }

    public void searchTime ()
    {
        log.writeLog("\n" + "Command \"8\"");
        boolean flag1 = false;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Message between [data1; data2]: ");
        System.out.println("input [data1]: dd/mm/yyyy HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        Date date1 = null;
        try
        {
            date1 = format1.parse(sc.nextLine());
        } catch (ParseException e)
        {
            log.writeLog("\n Exception: " + e.getMessage());
        }
        System.out.println("input [data2]: dd/mm/yyyy HH:mm:ss");
        Date data2 = null;
        try
        {
            data2 = format1.parse(sc.nextLine());
        }catch (ParseException e)
        {
            log.writeLog("\n Exception: " + e.getMessage());
        }
        for(MakeMail it : history)
        {
            if ((date1 != null)&& (data2 != null))
            {
                if(it.getData().after(date1) && it.getData().before(data2))
                {
                    System.out.println(it.toString());
                    flag1 = true;
                    ++count;
                }
            }
        }
        if(!flag1)
        {
            System.out.println("Can`t found");
        }else
        {
            log.writeLog(" completed successfully, found: " + count + " messages ");
        }
    }

    public void searchReg()
    {
        try
        {
            System.out.println("Enter regular expression");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String expression = reader.readLine();
            log.writeLog("\n Command \"6\"");
            boolean flag1 = false;
            int count = 0;
            try
            {
                for(MakeMail it2 : history)
                {
                    Pattern pattern = Pattern.compile(expression);
                    Matcher matcher = pattern.matcher(it2.getMessege());
                    if(matcher.find())
                    {
                        flag1 = true;
                        System.out.println(it2.toString());
                        ++count;
                    }
                }
            }catch (PatternSyntaxException e)
            {
                log.writeLog("\n Wrong reg: " + expression);
                System.out.println("Wrong reg: " + expression);
            }
            if(!flag1)
            {
                System.out.println("Can't found");
                log.writeLog(" Can't found");
            }else
            {
                log.writeLog(" completed successfully, found: " + count + " messages ");
            }
        }catch (IOException e)
        {
            log.writeLog("\n Buffer reader error" + e);
            System.out.println("Buffer reader error" + e);
        }
    }

    public void searchWord ()
    {
        try
        {
            System.out.println("Enter word for search: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String wordSearch = reader.readLine();
            log.writeLog("\n" + "Command \"7\"");
            boolean flag1 = false;
            int count = 0;
            for (MakeMail it2 : history)
            {
                if(it2.getMessege().contains(wordSearch))
                {
                    System.out.println(it2.toString());
                    flag1 = true;
                    count++;
                }
            }
            if(!flag1)
            {
                System.out.println("Can't found");
                log.writeLog(" Can't found");
            }else
            {
                log.writeLog(" completed successfully, found: " + count + " messages ");
            }
        }catch (IOException e)
        {
            log.writeLog("\n" + "Buffer reader error" + e);
            System.out.println("Buffer reader error" + e);
        }
    }

    public void searchAuthor()
    {
        try
        {
            log.writeLog("\n" + "Command \"5\"");
            System.out.println("Enter name for search: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String authSearch = reader.readLine();
            boolean flag1 = false;
            int count = 0;
            for (MakeMail it2 : history)
            {
                if (it2.getAuthor().equals(authSearch))
                {
                    System.out.println(it2.toString());
                    flag1 = true;
                    ++count;
                }
            }
            if(!flag1)
            {
                System.out.println("Can't found");
                log.writeLog(" Can't found");
            }else
            {
                log.writeLog(" completed successfully, found: " + count + " messages ");
            }
        }catch (IOException e)
        {
            log.writeLog("\n" + "Buffer reader error" + e);
            System.out.println("Buffer reader error" + e);
        }
    }
}

