package org.jeecg.modules.cinema.generator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.cinema.generator.entity.Star;
import org.jeecg.modules.cinema.generator.service.IStarService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 演员信息
 * @Author: jeecg-boot
 * @Date:   2023-03-11
 * @Version: V1.0
 */
@Api(tags="演员信息")
@RestController
@RequestMapping("/generator/star")
@Slf4j
public class StarController extends JeecgController<Star, IStarService> {
	@Autowired
	private IStarService starService;
	
	/**
	 * 分页列表查询
	 *
	 * @param star
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "演员信息-分页列表查询")
	@ApiOperation(value="演员信息-分页列表查询", notes="演员信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Star>> queryPageList(Star star,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Star> queryWrapper = QueryGenerator.initQueryWrapper(star, req.getParameterMap());
		Page<Star> page = new Page<Star>(pageNo, pageSize);
		IPage<Star> pageList = starService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param star
	 * @return
	 */
	@AutoLog(value = "演员信息-添加")
	@ApiOperation(value="演员信息-添加", notes="演员信息-添加")
	@RequiresPermissions("generator:fly_cinema_star:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Star star) {
		starService.save(star);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param star
	 * @return
	 */
	@AutoLog(value = "演员信息-编辑")
	@ApiOperation(value="演员信息-编辑", notes="演员信息-编辑")
	@RequiresPermissions("generator:fly_cinema_star:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Star star) {
		starService.updateById(star);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "演员信息-通过id删除")
	@ApiOperation(value="演员信息-通过id删除", notes="演员信息-通过id删除")
	@RequiresPermissions("generator:fly_cinema_star:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		starService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "演员信息-批量删除")
	@ApiOperation(value="演员信息-批量删除", notes="演员信息-批量删除")
	@RequiresPermissions("generator:fly_cinema_star:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.starService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "演员信息-通过id查询")
	@ApiOperation(value="演员信息-通过id查询", notes="演员信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Star> queryById(@RequestParam(name="id",required=true) String id) {
		Star star = starService.getById(id);
		if(star==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(star);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param star
    */
    @RequiresPermissions("generator:fly_cinema_star:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Star star) {
        return super.exportXls(request, star, Star.class, "演员信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("generator:fly_cinema_star:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Star.class);
    }

}
