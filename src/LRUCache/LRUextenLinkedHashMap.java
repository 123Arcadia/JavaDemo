package LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUextenLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int cacheSize;

    public LRUextenLinkedHashMap(int cacheSize) {
        // 设置初始容量、加载因子以及按访问顺序排序
        super(cacheSize, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当map大小超过设定的cacheSize时，移除最老（最少最近使用）的entry
        return size() > cacheSize;
    }
}