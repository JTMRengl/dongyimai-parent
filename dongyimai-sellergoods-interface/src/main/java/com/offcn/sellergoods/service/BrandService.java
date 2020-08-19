package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;


public interface BrandService {

    public List<TbBrand> findAll();

    /**
     * 分页
     * pageNum 当前页号(确定查询的起始位置)  (pageNum-1)*PageSize
     * PageSize 每页的大小
     */
    public PageResult findPage(int pageNum, int pageSize);

    /**
     * 增
     * @param brand
     */
    public void add(TbBrand brand);


    /**
     * 根据id查询一个
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 修改
     * @param brand
     */
    public void update(TbBrand brand);

    /**
     * 删除
     * 根据id的数组
     */
    public void delete(Long[] ids);

    /**
     * 分页条件查询
     * pageNum 当前页号(确定查询的起始位置)  (pageNum-1)*PageSize
     * PageSize 每页的大小
     */
    public PageResult findPage(TbBrand brand,int pageNum, int pageSize);
}
