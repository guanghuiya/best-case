package com.meiqiu.去重;

import lombok.Data;

/**
 * @Description
 * @Author sgh
 * @Date 2025/2/25
 * @Time 15:26
 */
@Data
public class Item {

    private Integer itemId;

    private Integer skuId;

    public Item(Integer itemId, Integer skuId){
        this.itemId = itemId;
        this.skuId = skuId;
    }

    //重写 hashCode 方法
    @Override
    public int hashCode() {
        return itemId.hashCode() + skuId.hashCode();
    }

    //重写 equals 方法
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Item)){
            return false;
        }
        Item item = (Item) obj;
        return itemId.equals(item.itemId) && skuId.equals(item.skuId);
    }

}
