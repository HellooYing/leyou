package com.leyou.item.mapper;

import com.leyou.item.pojo.Spu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface SpuMapper extends Mapper<Spu> {
    @Update("UPDATE tb_spu SET saleable=0 WHERE id=#{id}")
    int obtainedGoods(@Param("id") Long id);

    @Update("UPDATE tb_spu SET saleable=1 WHERE id=#{id}")
    int shelfGoods(@Param("id") Long id);
}
