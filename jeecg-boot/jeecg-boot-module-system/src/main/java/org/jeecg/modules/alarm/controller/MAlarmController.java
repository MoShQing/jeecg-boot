package org.jeecg.modules.alarm.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.alarm.entity.MAlarm;
import org.jeecg.modules.alarm.service.IMAlarmService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 报警数据
 * @Author: jeecg-boot
 * @Date:   2023-05-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="报警数据")
@RestController
@RequestMapping("/alarm/mAlarm")
public class MAlarmController extends JeecgController<MAlarm, IMAlarmService> {
	@Autowired
	private IMAlarmService mAlarmService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mAlarm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "报警数据-分页列表查询")
	@ApiOperation(value="报警数据-分页列表查询", notes="报警数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MAlarm mAlarm,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MAlarm> queryWrapper = QueryGenerator.initQueryWrapper(mAlarm, req.getParameterMap());
		Page<MAlarm> page = new Page<MAlarm>(pageNo, pageSize);
		IPage<MAlarm> pageList = mAlarmService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param mAlarm
	 * @return
	 */
	@AutoLog(value = "报警数据-添加")
	@ApiOperation(value="报警数据-添加", notes="报警数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MAlarm mAlarm) {
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
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报警数据-通过id删除")
	@ApiOperation(value="报警数据-通过id删除", notes="报警数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mAlarmService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "报警数据-批量删除")
	@ApiOperation(value="报警数据-批量删除", notes="报警数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mAlarmService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "报警数据-通过id查询")
	@ApiOperation(value="报警数据-通过id查询", notes="报警数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MAlarm mAlarm = mAlarmService.getById(id);
		return Result.ok(mAlarm);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param mAlarm
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MAlarm mAlarm) {
      return super.exportXls(request, mAlarm, MAlarm.class, "报警数据");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, MAlarm.class);
  }

}
