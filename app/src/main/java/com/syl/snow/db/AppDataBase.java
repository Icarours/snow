package com.syl.snow.db;

import android.content.Context;

import com.syl.snow.bean.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Bright on 2019/1/20.
 *
 * @Describe 创建数据库
 * @Called
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                if (instance == null) {
                    instance = create(context);
                }
            }
        }
        return instance;
    }

    /**
     * 创建数据库
     * @param context
     * @return
     */
    private static AppDataBase create(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "snow-room-db").build();
    }

    //获得dao
    public abstract UserDao getUserDao();
}
