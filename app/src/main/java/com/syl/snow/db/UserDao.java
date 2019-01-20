package com.syl.snow.db;

import com.syl.snow.bean.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by Bright on 2019/1/20.
 *
 * @Describe data access object数据库操作类
 * @Called
 */
@Dao
public interface UserDao {
    @Query("select * from user")
    List<User> getAllUser();

    @Query("select * from user where uid>:startUid order by uid asc limit:limitUid")
    List<User> getPageUser(int startUid,int limitUid);

    @Query("select * from user where uid in (:ids)")
    List<User> loadAllByIds(int[] ids);

    @Query("select * from user where uid = (:id)")
    User findUserById(int id);

    @Insert
    void insert(User user);

    @Insert
    void insert(User...users);

    @Insert
    void insert(List<User> users);

    @Delete
    void delete(User user);

    /**
     * 备注: SQLite将@Insert(onConflict = REPLACE)作为REMOVE和REPLACE的集合来操作, 而非单独的UPDATE操作. 这个取代冲突值的方法能够影响你的外键约束.
     * @param user
     */
    @Update()
    void update(User user);
}
