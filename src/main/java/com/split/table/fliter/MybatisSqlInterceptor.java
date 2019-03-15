package com.split.table.fliter;


import com.split.table.strategy.Strategy;
import com.split.table.strategy.SupplierStrategy;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Properties;

/**
 * @author yebin
 * @version 1.0
 * @className MybatisSqlInterceptor
 * @description sql拦截
 * @date 2019/3/14 11:44
 **/

@Intercepts(@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class MybatisSqlInterceptor implements Interceptor {


    public final static HashSet<String> topicSet = new HashSet<>();

    public final static LinkedHashMap<String,String> ruleMap = new LinkedHashMap<>();

    public final static LinkedHashMap<String, Strategy> tbaleMap
            = new LinkedHashMap<>();


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();

        MappedStatement ms = (MappedStatement) args[0];
        //获取执行的sql
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        System.out.println(boundSql.getSql());
        String sql = boundSql.getSql();
        topicSet.forEach((s) -> {
            if (sql.contains(s)) {
             String className= ruleMap.get(s);
                Strategy ruleStrategy=tbaleMap.get(className);
                if(ruleStrategy == null){
                    throw new RuntimeException("表名为"+s+"未找到分库分表的实现类");
                }
                String sqls = sql.replace(s, s + "_"+ruleStrategy.getIndex(""));
                //改写下表
                System.out.println(sqls);
                return;
            }
        });

        //获取操作类型
        System.out.println(ms.getSqlCommandType().compareTo(SqlCommandType.SELECT) == 0);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
