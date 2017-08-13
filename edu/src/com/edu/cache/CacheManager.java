package com.edu.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.edu.cache.entity.CacheDataEntity;
import com.edu.cache.entity.CacheEntity;
import com.edu.cache.service.PullDataToCache;

@Component
public class CacheManager implements InitializingBean, ServletContextAware {
    private static final Log log = LogFactory.getLog(CacheManager.class);

    private static final ConcurrentMap<String, CacheEntity> CACHE = new ConcurrentHashMap<String, CacheEntity>();

    private static ConcurrentMap<String, CacheDataEntity> cacheData = new ConcurrentHashMap<String, CacheDataEntity>();

    private ServletContext servletContext;

    @Autowired
    private PullDataToCache pullDataToCache;

//    @Value("${regiondeviceinit_reg_dev_task}")
    public String INIT_TASK = "ON";

//    @Value("${regiondeviceinitial_delay}")
    public long INITIAL_DELAY = 60000;// 初试延时执行时间  1分钟后执行

//    @Value("${regiondeviceflush_time}")
    public Integer FLUSH_TIME = 300000; // 10分钟创建定时刷新一次

    // 加载缓存
    public void load() {
        log.debug("--------- 加载xml配置文件中的缓存 start----------------");
        pullDataToCache.pullData();
        log.debug("--------- 加载xml配置文件中的缓存 end----------------");
    }

    // 重新加载缓存
    public void reload() {
        CACHE.clear();
        cacheData.clear();
        load();
    }

    /**
     * 获取某个缓存
     * 
     * @param key
     *            键
     * @return 如果Key不存在的话，返回一个空列表
     */
    public List<Object> getCache(String key) {
        CacheEntity cacheEntity = CACHE.get(key);
        if (cacheEntity == null) {
            return new ArrayList<Object>();
        } else {
            return CACHE.get(key).getList();
        }
    }

    /**
     * 获取某个缓存
     * 
     * @param key
     *            键
     * @return 如果Key不存在的话，返回一个空列表
     */
    public List<Map<String, Object>> getCacheData(String key) {
        CacheDataEntity cacheEntity = cacheData.get(key);
        if (cacheEntity == null) {
            return new ArrayList<Map<String, Object>>();
        } else {
            return cacheData.get(key).getList();
        }
    }
    
    // 获取某个表的map缓存
    public Map<String, Object> getCacheData(String key, String field) {
        CacheDataEntity cacheEntity = cacheData.get(key);
        if (cacheEntity == null) {
            return new HashMap<String, Object>();
        } else {
            return cacheData.get(key).getCacheMapData().get(field);
        }
        
    }

    /**
     * 添加一个对象入缓存，追加
     * 
     * @param key
     * @param o
     */
    public void putObj(String key, Object o) {
        List list = getCache(key);
        if (list.size() > 0) {
            list.add(o);
        } else {
            CacheEntity ce = new CacheEntity();
            list = ce.getList();
            list.add(o);
            CACHE.put(key, ce);
        }
    }

    /**
     * 添加一个对象入缓存，追加
     * 
     * @param key
     * @param o
     */
    public void putCacheData(String key, Map<String, Object> o) {
        List<Map<String, Object>> list = getCacheData(key);
        if (list.size() > 0) {
            list.add(o);
        } else {
            CacheDataEntity ce = new CacheDataEntity();
            list = ce.getList();
            list.add(o);
            cacheData.put(key, ce);
        }
    }

    /**
     * 添加一组数据入缓存,追加
     * 
     * @param key
     * @param ol
     */
    public void putAll(String key, Collection ol) {
        List list = getCache(key);
        if (list.size() > 0) {
            list.addAll(ol);
        } else {
            CacheEntity ce = new CacheEntity();
            list = ce.getList();
            list.addAll(ol);
            CACHE.put(key, ce);
        }
    }

    /**
     * 添加一组数据入缓存，覆盖
     * 
     * @param key
     * @param ol
     */
    public void putCollection(String key, Collection ol) {
        CacheEntity ce = new CacheEntity();
        List list = ce.getList();
        list.addAll(ol);
        CACHE.put(key, ce);
    }

    /**
     * 缓存key的 cacheEnty
     * 
     * @param key
     * @param cacheEnty
     * @param isOverRider
     *            如果key存在的话，是否覆盖原先的缓存
     */
    public void put(String key, CacheDataEntity cacheEnty, boolean isOverRider) {
        if (isOverRider) {
            cacheData.put(key, cacheEnty);
        } else {
            List<Map<String, Object>> tl = cacheEnty.getList();
            putAll(key, tl);
        }
    }

    // 缓存中删除数据
    public void remove(String key, Object o) {
        List list = CACHE.get(key).getList();
        if (list != null) {
            for (Object to : list) {
                if (to.equals(o)) {
                    list.remove(o);
                } else {
                    log.info(o + " 缓存中不存在该对象！");
                }
            }
        }
    }

    // 销毁缓存
    public void destory() {
        CACHE.clear();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加载系统缓存
        load();
        if (null != servletContext) {
            servletContext.setAttribute("CACHE", CACHE);
        }

        /**
         * 定时刷新逻辑缓存【10 分钟加载一次】
         */
        if (INIT_TASK.equals("ON")) {
            ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();
            executors.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    log.debug("---------------------- 加载逻辑缓存 start ------------------------");
                    pullDataToCache.pullLogicData();
//                    pullDataToCache.pullData();
                    log.debug("---------------------- 加载逻辑缓存 end  -------------------------");
                }
            }, INITIAL_DELAY, FLUSH_TIME, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void setServletContext(ServletContext cxt) {
        this.servletContext = cxt;
    }
}
