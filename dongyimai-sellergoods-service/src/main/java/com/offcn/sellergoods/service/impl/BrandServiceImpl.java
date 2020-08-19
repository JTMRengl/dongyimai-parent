package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    public List<TbBrand> findAll() {

        return  brandMapper.selectByExample( null );
    }
    /**
     * 分页
     * pageNum 当前页号(确定查询的起始位置)  (pageNum-1)*PageSize
     * PageSize 每页的大小
     */
    public PageResult findPage(int pageNum, int pageSize) {

        PageHelper.startPage( pageNum, pageSize );
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample( null );

        return new PageResult( page.getTotal(), page.getResult() );
    }

    public void add(TbBrand brand) {
        brandMapper.insert( brand );
    }

    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey( id );
    }

    public void update(TbBrand brand) {
         brandMapper.updateByPrimaryKey( brand );
    }

    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey( id );
        }
    }
    /**
     * 分页条件查询
     * pageNum 当前页号(确定查询的起始位置)  (pageNum-1)*PageSize
     * PageSize 每页的大小
     */
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {

        //1.分页
        PageHelper.startPage( pageNum, pageSize ); // limit 1,10

        //2.条件
        TbBrandExample example = new TbBrandExample();// select * from tb_brand
        TbBrandExample.Criteria criteria = example.createCriteria();// where

        if(brand != null){
            //2.1 名称
            if(brand.getName() != null && brand.getName().length() > 0){
                criteria.andNameLike( "%"+brand.getName()+"%" );// name like '%+三+%'
            }
            //2.1 首字母
            if(brand.getFirstChar() != null && brand.getFirstChar().length() > 0){
                criteria.andFirstCharEqualTo( brand.getFirstChar() );//  and first_char = 'S'
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample( example );//拼接的完整sql
        return new PageResult( page.getTotal(), page.getResult() );
    }
}
