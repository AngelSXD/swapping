package com.sxd.swapping.WorkSheetUtils;


import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 * 可接受操作类型： 新增  更新  删除
 *
 *
 * =============已实现================
 * 新增：
 *  新增表的列
 *
 *
 * =============未实现=================
 * 更新：
 *  更新列的类型信息
 *  重命名列[此处用不到]
 *
 *
 *
 * 删除：
 *  删除表的列
 *  删除索引[待定]
 *
 *
 * =============注意事项================
 *
 *  【注意：此工具类仅提供DDL语句的生成，不做数据库相关操作，不检查新增列的合法性】
 *
 */


public class DDLCreater {


    /**
     * 统一表名头
     */
    public static final String TABLE_NAME = "worksheet_data_";

    /**
     * DDL头规格
     */
    public static final String ALTER_TABLE = "ALTER TABLE %s ";

    /**
     * ADD规格
     */
    public static final String ADD_COL_DDL = " ADD  %s  %s %s ,";




    /**
     * 构造方法 完善表名
     * @param dataId
     */
    public DDLCreater(Long dataId) {
        super();
        this.tableName = TABLE_NAME + dataId;
    }


    private String tableName;//表名

    private List<String> colList = new ArrayList<>();//列List

    private List<Boolean> requiredList = new ArrayList<>();//是否必填List


    /**
     * 添加列信息
     * @param field 列名 eq:  input_0   textarea_1
     */
    public void addField(String field) {
        this.addField(field, false);
    }

    /**
     * 添加列信息
     * @param field 列名 eq:  input_0   textarea_1
     * @param required  是否必填 默认false 不必填
     */
    public void addField(String field, boolean required) {
        colList.add(field);
        requiredList.add(required);
    }


    /**
     * 获取 新增列 DDL
     * Add DDL
     *
     * @return
     */
    public String getDDL() {
        StringBuffer result = new StringBuffer();
        result.append(String.format(ALTER_TABLE,this.tableName));

        for (int i = 0; i < colList.size(); i++) {
            String field = colList.get(i);
            result.append(
                    String.format(
                            ADD_COL_DDL,field,
                            getDataType(field.split("_")[0]),
                            requiredList.get(i) ? "NOT NULL" : ""));
        }

        deleteLastChar(result);

        return result.toString();
    }





    /**
     * 根据列类型 获取数据库字段类型
     *
     * @param fieldType 列类型
     * @return
     */
    private String getDataType(String fieldType) {
        String dataType = " varchar(255) ";
        switch (fieldType) {
            case "input":
                break;
            case "textarea":
                dataType = " text ";
                break;
            case "input-number":
                dataType = " varchar(100) ";
                break;
            case "date":
                dataType = " timestamp ";
                break;
            case "radio":
                break;
            case "checkbox":
                dataType = " varchar(500) ";
                break;
            case "select":
                break;
            case "area":
                dataType = " text ";
                break;
            case "location":
                dataType = " varchar(30) ";
                break;
            case "file":
                dataType = " text ";
                break;
            case "serial":
                break;
            case "mobile":
                dataType = " varchar(11) ";
                break;
            case "dept-user":
                break;
            case "dept-base":
                break;
            default:
                break;
        }

        return dataType;
    }


    /**
     * 将StringBuffer最后一位, 删除
     *
     * @param sb
     */
    private void deleteLastChar(StringBuffer sb) {
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
    }



}

