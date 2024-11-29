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


import com.dao.FangchanjingliDao;
import com.entity.FangchanjingliEntity;
import com.service.FangchanjingliService;
import com.entity.vo.FangchanjingliVO;
import com.entity.view.FangchanjingliView;

@Service("fangchanjingliService")
public class FangchanjingliServiceImpl extends ServiceImpl<FangchanjingliDao, FangchanjingliEntity> implements FangchanjingliService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FangchanjingliEntity> page = this.selectPage(
                new Query<FangchanjingliEntity>(params).getPage(),
                new EntityWrapper<FangchanjingliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<FangchanjingliEntity> wrapper) {
		  Page<FangchanjingliView> page =new Query<FangchanjingliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<FangchanjingliVO> selectListVO(Wrapper<FangchanjingliEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public FangchanjingliVO selectVO(Wrapper<FangchanjingliEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<FangchanjingliView> selectListView(Wrapper<FangchanjingliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FangchanjingliView selectView(Wrapper<FangchanjingliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
