package com.we.epub;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.TOCReference;

import java.util.List;

public class EpubMenuParser {

    private ContentItem menu = new ContentItem();

    public ContentItem startParse(Book book){
        //从深度为0开始遍历
        parseMenu(book.getTableOfContents().getTocReferences(), 0);
        return menu;

    }

    /**
     * 遍历epub书籍的目录
     * @param refs 
     * @param level 菜单深度
     */
    private void parseMenu(List<TOCReference> refs, int level) {

        if (refs == null || refs.isEmpty()) {
            return;
        }

        for (TOCReference ref : refs) {

            if (ref.getResource() != null) {
                if (level == 0) {
                    // 第一层，一级节点，父节点是root
                    ContentItem item = new ContentItem(ref.getTitle(),
                             ref.getCompleteHref(),(int)ref.getResource().getSize());
                    menu.getChildren().add(item);

                } else if (level == 1) {

                    int lastIndexOf_depth1 = menu.getChildren().size() - 1;// 当前最后一个一级节点的位置

                    // 存入root的孩子节点中的最后一个一级节点中
                    ContentItem item2 = new ContentItem(ref.getTitle(),
                            ref.getCompleteHref(),(int)ref.getResource().getSize());

                    menu.getChildren().get(lastIndexOf_depth1).getChildren()
                            .add(item2);

                } else if (level == 2) {

                    int lastIndexOf_depth1 = menu.getChildren().size() - 1;// 当前最后一个一级节点的位置
                    int lastIndexOf_depth2 = menu.getChildren()
                            .get(lastIndexOf_depth1).getChildren().size() - 1;// 当前最后一个二级节点的位置

                    // 父节点为二级节点中的最后一个节点
                    ContentItem item = new ContentItem(ref.getTitle(),
                        ref.getCompleteHref(),(int)ref.getResource().getSize());

                    menu.getChildren().get(lastIndexOf_depth1).getChildren()
                            .get(lastIndexOf_depth2).getChildren().add(item);
                }
            }
            //继续遍历它的儿子
            parseMenu(ref.getChildren(), level + 1);
        }
    }

}
