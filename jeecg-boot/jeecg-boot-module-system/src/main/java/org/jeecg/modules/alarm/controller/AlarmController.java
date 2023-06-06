package org.jeecg.modules.alarm.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.alarm.entity.Alarm;
import org.jeecg.modules.alarm.entity.MAlarm;
import org.jeecg.modules.alarm.entity.alarm.AlarmBean;
import org.jeecg.modules.alarm.service.IMAlarmService;
import org.jeecg.modules.chemical.entity.MChemical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
* @Description: 报警数据
* @Author: jeecg-boot
* @Date:   2023-05-08
* @Version: V1.0
*/
@Slf4j
@Api(tags="报警数据回调")
@RestController
@RequestMapping("/alarm/import")
public class AlarmController extends JeecgController<MAlarm, IMAlarmService> {
   @Autowired
   private IMAlarmService mAlarmService;

   /**
    * 添加
    *
    * @param alarm
    * @return
    */
   @AutoLog(value = "报警数据-添加")
   @ApiOperation(value="报警数据-添加", notes="报警数据-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody AlarmBean alarm) {
//       log.info(JSON.toJSONString(alarm));
       MAlarm mAlarm = new MAlarm();
       String srcPicData = alarm.getAlarmPicData();
       alarm.setAlarmPicData("");
       alarm.setSrcPicData("");
       mAlarm.setAlarmType(alarm.getTaskId()).setCameraId(alarm.getCameraId()).setCameraName(alarm.getCameraName()).setSrcPicData(srcPicData).setJson(JSON.toJSONString(alarm));

       mAlarmService.save(mAlarm);
       return Result.ok("添加成功！");
   }

    /**
     * 编辑
     *
     * @param mAlarm
     * @return
     */
    @AutoLog(value = "报警数据-编辑")
    @ApiOperation(value="报警数据-编辑", notes="报警数据-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody MAlarm mAlarm) {
        mAlarmService.updateById(mAlarm);
        return Result.ok("编辑成功!");
    }

}
