package com.maxlong.study.springcache;

import com.maxlong.study.datacollection.CollectionDataBase;
import com.maxlong.study.vo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/7/7 17:16
 *          类说明
 */
@Component
public class UserInfoDaoImpl  implements  UserInfoDao{

    @Override
    @CacheEvict(value = "usersCache", allEntries=true)
    public void add(User user) throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> map = new HashMap<>();
        if (user != null) {
            BeanMap beanMap = BeanMap.create(user);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        CollectionDataBase.getInstance().insertData(map);
    }

    @Override
    public void delete(String id) {
        CollectionDataBase.getInstance().deleteDataById(id);
    }

    @Override
    public void update(User user) throws InvocationTargetException, IllegalAccessException {
        Map<String,Object> map = new HashMap<>();
        BeanUtils.populate(user,map);
        CollectionDataBase.getInstance().updateDataByMap(map);
    }

    @Override
    public User find(String id) {
        return null;
    }

    @Override
    @Cacheable(value = "usersCache")
    public List<User> getAll() throws InvocationTargetException, IllegalAccessException {
        List<Map<String,Object>> resList = CollectionDataBase.getInstance().selectAllData();
        List<User> userList= new ArrayList<>();
        for(Map<String,Object> map : resList){
            User user = new User();
            BeanUtils.populate(user,map);
            userList.add(user);
        }
        return userList;
    }
}
