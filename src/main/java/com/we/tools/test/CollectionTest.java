package com.we.tools.test;

import com.we.core.common.util.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectionTest {
    public static void main(String[] args) {
        //是否是空方法测试
        check();
    }


    public static void check(){

        List<BookCategoryDto> list = CollectionUtil.list();
        list.add(new BookCategoryDto(1,"书籍1"));
        list.add(new BookCategoryDto(2,"书籍1"));

        List<BookCategoryDto> uniquelist = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(BookCategoryDto::getName))), ArrayList::new)
        );

        System.out.println(uniquelist);

    }
}

class BookCategoryDto{
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public BookCategoryDto() {

    }

    public BookCategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
