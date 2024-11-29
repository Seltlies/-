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


import com.dao.KanfangxinxiDao;
import com.entity.KanfangxinxiEntity;
import com.service.KanfangxinxiService;
import com.entity.vo.KanfangxinxiVO;
import com.entity.view.KanfangxinxiView;

@Service("kanfangxinxiService")
public class KanfangxinxiServiceImpl extends ServiceImpl<KanfangxinxiDao, KanfangxinxiEntity> implements KanfangxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KanfangxinxiEntity> page = this.selectPage(
                new Query<KanfangxinxiEntity>(params).getPage(),
                new EntityWrapper<KanfangxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KanfangxinxiEntity> wrapper) {
		  Page<KanfangxinxiView> page =new Query<KanfangxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<KanfangxinxiVO> selectListVO(Wrapper<KanfangxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KanfangxinxiVO selectVO(Wrapper<KanfangxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KanfangxinxiView> selectListView(Wrapper<KanfangxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KanfangxinxiView selectView(Wrapper<KanfangxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
