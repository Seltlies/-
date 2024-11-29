package com.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;

import com.entity.NewstypeEntity;
import com.entity.view.NewstypeView;

import com.service.NewstypeService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 房产资讯分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
@RestController
@RequestMapping("/newstype")
public class NewstypeController {
    @Autowired
    private NewstypeService newstypeService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, NewstypeEntity newstype,
                         HttpServletRequest request){
        EntityWrapper<NewstypeEntity> ew = new EntityWrapper<NewstypeEntity>();

		PageUtils page = newstypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, newstype), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, NewstypeEntity newstype,
                         HttpServletRequest request){
        EntityWrapper<NewstypeEntity> ew = new EntityWrapper<NewstypeEntity>();

		PageUtils page = newstypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, newstype), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(NewstypeEntity newstype){
       	EntityWrapper<NewstypeEntity> ew = new EntityWrapper<NewstypeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( newstype, "newstype")); 
        return Response.ok().put("data", newstypeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(NewstypeEntity newstype){
        EntityWrapper< NewstypeEntity> ew = new EntityWrapper< NewstypeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( newstype, "newstype")); 
		NewstypeView newstypeView =  newstypeService.selectView(ew);
		return Response.ok("查询房产资讯分类成功").put("data", newstypeView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        NewstypeEntity newstype = newstypeService.selectById(id);
        return Response.ok().put("data", newstype);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        NewstypeEntity newstype = newstypeService.selectById(id);
        return Response.ok().put("data", newstype);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody NewstypeEntity newstype, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(newstype);
        newstypeService.insert(newstype);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody NewstypeEntity newstype, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(newstype);
        newstypeService.insert(newstype);
        return Response.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public Response security(@RequestParam String username){
        NewstypeEntity newstype = newstypeService.selectOne(new EntityWrapper<NewstypeEntity>().eq("", username));
        return Response.ok().put("data", newstype);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public Response update(@RequestBody NewstypeEntity newstype, HttpServletRequest request){
        //ValidatorUtils.validateEntity(newstype);
        newstypeService.updateById(newstype);//全部更新
        return Response.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        newstypeService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public Response autoSort(@RequestParam Map<String, Object> params, NewstypeEntity newstype, HttpServletRequest request, String pre){
        EntityWrapper<NewstypeEntity> ew = new EntityWrapper<NewstypeEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = newstypeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, newstype), params), params));
        return Response.ok().put("data", page);
    }










}
