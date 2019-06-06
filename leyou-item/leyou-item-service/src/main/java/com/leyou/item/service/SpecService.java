package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecService {
    @Autowired
    private SpecGroupMapper specGroupMapper;

    public List<SpecGroup> queryGroupsByCid(Long cid){
        SpecGroup specGroup=new SpecGroup();
        specGroup.setCid(cid);
        return specGroupMapper.select(specGroup);
    }

    @Transactional
    public void updateGroup(SpecGroup specGroup){
        specGroupMapper.updateByPrimaryKey(specGroup);
    }

    @Transactional
    public void saveGroup(SpecGroup specGroup) {
        specGroupMapper.insertSelective(specGroup);
    }

    @Transactional
    public void deleteGroup(Long gid) {
        specGroupMapper.deleteByPrimaryKey(gid);
    }
}
