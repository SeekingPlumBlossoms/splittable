package com.split.table;

import com.split.table.comman.dal.dobj.SupplierInfoDO;
import com.split.table.mapper.SupplierMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.split.table.mapper")
public class TableApplicationTests {
    @Autowired
    SupplierMapper supplierMapper;


    @Test
    public void contextLoads() {

        SupplierInfoDO supplierInfoDO = new SupplierInfoDO();
        supplierInfoDO.setSupplierCode("20190304204501");
        SupplierInfoDO supplierInfoDOs = supplierMapper.querySupplierInfo(supplierInfoDO);
        System.out.println(supplierInfoDOs==null?"":supplierInfoDOs.toString());
    }

}
