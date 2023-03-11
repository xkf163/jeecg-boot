package org.jeecg.modules.cinema.controller;

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
import org.jeecg.modules.cinema.entity.Film;
import org.jeecg.modules.cinema.service.IFilmService;

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
 * @Description: 电影信息
 * @Author: jeecg-boot
 * @Date:   2023-03-11
 * @Version: V1.0
 */
@Api(tags="电影信息")
@RestController
@RequestMapping("/cinema/film")
@Slf4j
public class FilmController extends JeecgController<Film, IFilmService> {
	@Autowired
	private IFilmService filmService;
	
	/**
	 * 分页列表查询
	 *
	 * @param film
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "电影信息-分页列表查询")
	@ApiOperation(value="电影信息-分页列表查询", notes="电影信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Film>> queryPageList(Film film,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Film> queryWrapper = QueryGenerator.initQueryWrapper(film, req.getParameterMap());
		Page<Film> page = new Page<Film>(pageNo, pageSize);
		IPage<Film> pageList = filmService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param film
	 * @return
	 */
	@AutoLog(value = "电影信息-添加")
	@ApiOperation(value="电影信息-添加", notes="电影信息-添加")
	@RequiresPermissions("org.jeecg.modules.cinema:fly_cinema_film:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Film film) {
		filmService.save(film);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param film
	 * @return
	 */
	@AutoLog(value = "电影信息-编辑")
	@ApiOperation(value="电影信息-编辑", notes="电影信息-编辑")
	@RequiresPermissions("org.jeecg.modules.cinema:fly_cinema_film:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Film film) {
		filmService.updateById(film);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "电影信息-通过id删除")
	@ApiOperation(value="电影信息-通过id删除", notes="电影信息-通过id删除")
	@RequiresPermissions("org.jeecg.modules.cinema:fly_cinema_film:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		filmService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "电影信息-批量删除")
	@ApiOperation(value="电影信息-批量删除", notes="电影信息-批量删除")
	@RequiresPermissions("org.jeecg.modules.cinema:fly_cinema_film:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.filmService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "电影信息-通过id查询")
	@ApiOperation(value="电影信息-通过id查询", notes="电影信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Film> queryById(@RequestParam(name="id",required=true) String id) {
		Film film = filmService.getById(id);
		if(film==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(film);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param film
    */
    @RequiresPermissions("org.jeecg.modules.cinema:fly_cinema_film:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Film film) {
        return super.exportXls(request, film, Film.class, "电影信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("org.jeecg.modules.cinema:fly_cinema_film:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Film.class);
    }

}
