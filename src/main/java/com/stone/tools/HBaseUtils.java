package com.stone.tools;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stone
 * @date 2019/5/27 11:45
 * description HBase处理工具类
 */
public class HBaseUtils {
    private static Configuration conf = null;
    private static Connection conn = null;
    private static Admin admin = null;

    static {
        //HBaseConfiguration conf = new HBaseConfiguration();
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.30.60.62");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        //HBaseAdmin admin = new HBaseAdmin(conf);
        //boolean tableExists = admin.tableExists(tableName);

        try {
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     * @param conn
     * @param admin
     */
    private static void close(Connection conn, Admin admin) {
        if (conn != null) {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     * @throws MasterNotRunningException
     * @throws ZooKeeperConnectionException
     * @throws IOException
     */
    public static boolean tableExists(String tableName) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        boolean tableExists = admin.tableExists(TableName.valueOf(tableName));
        return tableExists;
    }

    /**
     * 创建表
     * @param tableName
     * @param cfs
     * @throws MasterNotRunningException
     * @throws ZooKeeperConnectionException
     * @throws IOException
     */
    public static void createTable(String tableName, String... cfs) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        if (tableExists(tableName)) {
            System.out.println("表已存在！！");
            return;
        }
        //创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        //添加列族
        for (String cf : cfs) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        admin.createTable(hTableDescriptor);
        System.out.println("表创建成功！！");
    }

    /**]
     * 删除表
     * @param tableName
     * @throws IOException
     */
    public static void deleteTable(String tableName) throws IOException {
        if (!tableExists(tableName)) {
            return;
        }
        //使表不可用
        admin.disableTable(TableName.valueOf(tableName));
        //执行删除操作
        admin.deleteTable(TableName.valueOf(tableName));
    }

    /**
     * 增&改记录
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     * @param value
     * @throws IOException
     */
    public static void putData(String tableName, String rowKey, String cf, String cn, String value) throws IOException {
        if (!tableExists(tableName)) {
            return;
        }
        //HTable table = new HTable(conf, TableName.valueOf(tableName));
        //获取表对象
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            //创建put对象
            Put put = new Put(Bytes.toBytes(rowKey));
            //添加数据
            put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
            //执行添加操作
            table.put(put);
        } finally {
            table.close();
            close(conn,admin);
        }
    }

    /**
     * 批量插入
     * @param tableName 表名
     * @param puts 多个Put对象
     * @throws IOException
     */
    public static void putData(String tableName, List<Put> puts) throws IOException {
        if (!tableExists(tableName)) {
            return;
        }
        //HTable table = new HTable(conf, TableName.valueOf(tableName));
        //获取表对象
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            //执行添加操作
            table.put(puts);
        } finally {
            table.close();
            close(conn,admin);
        }
    }

