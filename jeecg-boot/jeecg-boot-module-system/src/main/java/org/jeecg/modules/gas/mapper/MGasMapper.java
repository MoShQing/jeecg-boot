package org.jeecg.modules.gas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.gas.model.MGas;

import java.util.Collection;

/**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
public interface MGasMapper extends BaseMapper<MGas> {
    boolean saveOrUpdateBatch(MGas mGas);
}
