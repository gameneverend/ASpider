package com.less.aspider.util;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import java.io.File;

/**
 * @author deeper
 * @date 2017/12/22
 */

public class BDBHelper {

    private StoredMap map;

    private Environment env;

    private Database catalogDatabase;
    private Database database;

    public BDBHelper(String dir) {
        File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        init(folder);
    }

    public void init(File folder) {
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setTransactional(true);
        envConfig.setAllowCreate(true);

        env = new Environment(folder,envConfig);

        // 设置DatabaseConfig
        DatabaseConfig dbConfig = new DatabaseConfig();
        // 不需要事务功能
        dbConfig.setTransactional(false);
        // 允许创建新的数据库文件
        dbConfig.setAllowCreate(true);
        // 设置一个key是否允许存储多个值，true代表允许，默认false.其中(class catalog database must not allow duplicates.此处只能是false)
        dbConfig.setSortedDuplicates(false);

        // open Database(如果有序列化绑定,则需要一个【元数据库】)
        catalogDatabase = env.openDatabase(null, "db_catalog.db", dbConfig);
        StoredClassCatalog storedClassCatalog = new StoredClassCatalog(catalogDatabase);

        // 设置DatabaseConfig(如果DatabaseConfig完全一样,可以重用上面的dbConfig)
        DatabaseConfig mydbConfig = new DatabaseConfig();
        mydbConfig.setTransactional(false);
        mydbConfig.setAllowCreate(true);
        mydbConfig.setSortedDuplicates(false);

        // open Database
        database = env.openDatabase(null, "db_urls.db", mydbConfig);

        // TupleBinding<String> keyBinding = TupleBinding.getPrimitiveBinding(String.class);
        EntryBinding keyBinding = new SerialBinding(storedClassCatalog, String.class);
        EntryBinding valueBinding = new SerialBinding(storedClassCatalog, Boolean.class);
        map = new StoredMap(database, keyBinding, valueBinding,true);
    }

    public void close() {
        database.close();
        catalogDatabase.close();
        // 同步内存中的数据到硬盘
        env.cleanLog();
        env.sync();
        env.close();
        env = null;
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public void put(String key,Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public void delete(String key) {
        map.remove(key);
    }

    public void clearAll() {
        map.clear();
    }
}
