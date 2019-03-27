package com.mko.test.entity;

/**
 * @program: testdemo
 * @description:
 * @author: yuxz
 * @create: 2019-03-25 16:48
 **/
public class cutPic {
    //  ===源图片路径名称如:c://1.jpg
    private String srcPath;
    // ===剪切图片存放路径名称.如:c://2.jpg
    private String subpath;
    // ===剪切点x坐标
    private int x;

    private int y;
    // ===剪切点宽度
    private int width;

    private int height;

    public cutPic(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
    }

}
