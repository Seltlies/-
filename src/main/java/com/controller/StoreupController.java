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

import com.entity.StoreupEntity;
import com.entity.view.StoreupView;

import com.service.StoreupService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 收藏表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:25
 */
@RestController
@RequestMapping("/storeup")
public class StoreupController {
    @Autowired
    private StoreupService storeupService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, StoreupEntity storeup,
                         HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            storeup.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();

		PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, StoreupEntity storeup,
                         HttpServletRequest request){
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();

		PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(StoreupEntity storeup){
       	EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
      	ew.allEq(MPUtil.allEQMapPre( storeup, "storeup")); 
        return Response.ok().put("data", storeupService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(StoreupEntity storeup){
        EntityWrapper< StoreupEntity> ew = new EntityWrapper< StoreupEntity>();
 		ew.allEq(MPUtil.allEQMapPre( storeup, "storeup")); 
		StoreupView storeupView =  storeupService.selectView(ew);
		return Response.ok("查询收藏表成功").put("data", storeupView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        StoreupEntity storeup = storeupService.selectById(id);
        return Response.ok().put("data", storeup);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        StoreupEntity storeup = storeupService.selectById(id);
        return Response.ok().put("data", storeup);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody StoreupEntity storeup, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(storeup);
    	storeup.setUserid((Long)request.getSession().getAttribute("userId"));
        storeupService.insert(storeup);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody StoreupEntity storeup, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(storeup);
        storeupService.insert(storeup);
        return Response.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public Response security(@RequestParam String username){
        StoreupEntity storeup = storeupService.selectOne(new EntityWrapper<StoreupEntity>().eq("", username));
        return Response.ok().put("data", storeup);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public Response update(@RequestBody StoreupEntity storeup, HttpServletRequest request){
        //ValidatorUtils.validateEntity(storeup);
        storeupService.updateById(storeup);//全部更新
        return Response.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        storeupService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public Response autoSort(@RequestParam Map<String, Object> params, StoreupEntity storeup, HttpServletRequest request, String pre){
        EntityWrapper<StoreupEntity> ew = new EntityWrapper<StoreupEntity>();
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
		PageUtils page = storeupService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeup), params), params));
        return Response.ok().put("data", page);
    }










}
