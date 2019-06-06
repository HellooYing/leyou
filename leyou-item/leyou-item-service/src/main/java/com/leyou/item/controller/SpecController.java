package com.leyou.item.controller;


import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecController {
    @Autowired
    SpecService specService;
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> groups=specService.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(groups);
    }

    @PutMapping("group")
    public ResponseEntity<Void> updateGroup(SpecGroup specGroup){
        specService.updateGroup(specGroup);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("group")
    public ResponseEntity<Void> saveGroup(SpecGroup group){
        specService.saveGroup(group);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("gid") Long gid){
        specService.deleteGroup(gid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
