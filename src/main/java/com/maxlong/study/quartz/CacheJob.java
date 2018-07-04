package com.maxlong.study.quartz;

import com.maxlong.study.springcache.UserInfoDao;
import com.maxlong.study.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/7/7 18:03
 *          类说明
 */
@Slf4j
@Component(value = "CacheJob")
public class CacheJob {

    @Autowired
    private UserInfoDao userInfoDao;

    protected void execute() throws JobExecutionException, InvocationTargetException, IllegalAccessException {

        List<User> users;

        User user = new User("maxlong","28");
        User user2 = new User("wutinting","24");
        userInfoDao.add(user);
        userInfoDao.add(user2);
        users = userInfoDao.getAll();
        log.info("第一次查询结果：{}",users);

        User user3= new User("lannan","24");
        userInfoDao.add(user3);
        users = userInfoDao.getAll();
        log.info("第二次查询结果：{}",users);
    }
}
