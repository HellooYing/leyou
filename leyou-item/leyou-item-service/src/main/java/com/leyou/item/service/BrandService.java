package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandsByPage(String key,Integer page,Integer rows,String sortBy,Boolean desc){
        Example example=new Example(Brand.class);
        Example.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc?"desc":"asc"));
        }
        List<Brand> brands=this.brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo=new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insertSelective(brand);
        cids.forEach(cid -> {
            brandMapper.insertBrandAndCategory(cid, brand.getId());
        });
    }
    @Transactional
    public void updateBrand(Brand brand,List<Long> cids) {
        brandMapper.updateByPrimaryKeySelective(brand);
        List<Long> cids2=brandMapper.selectCategoryByBid(brand.getId());
        if(!equal(cids,cids2)){
            brandMapper.deleteCategoryBrandByBid(brand.getId());
            cids.forEach(cid -> {
                brandMapper.insertBrandAndCategory(cid, brand.getId());
            });
        }
    }
    @Transactional
    public void deleteBrand(Long bid) {
        brandMapper.deleteByPrimaryKey(bid);
        brandMapper.deleteCategoryBrandByBid(bid);
    }

    private boolean equal(List l1,List l2){
        if(l1.size()!=l2.size()) return false;
        for (int i = 0; i < l1.size(); i++) {
            if(l1.get(i)!=l2.get(i)) return false;
        }
        return true;
    }

    public List<Brand> queryBrandsByCid(Long cid) {

        return this.brandMapper.selectBrandByCid(cid);
    }
}
