package com.zkq.mygallery.dao;

import com.zkq.mygallery.entity.Painting;
import com.zkq.mygallery.utils.PageModel;
import com.zkq.mygallery.utils.XmlDataSource;

import java.util.ArrayList;
import java.util.List;

public class PaintingDao {
    //获取分页模型,分页查询油画对象
    public PageModel pagination(int page,int rows){
        //获取油画对象集合
        List<Painting> data = XmlDataSource.getRawData();
        //获取分页模型
        PageModel pageModel = new PageModel(data,page,rows);
        return pageModel;
    }

    public PageModel pagination(int category,int page,int rows){
        List<Painting> data = XmlDataSource.getRawData();
        List<Painting> categoryList = new ArrayList<>();
        for(Painting p:data){
            if(p.getCategory()==category){
                categoryList.add(p);
            }
        }
        PageModel pageModel = new PageModel(categoryList, page, rows);
        return pageModel;
    }
    //数据新增
    public void create(Painting painting){
        XmlDataSource.append(painting);
    }

    public Painting findById(Integer id){
        List<Painting> paintingList = XmlDataSource.getRawData();
        Painting painting = null;
        for(Painting p:paintingList){
            if(p.getId().equals(id)){
                painting=p;
            }
        }
        return painting;
    }
    public void update(Painting painting) {
        XmlDataSource.update(painting);
    }

    public void delete(Integer id) {
        XmlDataSource.delete(id);
    }
}
