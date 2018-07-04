package com.maxlong.study.datacollection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2017/7/7 14:36
 *          类说明
 */
public class CollectionDataBase {

    private static Vector<ConcurrentHashMap<String,Object>> vector = new Vector<>();

    private static volatile CollectionDataBase instance;

    public static CollectionDataBase getInstance(){
        synchronized(CollectionDataBase.class){
            if(instance==null){
                instance = new CollectionDataBase();
            }
        }
        return instance;
    }

    private CollectionDataBase(){}

    public Boolean insertData(Map<String,Object> map){
        ConcurrentHashMap<String,Object> insertMap = new ConcurrentHashMap<>();
        Integer index = (Integer) map.get("index");
        if(index!=null){
            for(ConcurrentHashMap<String,Object> concurrentHashMap : vector){
               Integer o = (Integer) concurrentHashMap.get("index");
               if(o.equals(index)) return false;
            }
        } else {
            int max = -1;
            for(ConcurrentHashMap<String,Object> concurrentHashMap : vector){
                int o = (int) concurrentHashMap.get("index");
                if(max<o){max = o;}
            }
            map.put("index",max + 1);
        }
        insertMap.putAll(map);
        return vector.add(insertMap);
    }

    public boolean deleteDataByMap(Map<String,Object> map){
        Integer index = (Integer) map.get("index");
        if(index!=null || index < vector.size()){
            ConcurrentHashMap<String,Object> concurrentHashMap = vector.get(index);
            return vector.remove(concurrentHashMap);
        }else {
            return false;
        }
    }

    public int updateDataByMap(Map<String,Object> map){
        Integer index = (Integer) map.get("index");

        if(index!=null ||index < vector.size()){
            ConcurrentHashMap<String,Object> record = vector.get(index);
            Map<String,Object> recordBak = new HashMap<>();
            recordBak.putAll(record);
            Set<String> ketSet = map.keySet();
            for(String key : ketSet){
                if(record.replace(key,map.get(key))==null){
                    record.putAll(recordBak);
                    return -1;
                }
            }
            return 1;
        }else {
            return -1;
        }
    }

    public boolean deleteDataById(String id){
        Integer index = Integer.valueOf(id);
        boolean result = false;
        for(int i = 0;i<vector.size();i++){
            ConcurrentHashMap<String,Object> concurrentHashMap = vector.get(i);
            Integer o = (Integer) concurrentHashMap.get("index");
            if(o.equals(index)) {
                return vector.remove(concurrentHashMap);
            }
        }
        return result;
    }

    public Map<String,Object> selectDataByIndex(int index){
        return vector.get(index);
    }

    public List<Map<String,Object>> selectAllData(){
        List<Map<String,Object>> resultList = new ArrayList<>();

        for(ConcurrentHashMap<String,Object> concurrentHashMap : vector){
            Map<String,Object> map = new HashMap<>();
            map.putAll(concurrentHashMap);
            resultList.add(map);
        }
        return resultList;
    }

}
