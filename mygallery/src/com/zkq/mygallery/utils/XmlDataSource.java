package com.zkq.mygallery.utils;

import com.zkq.mygallery.entity.Painting;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/*
* 数据源类,将XML文件解析为Java对象
* */
public class XmlDataSource {
    //通过static静态关键字保证数据全局唯一
    private static List<Painting> data = new ArrayList<>();
    private static String dataFile;//painting.xml的完整物理路径
    static {
        //得到painting.xml的完整物理路径
        dataFile = XmlDataSource.class.getResource("/painting.xml").getPath();
        reload();
    }
    //提供外部获取数据源的方法
    public static List<Painting> getRawData(){
        return data;
    }
    //对XML文件 重载
    public static void reload(){
        try {
            dataFile = new URLDecoder().decode(dataFile, "UTF-8");
            SAXReader reader = new SAXReader();
            //1.获取Document文档对象
            Document document = reader.read(dataFile);
            //Xpath得到XML节点集合
            List<Node> nodes = document.selectNodes("/root/painting");
            data.clear();
            for(Node node:nodes){
                Element element = (Element) node;
                String id = element.attributeValue("id");
                String pname = element.elementText("pname");
                //System.out.println(id+":"+pname);
                Painting painting = new Painting();
                painting.setId(Integer.parseInt(id));
                painting.setPname(pname);
                painting.setCategory(Integer.parseInt(element.elementText("category")));
                painting.setPrice(Integer.parseInt(element.elementText("price")));
                painting.setPreview(element.elementText("preview"));
                painting.setDescription(element.elementText("description"));
                //System.out.println(painting.toString());
                data.add(painting);
            }
        } catch (UnsupportedEncodingException | DocumentException e) {
            e.printStackTrace();
        }
    }
    //对xml进行数据追加
    public static void append(Painting painting) {
        //1.读取XML文档得到Document对象
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile);
            Element rootElement = document.getRootElement();//获取根节点
            //2.创建新的Painting节点
            Element p = rootElement.addElement("painting");
            //3.创建Painting节点的各个子节点
            p.addAttribute("id",String.valueOf(data.size()+1));
            p.addElement("pname").setText(painting.getPname());
            p.addElement("preview").setText(painting.getPreview());
            p.addElement("description").setText(painting.getDescription());
            p.addElement("category").setText(String.valueOf(painting.getCategory()));
            p.addElement("price").setText(String.valueOf(painting.getPrice()));
            //4.写入XML,完成追加
            writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
            document.write(writer);
        } catch (DocumentException | FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reload();
        }

    }
    //对xml进行数据更新
    public static void update(Painting painting){
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile);
            List<Node> nodes = document.selectNodes("root/painting[@id=" + painting.getId() + "]");
            if(nodes.size()==0){
                throw new RuntimeException("id="+painting.getId()+"编号油画不存在");
            }
            Element p = (Element) nodes.get(0);
            p.selectSingleNode("pname").setText(painting.getPname());
            p.selectSingleNode("preview").setText(painting.getPreview());
            p.selectSingleNode("description").setText(painting.getDescription());
            p.selectSingleNode("category").setText(painting.getCategory().toString());
            p.selectSingleNode("price").setText(painting.getPrice().toString());
            writer = new OutputStreamWriter(new FileOutputStream(dataFile));
            document.write(writer);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            reload();
        }
    }
    /**
     * 按id号删除XML油画数据
     * @param id 油画id
     * @throws IOException
     */

    public static void delete(Integer id) {
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile);
            List<Node> nodes = document.selectNodes("/root/painting[@id=" + id + "]");
            if(nodes.size() == 0) {
                throw new RuntimeException("id=" + id + "编号油画不存在");
            }
            Element p = (Element)nodes.get(0);
            p.getParent().remove(p);
            writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
            document.write(writer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(writer!=null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            reload();
        }
    }

    public static void main(String[] args) {
        //new XmlDataSource();
        List<Painting> rawData = XmlDataSource.getRawData();
        System.out.println(rawData);
    }
}
