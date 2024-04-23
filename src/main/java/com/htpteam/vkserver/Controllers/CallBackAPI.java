package com.htpteam.vkserver.Controllers;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@RestController

public class CallBackAPI {

    private  boolean isReported = false;
    public boolean isTimeout = true;
    String url = "https://webdav.yandex.ru/WebDav/";
    Sardine sardine = SardineFactory.begin("mail", "password");

    @RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json")
    public String CallBack(@RequestBody String JSON_response) throws JSONException {
JSONObject JSON = new JSONObject(JSON_response);
        try
        {
            if (JSON.getString("type").equals("confirmation"))
            {
                String value = new BufferedReader(new InputStreamReader(sardine.get(url + "confirm.txt"), "UTF-8")).readLine();
                if(new JSONObject(value).getString("isChecking").equals("true"))
                {
                    return  new JSONObject(value).getString("code");
                }
                else
                {

                    return new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/groups.getCallbackConfirmationCode?group_id=174262647&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONObject("response").getString("code");
                }
            }

            else
            {
             new Thread(new Runnable() {
                 @Override
                 public void run() {

try {
    String buff;
    JSONObject object = JSON.getJSONObject("object");
    try {
        JSONArray fwd = object.getJSONArray("fwd_messages");
        if (fwd.length() > 1) {
            buff = "more 2";
        } else {
            buff = fwd.getJSONObject(0).getString("from_id");
        }
    } catch (Exception ex) {
        buff = "more 2";
    }
    _newMessage(object.getLong("peer_id"), object.getLong("from_id"), object.getString("text"), buff);
}catch (Exception e){}
                 }
             }).start();
                return "ok";
            }

        }
        catch (Exception e)
        {
            return e.toString();
        }

    }
long buffer_peer_id;
    int time;
    JSONObject obj;
    long gulag_id;

public int voted;
    public void _newMessage(long peer_id,long from_id, String text,String forward) {
        try {
            String value = new BufferedReader(new InputStreamReader(sardine.get(url + "command-list.txt"), "UTF-8")).readLine();
            String[] array;
            if(peer_id==2000000007)
            {
                SendTelegram("Новое сообщение из беседы Just a conversation- \nот "+returnNameUser(from_id) + ":\n"+text);
            }

            if(text.substring(0,1).equals("!")) {

                array = text.split("_");
                for (int i = 0; i < array.length; i++) {
                    System.out.println(array[i]);
                }
                if(array[0].equals("!Мой уровень")||array[0].equals("!мой уровень"))
                {
                    JSONObject score_users = new JSONObject(new BufferedReader(new InputStreamReader(sardine.get(url + "score-" + peer_id + ".txt"), "UTF-8")).readLine());
                    JSONObject lvl_users = new JSONObject(new BufferedReader(new InputStreamReader(sardine.get(url +peer_id + ".txt"), "UTF-8")).readLine());
                    int buff = 100-score_users.getInt(""+from_id);
                    SendMessage(peer_id,returnDomainuser(from_id) + ", ваш текущий уровень - "+lvl_users.getString(from_id+"") + ", до следующего осталось - " + buff);
                }
                if(array[0].equals("!tribunal"))
                {
                    if(forward.equals("more 2"))
                    {
                        SendMessage(peer_id,"Команду tribunal надо писать в ответ на сообщение человека");
                    }
                    else
                    {
                        if(isReported == true)
                        {
                            SendMessage(peer_id,"Трибунал уже активирован, дождитесь пока закончится.");
                        }else {
                            SendMessage(peer_id,"Пишем \"+\" в беседу за понижение уровня пользователя ");
                            gulag_id = Integer.parseInt(forward);
                            buffer_peer_id = peer_id;
                            isReported = true;
                        }
                    }

                }

                try {
                    if (array[0].equals("!new")) { //на что реагируем     ответ бота
                        JSONObject object = new JSONObject(value);
                        object.put("!" + array[1], array[2]);
                        sardine.put(url + "command-list.txt", object.toString().getBytes(StandardCharsets.UTF_8));
                        SendMessage(peer_id, "Добавлено успешно!");

                    }
                }catch (Exception ex)
                {
                    SendMessage(peer_id, "Правильное использование команды - !new_Команда на который должен среагировать бот_Ответ бота на сообщение \r\n Пример: !new_Привет_Пошёл нахуй");

                }



try{
                    JSONObject object = new JSONObject(value);

                    SendMessage(peer_id, object.getString(text));}catch (Exception e){}

            }

            if(isTimeout == true) {
                isTime();
                messageScore(peer_id, from_id);

            }
            if (isReported == true)
            {

                if(time == 0) {
                    obj = usrs_list(peer_id);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 60; i > 0; i--) {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception ex) {
                                }
                            }
                            isReported = false;
                            Ishighvotes(peer_id,voted , gulag_id);
                            voted = 0;
                          //  SendMessage(peer_id, "Трибунал завершён.");
                            obj = null;
                            time = 0;
                        }
                    }).start();
                    time = 1;
                }
                if (buffer_peer_id == peer_id && text.equals("+"))
                {

                        if(obj.getInt(""+from_id)==1)
                        {
                            SendMessage(peer_id,returnDomainuser(from_id) + " вы уже проголосовали...");
                        }
                        else
                        {
                         obj.put(from_id+"",1);   voted++;
                        }

                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void SendTelegram(final String value)
    {
        try{
            URL urll = new URL("https://api.telegram.org/bot1760557470:AAGCb730koE7i06ZAyuPRPacWy1rfuhycgo/sendMessage?chat_id=-1001682524854&text="+URLEncoder.encode(value));
            BufferedReader inn = new BufferedReader(new InputStreamReader(urll.openStream()));
            final String ggg = inn.readLine();
            //Toast.makeText(getBaseContext(),gg,Toast.LENGTH_LONG).show();
        }catch(final Exception e){


        }


    }
    public void SendMessage(long peer_id, String text) {
        try {
            new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/messages.send?peer_id=" + peer_id + "&message=" + URLEncoder.encode(text) + "&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private JSONObject usrs_list(long peer_id)
    {JSONObject buff = null;
        try {
            JSONObject responseUsers = new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/messages.getConversationMembers?peer_id=" + peer_id + "&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONObject("response");
            JSONArray arr = responseUsers.getJSONArray("items");
             buff = new JSONObject();
            for (int i = 0; i < responseUsers.getInt("count"); i++) {
                buff.put(arr.getJSONObject(i).getString("member_id"), 0);
            }
        }catch (Exception e){}
        return buff;
    }
    public synchronized void messageScore(long peer_id,long from_id) throws Exception {
        try {

                String value = new BufferedReader(new InputStreamReader(sardine.get(url + "score-" + peer_id + ".txt"), "UTF-8")).readLine();
                JSONObject users = new JSONObject(value);
                JSONObject newbuff = new JSONObject(value);
                JSONObject userslist = new JSONObject(new BufferedReader(new InputStreamReader(sardine.get(url + peer_id + ".txt"), "UTF-8")).readLine());

                try {
                    int count = users.getInt("" + from_id) + 1;
                    if (count >= 100) {
                        count = 0;
                        userslist.put("" + from_id, userslist.getInt("" + from_id) + 1);

                        sardine.put(url + peer_id + ".txt", userslist.toString().getBytes());
                        SendMessage(peer_id, "Поздравим " + returnDomainuser(from_id) + " с " + userslist.getInt("" + from_id) + " уровнем!");
                    }
                    newbuff.put("" + from_id, count);
                    sardine.put(url + "score-" + peer_id + ".txt", newbuff.toString().getBytes());


                } catch (Exception ex) {
                    userslist.put("" + from_id, 0);
                    users.put("" + from_id, 0);
                    sardine.put(url + "score-" + peer_id + ".txt", users.toString().getBytes());
                    sardine.put(url + peer_id + ".txt", userslist.toString().getBytes());
                }
            }
        catch(Exception e)
            {
                users("" + peer_id, "" + from_id);
                System.out.println(e);
            }

    }
    public JSONObject users(String peer_id,String from_id) throws Exception {
        try {
            String value = new BufferedReader(new InputStreamReader(sardine.get(url + peer_id + ".txt"), "UTF-8")).readLine();
return new JSONObject(value);
        } catch (Exception e) {
            JSONObject responseUsers = new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/messages.getConversationMembers?peer_id=" + peer_id + "&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONObject("response");
            JSONArray array = responseUsers.getJSONArray("items");
            JSONObject buff = new JSONObject();
            JSONObject scoreusers = new JSONObject();
            for (int i = 0; i < responseUsers.getInt("count"); i++) {
                buff.put(array.getJSONObject(i).getString("member_id"),0);
                scoreusers.put(array.getJSONObject(i).getString("member_id"),0);
            }
            sardine.put(url+peer_id + ".txt", buff.toString().getBytes());
            sardine.put(url+"score-"+peer_id + ".txt", scoreusers.toString().getBytes());
            return buff;
        }
    }



    public String returnDomainuser(long id) throws Exception {
     return "@"+new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/users.get?user_ids="+id+"&fields=domain&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONArray("response").getJSONObject(0).getString("domain");

    }
    public String returnNameUser(long id) throws Exception {
        return ""+new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/users.get?user_ids="+id+"&fields=domain&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONArray("response").getJSONObject(0).getString("first_name") +" " +
                new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/users.get?user_ids="+id+"&fields=domain&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONArray("response").getJSONObject(0).getString("last_name");

    }
    public static ArrayList<String> keylist = new ArrayList<String>();
    public void Ishighvotes(long peer_id,int voted,long from_id)
    {
        try
        {

            JSONObject peer = new JSONObject(new BufferedReader(new InputStreamReader(sardine.get(url + peer_id + ".txt"), "UTF-8")).readLine());
            JSONObject buff = null;

                JSONObject responseUsers = new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/messages.getConversationMembers?peer_id=" + peer_id + "&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONObject("response");
                JSONArray arr = responseUsers.getJSONArray("items");




            int count_users = responseUsers.getInt("count");
            double minvotes = 1.0*voted / count_users;
            int percent = (int) (minvotes * 100);
            if(percent >= 30)
            {
                SendMessage(peer_id,"Проголосовало "+percent + "%, уровень "+ returnDomainuser(from_id) + " понижен.");
                long user_lvl = peer.getInt(""+from_id) - 1;
                peer.put(""+from_id,user_lvl);
                sardine.put(url +peer_id+".txt", peer.toString().getBytes());
                keylist.clear();
            }
            else
            {
                SendMessage(peer_id,"В голосовании приняло слишком мало человек: "+percent+"%");
                keylist.clear();
            }


        }
        catch(Exception e)
        {
System.out.println(e);
if(e.toString().equals("java.lang.ArithmeticException: / by zero"))
{
    SendMessage(peer_id,"В голосовании никто не принял участие.");
}
        }
    }

private void isTime()
{
    new Thread(new Runnable() {
        @Override
        public void run() {
            isTimeout = false;
            for (int i = 10; i > 0; i--)
            {

               try{Thread.sleep(1000);}catch (Exception e){}

            }
            isTimeout = true;

        }
    }).start();
}


    public static void myfunction(JSONObject x) throws JSONException
    {
        JSONArray keys =  x.names();
        for(int i=0;i<keys.length();i++)
        {
            String current_key = keys.get(i).toString();
            if( x.get(current_key).getClass().getName().equals("org.json.JSONObject"))
            {
                keylist.add(current_key);
                myfunction((JSONObject) x.get(current_key));
            }
            else if( x.get(current_key).getClass().getName().equals("org.json.JSONArray"))
            {
                for(int j=0;j<((JSONArray) x.get(current_key)).length();j++)
                {
                    if(((JSONArray) x.get(current_key)).get(j).getClass().getName().equals("org.json.JSONObject"))
                    {
                        keylist.add(current_key);
                        myfunction((JSONObject)((JSONArray) x.get(current_key)).get(j));
                    }
                }
            }
            else
            {
                keylist.add(current_key);
            }
        }
    }


long[] spam_user;
public boolean spamfilter(long peer_id,long from_id)
{
    try
    {
        JSONObject spamdata = new JSONObject(new BufferedReader(new InputStreamReader(sardine.get(url+"spamdata-"+peer_id+".txt"), "UTF-8")).readLine());
        for (int i = 0; i < 5; i++)
        {
            spam_user[i] = spamdata.getInt(""+i);
        }
        for(int i = 0; i < 5; i++)
        {
            if(spam_user[i] == 0)
            {
                spam_user[i] = from_id;
                spamdata.put(""+i,from_id);
                sardine.put(url + "spamdata-"+peer_id+".txt", spamdata.toString().getBytes());
                break;
            }
            if(spam_user[0] != 0&&spam_user[1] != 0&&spam_user[2] != 0&&spam_user[3] != 0&&spam_user[4] != 0)
            {
                if(spam_user[0] == spam_user[1]&&spam_user[1] == spam_user[2]&& spam_user[2] == spam_user[3]&& spam_user[3] == spam_user[4])
                {

return true;
                }
            }
        }

    }
    catch (Exception e)
    {
        try {
            JSONObject json = new JSONObject()
                    .put("0", "0")
                    .put("1", "0")
                    .put("2", "0")
                    .put("3", "0")
                    .put("4", "0");
            sardine.put(url + "spamdata-"+peer_id+".txt", json.toString().getBytes());
        }catch (Exception exception){}
        return false;
    }

    return false;
}





}
