package Chat;

/**
 * Created by User on 14.03.2016.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class TaskBench
{
    private ArrayList <MakeMail> history;
    private BufferedReader reader;
    private Integer del;
    private Integer add;
    private Loger log;
    private Searcher search;

    public TaskBench()
    {
        del = 0;
        add = 0;
        history = new ArrayList<>();
        log = new Loger("LogOfChat.txt");
        log.writeLog("New Chat" + new Timestamp(System.currentTimeMillis()) + "\n");
    }

   public void tasksMeny ()
   {
       try
       {
           BufferedReader tasksReader = new BufferedReader(new InputStreamReader(System.in));
           String task = "";
           while (!task.equals("10"))
           {
               System.out.println("Выберите действие:");
               System.out.println("1.Загрузка сообщения.");
               System.out.println("2.Сохранение сообщения. ");
               System.out.println("3.Просмотр истории сообщений.");
               System.out.println("4.Удаление сообщения.");
               System.out.println("5.Поиск в истории сообщений по автору.");
               System.out.println("6.Поиск в истории сообщщений по регулярному вырожению.");
               System.out.println("7.Поиск в истории сообщения по ключевому слову.");
               System.out.println("8.Просмотр истории сообщений за определенный период.");
               System.out.println("9.Добавление сообщения.");
               System.out.println("10.Выйти из чата :)");

               task = tasksReader.readLine();

               switch (task)
               {
                   case "1":
                       load();
                       break;
                   case "2":
                       save();
                       break;
                   case "3":
                       show();
                       break;
                   case "4":
                       delMessage();
                       break;
                   case "5":
                       search.searchAuthor();
                       break;
                   case "6":
                       search.searchWord();
                       break;
                   case "7":
                       search.searchReg();
                       break;
                   case "8":
                       search.searchTime();
                       break;
                   case "9":
                       addMessage();
                       break;
                   case "10":
                       this.endChat();
                       break;
                   default:
                       System.out.println("\n" + task + "Can`t find this command, sorry");
                       break;


               }
           }
       } catch (IOException e)
       {
           log.writeLog ("\n" + "Buffer reader error" + e);
           System.out.println("Buffer reader error" + e);
       }
   }

    public void show()
    {
        log.writeLog("\n" + "Command \"3\"");
        for (MakeMail it : history)
        {
            System.out.println(it.toString());
        }
        if (history.isEmpty())
        {
            System.out.println("Empty history");
            log.writeLog(" completed successfully, show: " + (history.size() - 1) + "message ");
        }
    }

    public void delMessage()
    {
        if (history.isEmpty())
        {
            System.out.println("History is empty");
            return;
        }
        log.writeLog("\n" + "Command \"4\"");
        try
        {
            System.out.println("Enter inde for del: ");
            boolean flag = false;
            reader = new BufferedReader(new InputStreamReader(System.in));
            String idForDel = reader.readLine();
            Iterator <MakeMail> it = history.iterator();
            while (it.hasNext())
            {
                if (it.next().getId().equals(idForDel))
                {
                    it.remove();
                    System.out.println("Delete");
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                log.writeLog(" not found delete id" + idForDel + "message");
            }
            else
            {
                log.writeLog(" completed successfully, delete id:" + idForDel + "message");
                del++;
            }
        }
        catch (IOException e)
        {
            log.writeLog("\n" + "Buffer reader error" + e);
            System.out.println("Buffer reader error" + e);
        }
    }

    public void addMessage()
    {

        try
        {
            Date date = new Date();
            log.writeLog("\n" + "Command \"9\"");
            System.out.println("Enter name: ");
            String name = "";
            boolean flag = false;
            reader = new BufferedReader(new InputStreamReader(System.in));
            while (name.equals("")) {
                name = reader.readLine();
                if (!name.equals("")) {
                    flag = true;
                }
                if (!flag) {
                    log.writeLog("\n" + " warning! Empty name! ");
                    System.out.println("Enter not empty name,pls: ");
                }
            }
            System.out.println("Enter message: ");
            String mes = reader.readLine();
            long timest = date.getTime();
            if (!history.isEmpty()) {
                Integer tempPer = Integer.parseInt(history.get(history.size() - 1).getId()) + 1;
                String tempId = tempPer.toString();
                history.add(new MakeMail(tempId, name, timest, mes));
                log.writeLog(" completed successfully, add id: " + tempId + " message ");
                add++;
            } else {
                Integer tempPer = 1;
                String tempId = tempPer.toString();
                history.add(new MakeMail(tempId, name, timest, mes));
                log.writeLog(" completed successfully, add id: " + tempId + " message ");
                add++;
            }
        } catch (IOException e) {
            log.writeLog("\n" + "Buffer reader error" + e);
            System.out.println("Buffer reader error" + e);
        }
    }


    public void save() {
        log.writeLog("\n" + "Command \"2\"");
        Gson gson = new GsonBuilder().create();
        try {
            Writer writerOut = new FileWriter("input.json");
            gson.toJson(history, writerOut);
            writerOut.close();
            log.writeLog(" completed successfully");
        } catch (IOException e) {
            log.writeLog("\n" + "Input stream (save) error");
            System.out.println("Input stream (save) error" + e);
        }

    }

    public void load() {
        log.writeLog("\n" + "Command \"1\"");
        try {
            history.clear();
            Gson gson = new GsonBuilder().create();
            Reader reader = new InputStreamReader(new FileInputStream("input.json"));
            MakeMail[] mesArr = gson.fromJson(reader, MakeMail[].class);
            Collections.addAll(history, mesArr);
        } catch (IOException e) {
            log.writeLog("\n" + "Input stream error " + e);
            System.out.println("Input stream error " + e);
        }

        if (!history.isEmpty()) {
            log.writeLog(" completed successfully" + ", size: " + (history.size() - 1));
            System.out.println("Load completed successfully" + " size: " + (history.size() - 1));
        } else {
            log.writeLog(" empty file");
            System.out.println("Empty file");
        }
    }




    public void beginChat()
    {
        tasksMeny();
    }

    public void endChat() {
        log.writeLog("\n" + "Session statistic: " + "Delete: " + del + " messages | Add: " + add + " messages" +
                "\n" + "End Chat " + "\n");
        log.closeLog();
    }
}


