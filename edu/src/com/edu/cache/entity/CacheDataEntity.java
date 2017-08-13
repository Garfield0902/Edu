package com.edu.cache.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CacheDataEntity{
    private String dbName;
    private String dbo;
    private String table;
    private String columns;
    private String conditions;
    private String key;
    private String fullCache;
    private String toMapField;
    private Map<String, Map<String, Object>> cacheMapData;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getDbo() {
        return dbo;
    }
    public void setDbo(String dbo) {
        this.dbo = dbo;
    }
    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    
    /**  
     * 获取list  
     * @return list list  
     */
    public List<Map<String, Object>> getList() {
        return list;
    }
    /**  
     * 设置list  
     * @param list list  
     */
    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
    /**  
     * 获取columns  
     * @return columns columns  
     */
    public String getColumns() {
        return columns;
    }
    /**  
     * 设置columns  
     * @param columns columns  
     */
    public void setColumns(String columns) {
        this.columns = columns;
    }
    /**  
     * 获取conditions  
     * @return conditions conditions  
     */
    public String getConditions() {
        return conditions;
    }
    /**  
     * 设置conditions  
     * @param conditions conditions  
     */
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
    /**  
     * 获取toMapField  
     * @return toMapField toMapField  
     */
    public String getToMapField() {
        return toMapField;
    }
    /**  
     * 设置toMapField  
     * @param toMapField toMapField  
     */
    public void setToMapField(String toMapField) {
        this.toMapField = toMapField;
    }
    
    /**  
     * 获取cacheMapData  
     * @return cacheMapData cacheMapData  
     */
    public Map<String, Map<String, Object>> getCacheMapData() {
        return cacheMapData;
    }
    /**  
     * 设置cacheMapData  
     * @param cacheMapData cacheMapData  
     */
    public void setCacheMapData(Map<String, Map<String, Object>> cacheMapData) {
        this.cacheMapData = cacheMapData;
    }
    /**  
     * 获取fullCache  
     * @return fullCache fullCache  
     */
    public String getFullCache() {
        return fullCache;
    }
    /**  
     * 设置fullCache  
     * @param fullCache fullCache  
     */
    public void setFullCache(String fullCache) {
        this.fullCache = fullCache;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CacheEntity [dbName=" + dbName + ", dbo=" + dbo + ", table=" + table + ", columns=" + columns
                + ", conditions=" + conditions + ", key=" + key + ", fullCache=" + fullCache + ", toMapField="
                + toMapField + ", cacheMapData=" + cacheMapData + ", list=" + list + "]";
    }
}