    /**
     * 追加插入(将原有value的后面追加新的value，如原有value=a追加value=bc则最后的value=abc)
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @param value
     * @throws IOException
     */
    public static void appendData(String tableName, String rowKey, String columnFamily, String column, String value) throws IOException {
        if (!tableExists(tableName)) {
            return;
        }
        //HTable table = new HTable(conf, TableName.valueOf(tableName));
        //获取表对象
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            // 通过rowkey创建一个append对象
            Append append = new Append(Bytes.toBytes(rowKey));
            // 在append对象中设置列族、列、值
            append.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
            // 追加数据
            table.append(append);
        } finally {
            // 关闭资源
            table.close();
            close(conn, admin);
        }
    }

    /**
     * 计数器(amount为正数则计数器加，为负数则计数器减，为0则获取当前计数器的值)
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @param amount
     * @return
     * @throws IOException
     */
    public static Long incrementColumnValue(String tableName, String rowKey, String columnFamily, String column, long amount) throws IOException {
        if (!tableExists(tableName)) {
            return 0L;
        }
        //HTable table = new HTable(conf, TableName.valueOf(tableName));
        //获取表对象
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            // 计数器
            long result = table.incrementColumnValue(Bytes.toBytes(rowKey), Bytes.toBytes(columnFamily), Bytes.toBytes(column), amount);
            // 关闭资源
            return result;
        } finally {
            table.close();
            close(conn, admin);
        }
    }

    /**
     * 删除数据
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     * @throws IOException
     */
    public static void deleteData(String tableName, String rowKey, String cf, String cn) throws IOException {
        if (!tableExists(tableName)) {
            return;
        }
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));
            //delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn)); 慎用，只会删除最新版本的数据
            table.delete(delete);
        } finally {
            table.close();
            close(conn, admin);
        }
    }

    /**
     * 查询
     * @param tableName
     * @throws MasterNotRunningException
     * @throws ZooKeeperConnectionException
     * @throws IOException
     */
    public static void scanTable(String tableName) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        if (!tableExists(tableName)) {
            return;
        }
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            Scan scan = new Scan();
            ResultScanner results = table.getScanner(scan);
            for (Result result : results) {
                Cell[] cells = result.rawCells();
                for (Cell cell : cells) {
                    System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell))
                            + ",CF:" + Bytes.toString(CellUtil.cloneFamily(cell))
                            + ",CN:" + Bytes.toString(CellUtil.cloneQualifier(cell))
                            + ",VALUE:" + Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        } finally {
            table.close();
            close(conn, admin);
        }
    }

    /**
     * 获取指定tableName，rowKey，cf，cn的值
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     * @return
     * @throws MasterNotRunningException
     * @throws ZooKeeperConnectionException
     * @throws IOException
     */
    public static String getData(String tableName, String rowKey, String cf, String cn) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
        if (!tableExists(tableName)) {
            return null;
        }
        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            Get get = new Get(Bytes.toBytes(rowKey));
            byte[] value = table.get(get).getValue(Bytes.toBytes(cf), Bytes.toBytes(cn));
            if (value == null || value.length == 0) {
                table.close();
                return null;
            } else {
                return Bytes.toString(value);
            }
        } finally {
            table.close();
            close(conn, admin);
        }

//        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
//		get.setMaxVersions();
//        Result result = table.get(get);
//        Cell[] cells = result.rawCells();
//
//        for (Cell cell : cells) {
//            System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell))
//                    + ",CF:" + Bytes.toString(CellUtil.cloneFamily(cell))
//                    + ",CN:" + Bytes.toString(CellUtil.cloneQualifier(cell))
//                    + ",VALUE:" + Bytes.toString(CellUtil.cloneValue(cell)));
//        }
//        table.close();
    }

    /**
     * 根据条件查询数据
     * @param tableName
     * @param condition
     * @return Map<String, Long> 其中键为rowKey，值为指定字段值，这里为Long型
     * @throws IOException
     */
    public static Map<String, Long> query(String tableName, String condition, String cf, String cn) throws IOException {
        Map<String, Long> map = new HashMap<>();

        if (!tableExists(tableName)) {
            return null;
        }

        Table table = conn.getTable(TableName.valueOf(tableName));
        try {
            Scan scan = new Scan();
            Filter filter = new PrefixFilter(Bytes.toBytes(condition));
            scan.setFilter(filter);

            ResultScanner rs = table.getScanner(scan);
            for (Result result : rs) {
                String rowKey = Bytes.toString(result.getRow());
                Long cnValue = Bytes.toLong(result.getValue(cf.getBytes(), cn.getBytes()));
                map.put(rowKey, cnValue);
            }
            return map;
        } finally {
            table.close();
            close(conn, admin);
        }
    }

    public static void main(String[] args) throws Exception {

//		System.out.println(tableExists("staff"));
//		createTable("staff", "info");
//		System.out.println(tableExists("staff"));
//		deleteTable("staff");
//		System.out.println(tableExists("staff"));

//		putData("student", "1003", "info", "name", "tom");
//		deleteData("student", "1001", "info", "age");
//		scanTable("student");
//      getData("student", "1002", "info", "name");

//        putData("iqiyi.log", "1001", "info", "name", "tom");

        Map<String, Long> map = HBaseUtils.query("iqiyi.category_search_count", "20190528", "info", "click_count");
        for (Map.Entry<String, Long> entry : map.entrySet()){
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
//        close(conn, admin);
    }
}
