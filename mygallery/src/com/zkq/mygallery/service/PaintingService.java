package com.zkq.mygallery.service;

import com.zkq.mygallery.dao.PaintingDao;
import com.zkq.mygallery.entity.Painting;
import com.zkq.mygallery.utils.PageModel;
import com.zkq.mygallery.utils.XmlDataSource;

import java.util.List;

public class PaintingService {
    private PaintingDao paintingDao = new PaintingDao();
    //通过可选参数让程序执行不同行为
    public PageModel pagination(int page,int rows,String...category){
        PageModel pageModel = null;
        if(rows==0){
            throw new RuntimeException("无效的rows参数");
        }
        if(category.length==0||category[0]==null){
            pageModel = paintingDao.pagination(page, rows);
        }else {
            pageModel = paintingDao.pagination(Integer.parseInt(category[0]),page, rows);
        }
        return pageModel;
    }

    public void create(Painting painting){
        paintingDao.create(painting);
    }

    public Painting findById(Integer id){
        Painting painting = paintingDao.findById(id);
        if(painting==null){
            throw new RuntimeException("[id"+id+"]油画不存在");
        }
        return painting;
    }
    /**
     * 更新业务逻辑
     * @param newPainting 新的油画数据
     * @param isPreviewModified 是否修改Preview属性
     */
    public void update(Painting newPainting,Integer isPreviewModified) {
        //createtime:
        //在原始数据基础上覆盖更新
        Painting oldPainting = this.findById(newPainting.getId());
        oldPainting.setPname(newPainting.getPname());
        oldPainting.setCategory(newPainting.getCategory());
        oldPainting.setPrice(newPainting.getPrice());
        oldPainting.setDescription(newPainting.getDescription());
        if(isPreviewModified == 1) {
            oldPainting.setPreview(newPainting.getPreview());
        }
        paintingDao.update(oldPainting);
    }


    /**
     * 按id号删除数据
     * @param id
     */
    public void delete(Integer id) {
        paintingDao.delete(id);
    }

    public static void main(String[] args) {
        PaintingService paintingService = new PaintingService();
        PageModel pageModel = paintingService.pagination(2, 6);
        List<Painting> paintingList = pageModel.getPageData();
        for(Painting painting : paintingList) {
            System.out.println(painting.getPname());
        }
        System.out.println(pageModel.getPageStartRow() + ":" + pageModel.getPageEndRow());
    }
}
