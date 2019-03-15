package com.split.table.comman.dal.dobj;

import lombok.Data;

/**
 * @author yebin
 * @version 1.0
 * @className SupplierInfoDO
 * @description s
 * @date 2019/3/14 11:36
 **/
@Data
public class SupplierInfoDO {
    Integer id;
    String supplierCode;

    @Override
    public String toString() {
        return "SupplierInfoDO{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                '}';
    }
}
