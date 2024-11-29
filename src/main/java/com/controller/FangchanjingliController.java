package com.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.annotation.IgnoreAuth;

import com.entity.FangchanjingliEntity;
import com.entity.view.FangchanjingliView;

import com.service.FangchanjingliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.Response;
import com.utils.MPUtil;

/**
 * 房产经理
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-05 14:22:24
 */
@RestController
@RequestMapping("/fangchanjingli")
public class FangchanjingliController {
    @Autowired
    private FangchanjingliService fangchanjingliService;




    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public Response login(String username, String password, String captcha, HttpServletRequest request) {
		FangchanjingliEntity u = fangchanjingliService.selectOne(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return Response.error("账号或密码不正确");
		}
		
		String token = tokenService.generateToken(u.getId(), username,"fangchanjingli",  "管理员" );
		return Response.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public Response register(@RequestBody FangchanjingliEntity fangchanjingli){
    	//ValidatorUtils.validateEntity(fangchanjingli);
    	FangchanjingliEntity u = fangchanjingliService.selectOne(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", fangchanjingli.getJinglizhanghao()));
		if(u!=null) {
			return Response.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		fangchanjingli.setId(uId);
        fangchanjingliService.insert(fangchanjingli);
        return Response.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public Response logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return Response.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public Response getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        FangchanjingliEntity u = fangchanjingliService.selectById(id);
        return Response.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public Response resetPass(String username, HttpServletRequest request){
    	FangchanjingliEntity u = fangchanjingliService.selectOne(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", username));
    	if(u==null) {
    		return Response.error("账号不存在");
    	}
        u.setMima("123456");
        fangchanjingliService.updateById(u);
        return Response.ok("密码已重置为：123456");
    }



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public Response page(@RequestParam Map<String, Object> params, FangchanjingliEntity fangchanjingli,
                         HttpServletRequest request){
        EntityWrapper<FangchanjingliEntity> ew = new EntityWrapper<FangchanjingliEntity>();

		PageUtils page = fangchanjingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangchanjingli), params), params));

        return Response.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public Response list(@RequestParam Map<String, Object> params, FangchanjingliEntity fangchanjingli,
                         HttpServletRequest request){
        EntityWrapper<FangchanjingliEntity> ew = new EntityWrapper<FangchanjingliEntity>();

		PageUtils page = fangchanjingliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fangchanjingli), params), params));
        return Response.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public Response list(FangchanjingliEntity fangchanjingli){
       	EntityWrapper<FangchanjingliEntity> ew = new EntityWrapper<FangchanjingliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fangchanjingli, "fangchanjingli")); 
        return Response.ok().put("data", fangchanjingliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public Response query(FangchanjingliEntity fangchanjingli){
        EntityWrapper< FangchanjingliEntity> ew = new EntityWrapper< FangchanjingliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fangchanjingli, "fangchanjingli")); 
		FangchanjingliView fangchanjingliView =  fangchanjingliService.selectView(ew);
		return Response.ok("查询房产经理成功").put("data", fangchanjingliView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public Response info(@PathVariable("id") Long id){
        FangchanjingliEntity fangchanjingli = fangchanjingliService.selectById(id);
        return Response.ok().put("data", fangchanjingli);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable("id") Long id){
        FangchanjingliEntity fangchanjingli = fangchanjingliService.selectById(id);
        return Response.ok().put("data", fangchanjingli);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public Response save(@RequestBody FangchanjingliEntity fangchanjingli, HttpServletRequest request){
        if(fangchanjingliService.selectCount(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", fangchanjingli.getJinglizhanghao()))>0) {
            return Response.error("经理账号已存在");
        }
    	fangchanjingli.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fangchanjingli);
    	FangchanjingliEntity u = fangchanjingliService.selectOne(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", fangchanjingli.getJinglizhanghao()));
		if(u!=null) {
			return Response.error("用户已存在");
		}
		fangchanjingli.setId(new Date().getTime());
        fangchanjingliService.insert(fangchanjingli);
        return Response.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public Response add(@RequestBody FangchanjingliEntity fangchanjingli, HttpServletRequest request){
        if(fangchanjingliService.selectCount(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", fangchanjingli.getJinglizhanghao()))>0) {
            return Response.error("经理账号已存在");
        }
    	fangchanjingli.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(fangchanjingli);
    	FangchanjingliEntity u = fangchanjingliService.selectOne(new EntityWrapper<FangchanjingliEntity>().eq("jinglizhanghao", fangchanjingli.getJinglizhanghao()));
		if(u!=null) {
			return Response.error("用户已存在");
		}
		fangchanjingli.setId(new Date().getTime());
        fangchanjingliService.insert(fangchanjingli);
        return Response.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public Response update(@RequestBody FangchanjingliEntity fangchanjingli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fangchanjingli);
        if(fangchanjingliService.selectCount(new EntityWrapper<FangchanjingliEntity>().ne("id", fangchanjingli.getId()).eq("jinglizhanghao", fangchanjingli.getJinglizhanghao()))>0) {
            return Response.error("经理账号已存在");
        }
        fangchanjingliService.updateById(fangchanjingli);//全部更新
        return Response.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Response delete(@RequestBody Long[] ids){
        fangchanjingliService.deleteBatchIds(Arrays.asList(ids));
        return Response.ok();
    }
    
	










}
