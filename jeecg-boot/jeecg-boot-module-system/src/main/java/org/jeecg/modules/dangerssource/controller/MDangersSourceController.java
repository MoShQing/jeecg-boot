package org.jeecg.modules.dangerssource.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.dangerssource.entity.MDangersSource;
import org.jeecg.modules.dangerssource.service.IMDangersSourceService;
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
 * @Description: 重大危险源
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="重大危险源")
@RestController
@RequestMapping("/dangerssource/mDangersSource")
public class MDangersSourceController extends JeecgController<MDangersSource, IMDangersSourceService> {
	@Autowired
	private IMDangersSourceService mDangersSourceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mDangersSource
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "重大危险源-分页列表查询")
	@ApiOperation(value="重大危险源-分页列表查询", notes="重大危险源-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MDangersSource mDangersSource,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MDangersSource> queryWrapper = QueryGenerator.initQueryWrapper(mDangersSource, req.getParameterMap());
		Page<MDangersSource> page = new Page<MDangersSource>(pageNo, pageSize);
		IPage<MDangersSource> pageList = mDangersSourceService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param mDangersSource
	 * @return
	 */
	@AutoLog(value = "重大危险源-添加")
	@ApiOperation(value="重大危险源-添加", notes="重大危险源-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MDangersSource mDangersSource) {
		mDangersSourceService.save(mDangersSource);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param mDangersSource
	 * @return
	 */
	@AutoLog(value = "重大危险源-编辑")
	@ApiOperation(value="重大危险源-编辑", notes="重大危险源-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MDangersSource mDangersSource) {
		mDangersSourceService.updateById(mDangersSource);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重大危险源-通过id删除")
	@ApiOperation(value="重大危险源-通过id删除", notes="重大危险源-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mDangersSourceService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "重大危险源-批量删除")
	@ApiOperation(value="重大危险源-批量删除", notes="重大危险源-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mDangersSourceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重大危险源-通过id查询")
	@ApiOperation(value="重大危险源-通过id查询", notes="重大危险源-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MDangersSource mDangersSource = mDangersSourceService.getById(id);
		return Result.ok(mDangersSource);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param mDangersSource
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MDangersSource mDangersSource) {
      return super.exportXls(request, mDangersSource, MDangersSource.class, "重大危险源");
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
      return super.importExcel(request, response, MDangersSource.class);
  }

}
