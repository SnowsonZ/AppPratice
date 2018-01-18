package com.example.snowson.apptest.bean.ob;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

/**
 * Created by snowson on 18-1-18.
 * 自定义类型Test
 */
@Entity
public class Goods {
    @Id
    public long id;
    public String goodsName;
    public String goodsPrice;
    @Convert(converter = GoodsTypeConverter.class, dbType = Integer.class)
    public GoodsType goodsType;

    public enum GoodsType {
        TYPE_ONE(0), TYPE_TWO(1), TYPE_THREE(2);
        final int id;

        GoodsType(int id) {
            this.id = id;
        }
    }

    public static class GoodsTypeConverter implements PropertyConverter<GoodsType, Integer> {

        @Override
        public GoodsType convertToEntityProperty(Integer databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            for (GoodsType goodsType : GoodsType.values()) {
                if (goodsType.id == databaseValue) {
                    return goodsType;
                }
            }
            return GoodsType.TYPE_ONE;
        }

        @Override
        public Integer convertToDatabaseValue(GoodsType entityProperty) {
            return entityProperty == null ? null : entityProperty.id;
        }
    }

}
