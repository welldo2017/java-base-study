package com.welldo.design_pattern.create.builder;

import java.util.Date;

/**
 *  GenericBuilder 模式改良
 *
 * 思路：
 * 1）在 Bean 类里面新建一个静态内部类：XxxBuilder；
 * 2）把 Bean 类的所有参数,复制到 XxxBuilder，然后在 XxxBuilder 新建必须参数的构造器，其他参数使用变量名作为方法然后返回自身（this）以便形成链式调用；
 * 3）在 Bean 类里面新建一个接收 XxxBuilder 参数的私有构造器，避免使用 new 创建对象；
 * 4）在 XxxBuilder 类新建一个 build 方法开始构建 Bean 类，也是作为链式调用的结束；
 *
 * author:welldo
 * date: 2021/4/26 15:41
 */
public class Task3 {
    private long id;//必须参数
    private String name;//必须参数
    private String content;
    private int type;
    private int status;
    private Date finishDate;


    //1）在 Bean 类里面新建一个静态内部类：XxxBuilder；
    public static class TaskBuilder {

        //2）把 Bean 类的所有参数,复制到 XxxBuilder，
        private long id;//必须参数
        private String name;//必须参数
        private String content;
        private int type;
        private int status;
        private Date finishDate;

        //2) 然后在 XxxBuilder中 新建必须参数的构造器，
        public TaskBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        //2) 非必须参数: 使用参数名(变量名)作为方法名,然后返回自身（this）以便形成链式调用；
        public TaskBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TaskBuilder type(int type) {
            this.type = type;
            return this;
        }

        public TaskBuilder status(int status) {
            this.status = status;
            return this;
        }

        public TaskBuilder finishDate(Date finishDate) {
            this.finishDate = finishDate;
            return this;
        }

        //4）在 XxxBuilder 类新建一个 build 方法开始构建 Bean 类，也是作为链式调用的结束；
        public Task3 build(){
            return new Task3(this);
        }

    }

    //3）在 Bean 类里面新建一个接收 XxxBuilder 参数的私有构造器，(私有的原因:避免使用 new 创建对象)
    private Task3(TaskBuilder taskBuilder) {
        this.id = taskBuilder.id;
        this.name = taskBuilder.name;
        this.content = taskBuilder.content;
        this.type = taskBuilder.type;
        this.status = taskBuilder.status;
        this.finishDate = taskBuilder.finishDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", finishDate=" + finishDate +
                '}';
    }
}
