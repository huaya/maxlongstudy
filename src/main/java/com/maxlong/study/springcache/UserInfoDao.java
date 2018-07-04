package com.maxlong.study.springcache;

import com.maxlong.study.vo.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/7/7 14:09
 *          类说明
 */
public interface UserInfoDao {

    void add(User user) throws InvocationTargetException, IllegalAccessException;

    void delete(String id);

    void update(User user) throws InvocationTargetException, IllegalAccessException;

    User find(String id);

    List<User> getAll() throws InvocationTargetException, IllegalAccessException;

}
