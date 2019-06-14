package com.we.epub;


import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.CreatorContributor;
import nl.siegmann.epublib.epub.Epub2Writer;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.epub.EpubWriter;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class ResolveEpub {


    public static void main(String[] args) throws IOException {
        String bookPath = "E:\\epub\\中国绘画史.epub";
        ResolveEpub zip = new ResolveEpub();
        zip.resolve(bookPath);
        //unZipFiles(bookPath, "E:\\epub\\");
    }

    //下面是在java中解析epub电子书 可以将epub电子书中的书名、封面、目录、内容（html格式）读取出来
    public void resolve(String bookPath){
        try {
            EpubReader epubReader = new EpubReader();
            EpubWriter epubWriter = new Epub2Writer();
            Book book = epubReader.readEpub(new FileInputStream(bookPath));
            //书名
            String title = book.getTitle();
            System.out.println("======================="+title);
            //作者
            List<CreatorContributor> authorList = book.getMetadata().getAuthors();
            for (int i = 0; i < authorList.size(); i++) {
                CreatorContributor author = authorList.get(i);
                System.out.println("======================="+author);
            }

            //循环解析菜单
            EpubMenuParser parser = new EpubMenuParser();
            ContentItem contentItem = parser.startParse(book);
            if (contentItem!=null) {
                System.out.println(contentItem.getTitle()+"=="+contentItem.getUrl());
                getMenuChildren(contentItem);
            }

            /*//封面
            Resource resource = book.getCoverImage();
            if (resource!=null) {
                byte[] p = resource.getData();
                //将图片输出
                String newFilename = "e:\\epub\\coverImage.jpg";
                FileImageOutputStream imgout = new FileImageOutputStream(new File(newFilename));
                imgout.write(p, 0, p.length);
                imgout.close();
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //递归调用
    private void getMenuChildren(ContentItem contentItem) {
        List<ContentItem> children = contentItem.getChildren();
        if (children!=null) {
            for (ContentItem item : children) {
                System.out.println(item.getTitle()+"=="+item.getUrl());
                if (item.getChildren()!=null) {
                    getMenuChildren(item);
                }
            }
        }
    }


    /**
     * 解压到指定目录
     * @param zipPath
     * @param descDir
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     * @param zipFile	待解压的zip文件
     * @param descDir 	指定目录
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {

        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));//解决中文文件夹乱码
        String name =zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));

        File pathFile = new File(descDir+name);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + name +"/"+ zipEntryName).replaceAll("\\*", "/");

            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
//			System.out.println(outPath);

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
        return;
    }

}
