package com.kotall.learn.dynamicds.ds;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/29 11:34
 * @since 1.0.0
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

}
