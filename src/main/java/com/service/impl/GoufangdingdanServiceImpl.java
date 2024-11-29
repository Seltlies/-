package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.GoufangdingdanDao;
import com.entity.GoufangdingdanEntity;
import com.service.GoufangdingdanService;
import com.entity.vo.GoufangdingdanVO;
import com.entity.view.GoufangdingdanView;

@Service("goufangdingdanService")
public class GoufangdingdanServiceImpl extends ServiceImpl<GoufangdingdanDao, GoufangdingdanEntity> implements GoufangdingdanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoufangdingdanEntity> page = this.selectPage(
                new Query<GoufangdingdanEntity>(params).getPage(),
                new EntityWrapper<GoufangdingdanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GoufangdingdanEntity> wrapper) {
		  Page<GoufangdingdanView> page =new Query<GoufangdingdanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<GoufangdingdanVO> selectListVO(Wrapper<GoufangdingdanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GoufangdingdanVO selectVO(Wrapper<GoufangdingdanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GoufangdingdanView> selectListView(Wrapper<GoufangdingdanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GoufangdingdanView selectView(Wrapper<GoufangdingdanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<GoufangdingdanEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<GoufangdingdanEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<GoufangdingdanEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
