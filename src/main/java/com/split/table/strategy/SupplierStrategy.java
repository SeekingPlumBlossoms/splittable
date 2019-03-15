package com.split.table.strategy;

import org.springframework.stereotype.Service;

/**
 * @author yebin
 * @version 1.0
 * @className SupplierStrategy
 * @description 策略类
 * @date 2019/3/14 17:01
 **/
@Service
public class SupplierStrategy implements Strategy{
    @Override
    public String getIndex(String name) {
        return "0000";
    }
}
