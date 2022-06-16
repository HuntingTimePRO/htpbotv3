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



    @RequestMapping(value = "/api", method = RequestMethod.POST, consumes = "application/json")
    public String CallBack(@RequestBody String JSON_response) throws JSONException {
JSONObject JSON = new JSONObject(JSON_response);
        try
        {
            if (JSON.getString("type").equals("confirmation"))
            {
               return new JSONObject(new BufferedReader(new InputStreamReader(new URL("https://api.vk.com/method/groups.getCallbackConfirmationCode?group_id=174262647&v=5.81&access_token=113248abacfc513252b96c99b8fc8a562a3ead722425909826efd7197f77e5a8a5371f32f14b2568425bf").openStream())).readLine()).getJSONObject("response").getString("code");
            }
            else
            {
                JSONObject object = JSON.getJSONObject("object");
               _newMessage(object.getLong("peer_id"), object.getString("text"));
                return "ok";
            }

        }
        catch (Exception e)
        {
            return e.toString();
        }

    }

    String url = "https://webdav.yandex.ru/WebDav/";
    Sardine sardine = SardineFactory.begin("hunting.time@yandex.ru", "qzvmnviyhsqtmelo");
    public void _newMessage(long peer_id, String text) {
        try {
            String value = new BufferedReader(new InputStreamReader(sardine.get(url + "command-list.txt"), "UTF-8")).readLine();
            String[] array;
            if(text.substring(0,1).equals("!")) {
                array = text.split("_");
                for (int i = 0; i < array.length; i++) {
                    System.out.println(array[i]);
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
                    SendMessage(peer_id, "Правильное использование команды - !new_Текст на который должен среагировать бот_Ответ бота на сообщение \r\n Пример: !new_Привет_Пошёл нахуй");
                }

                    JSONObject object = new JSONObject(value);
                    SendMessage(peer_id, object.getString(text));

            }


        }
        catch (Exception e)
        {
            System.out.println(e);
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
}
