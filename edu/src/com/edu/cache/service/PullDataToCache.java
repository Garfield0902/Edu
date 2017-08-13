package com.edu.cache.service;


public interface PullDataToCache {
	/**
	 * 加载xml配置中的缓存
	 * @param cache
	 */
	public void pullData();
	/**
	 * 加载逻辑缓存
	 * @param cache
	 */
	public void pullLogicData();
}
