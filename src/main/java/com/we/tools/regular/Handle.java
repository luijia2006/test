package com.we.tools.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handle {
    public static void main(String[] args) {
        String xmlString="123<audio src=\"258be589-4d7b-4053-9cfb-bee38db8f0f9-050.mp3\"  " +
                "\"name=\"2018083115103527879-2.mp3\" aliasname=\"2018083115103527879-2.mp3\" controls=\"\" " +
                "\"type=\"music\" path=\"tfedu/2018/82693715406618624\"></audio>123<audio></audio>";
        System.out.println(interceptImgPath(xmlString));
    }
    /**
     *
     * 提取出图片路径
     *
     * @param xmlStr 内容
     * @return 图片集合
     */
    public static String interceptImgPath(String xmlStr) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "(<|&lt;)(img|audio|video|a)[^>|&gt;].*?type\\s*=\\s*(\"|'|&quot;|&apos;)(uploadImg|music|video|attachment)(\"|'|&quot;|&apos;).*?(/){0,1}(>|&gt;)";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(xmlStr);
        StringBuffer sb = new StringBuffer();
        while (m_image.find()) {
            m_image.appendReplacement(sb, "[图片]");
        }
        return sb.toString();
    }

}
