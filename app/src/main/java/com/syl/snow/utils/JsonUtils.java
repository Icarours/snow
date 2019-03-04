package com.syl.snow.utils;

import android.content.Context;

import com.syl.snow.bean.User2;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Bright on 2019/3/4.
 *
 * @Describe 将对象保存到文件
 * @Called
 */
public class JsonUtils {
    private Context mContext;
    private String mFileName;

    public JsonUtils(Context context, String fileName) {
        mContext = context;
        mFileName = fileName;
    }

    /**
     * 将对象保存到文件
     *
     * @param list
     * @throws JSONException
     * @throws IOException
     */
    public void setFileName(ArrayList<User2> list) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (User2 user2 : list) {
            array.put(user2.toJSON());
        }
        Writer writer = null;
        try {
            OutputStream outputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(outputStream);
            writer.write(array.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 从文件中加载数据,并转化为对象
     *
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public ArrayList<User2> loadUser2() throws IOException, JSONException {
        ArrayList<User2> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray array = new JSONArray(jsonString.toString());
            for (int i = 0; i < array.length(); i++) {
                list.add(new User2(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return list;
    }
}
