package org.jeecg.modules.gas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.gas.model.MGas;
import org.jeecg.modules.gas.service.MGasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: 气体报警
* @Author: jeecg-boot
* @Date:   2023-05-13
* @Version: V1.0
*/
@RestController
@RequestMapping("/alarm/gas")
@Slf4j
public class MGasController extends JeecgController<MGas, MGasService> {
   @Autowired
   private MGasService mGasService;

   /**
    * 分页列表查询
    *
    * @param MGas
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @GetMapping(value = "/list")
   public Result<?> queryPageList(MGas MGas,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<MGas> queryWrapper = QueryGenerator.initQueryWrapper(MGas, req.getParameterMap());
       Page<MGas> page = new Page<MGas>(pageNo, pageSize);
       IPage<MGas> pageList = mGasService.page(page, null);
       return Result.ok(pageList);
   }


   /**
    *  编辑
    *
    * @param MGas
    * @return
    */
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody MGas MGas) {
       mGasService.updateById(MGas);
       return Result.ok("编辑成功!");
   }
}
