package org.jeecg.modules.management.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.management.entity.MDanger;
import org.jeecg.modules.management.service.IMDangerService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 重点危险品工艺
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="重点危险品工艺")
@RestController
@RequestMapping("/management/danger")
public class MDangerController extends JeecgController<MDanger, IMDangerService> {
	@Autowired
	private IMDangerService mDangerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mDanger
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "重点危险品工艺-分页列表查询")
	@ApiOperation(value="重点危险品工艺-分页列表查询", notes="重点危险品工艺-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MDanger mDanger,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MDanger> queryWrapper = QueryGenerator.initQueryWrapper(mDanger, req.getParameterMap());
		Page<MDanger> page = new Page<MDanger>(pageNo, pageSize);
		IPage<MDanger> pageList = mDangerService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param mDanger
	 * @return
	 */
	@AutoLog(value = "重点危险品工艺-添加")
	@ApiOperation(value="重点危险品工艺-添加", notes="重点危险品工艺-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MDanger mDanger) {
		mDangerService.save(mDanger);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param mDanger
	 * @return
	 */
	@AutoLog(value = "重点危险品工艺-编辑")
	@ApiOperation(value="重点危险品工艺-编辑", notes="重点危险品工艺-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MDanger mDanger) {
		mDangerService.updateById(mDanger);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重点危险品工艺-通过id删除")
	@ApiOperation(value="重点危险品工艺-通过id删除", notes="重点危险品工艺-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mDangerService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "重点危险品工艺-批量删除")
	@ApiOperation(value="重点危险品工艺-批量删除", notes="重点危险品工艺-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mDangerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重点危险品工艺-通过id查询")
	@ApiOperation(value="重点危险品工艺-通过id查询", notes="重点危险品工艺-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MDanger mDanger = mDangerService.getById(id);
		return Result.ok(mDanger);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param mDanger
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MDanger mDanger) {
      return super.exportXls(request, mDanger, MDanger.class, "重点危险品工艺");
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
      return super.importExcel(request, response, MDanger.class);
  }

}
