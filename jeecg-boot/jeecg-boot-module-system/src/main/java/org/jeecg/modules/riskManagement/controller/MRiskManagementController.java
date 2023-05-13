package org.jeecg.modules.riskManagement.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.modules.riskManagement.entity.MRiskManagement;
import org.jeecg.modules.riskManagement.service.IMRiskManagementService;
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
 * @Description: 安全风险管控清单
 * @Author: jeecg-boot
 * @Date:   2023-05-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="安全风险管控清单")
@RestController
@RequestMapping("/riskManagement/mRiskManagement")
public class MRiskManagementController extends JeecgController<MRiskManagement, IMRiskManagementService> {
	@Autowired
	private IMRiskManagementService mRiskManagementService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mRiskManagement
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "安全风险管控清单-分页列表查询")
	@ApiOperation(value="安全风险管控清单-分页列表查询", notes="安全风险管控清单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MRiskManagement mRiskManagement,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MRiskManagement> queryWrapper = QueryGenerator.initQueryWrapper(mRiskManagement, req.getParameterMap());
		Page<MRiskManagement> page = new Page<MRiskManagement>(pageNo, pageSize);
		IPage<MRiskManagement> pageList = mRiskManagementService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param mRiskManagement
	 * @return
	 */
	@AutoLog(value = "安全风险管控清单-添加")
	@ApiOperation(value="安全风险管控清单-添加", notes="安全风险管控清单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MRiskManagement mRiskManagement) {
		mRiskManagementService.save(mRiskManagement);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param mRiskManagement
	 * @return
	 */
	@AutoLog(value = "安全风险管控清单-编辑")
	@ApiOperation(value="安全风险管控清单-编辑", notes="安全风险管控清单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MRiskManagement mRiskManagement) {
		mRiskManagementService.updateById(mRiskManagement);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "安全风险管控清单-通过id删除")
	@ApiOperation(value="安全风险管控清单-通过id删除", notes="安全风险管控清单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mRiskManagementService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "安全风险管控清单-批量删除")
	@ApiOperation(value="安全风险管控清单-批量删除", notes="安全风险管控清单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mRiskManagementService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "安全风险管控清单-通过id查询")
	@ApiOperation(value="安全风险管控清单-通过id查询", notes="安全风险管控清单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MRiskManagement mRiskManagement = mRiskManagementService.getById(id);
		return Result.ok(mRiskManagement);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param mRiskManagement
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MRiskManagement mRiskManagement) {
      return super.exportXls(request, mRiskManagement, MRiskManagement.class, "安全风险管控清单");
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
      return super.importExcel(request, response, MRiskManagement.class);
  }

}
