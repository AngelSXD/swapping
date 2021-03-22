package com.sxd.swapping.objoutputstream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  ObjectOutputStream 写对象到序列化文件中
 *  ObjectInputStream  读对象到反序列化文件中
 */
public class SerializeTest {


    /**
     * 写生成序列化文件
     *
     * 对同一个 共享对象 做重复性的多次写入 建议使用writeObject + reset方法 组合使用 做重置动作 （即深克隆效果）下面这个 方法写入的 是 test1 test2  test3 三条。
     * 对于 非共享对象， 做多次重复性的写入  可以使用 writeUnshared 方法（即浅克隆效果）
     *
     *
     *
     * 而 单独使用 writeObject方法，会导致 第二次写入的对象 依旧是 第一次写对的对象。即 流不对被重置，导致下面这个 方法写入的 是错误的 test1 test2  test1  test2 四条。
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        List<Student> students = new ArrayList<>();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("C:/Users/ouiyuio/Desktop/students.ser")));
        for (int i = 1; i <= 3; i++) {
            Student student = new Student(i, "test" + i);
            students.add(student);

            if (students.size() >= 2) {
                // objectOutputStream.writeUnshared(students);
                objectOutputStream.writeObject(students);
                objectOutputStream.reset();  //使用 reset保证写入重复对象时进行了重置操作
                students.clear();
            }
        }
        if (students.size() > 0) {
            objectOutputStream.writeObject(students);
            objectOutputStream.flush();
        }
        objectOutputStream.writeObject(null);
        objectOutputStream.close();
    }


    /**
     * 反序列化读取文件内容
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("C:/Users/ouiyuio/Desktop/students.ser")));
        List<Student> students = (List<Student>) objectInputStream.readObject();
        int count = 0;
        while (students != null) {
            count += students.size();
            for (Student student : students) {
                System.out.println(student);
            }
            students = (List<Student>) objectInputStream.readObject();
        }
        objectInputStream.close();
        System.out.println(count);
    }

    @Test
    public void test5() throws Exception {
        int count = 0;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("C:/Users/ouiyuio/Desktop/students.ser")));
        List<Student> students1 = (List<Student>) objectInputStream.readObject();
        count += students1.size();
        for (Student student : students1) {
            System.out.println(student);
        }
        List<Student> students2 = (List<Student>) objectInputStream.readObject();
        count += students2.size();
        for (Student student : students2) {
            System.out.println(student);
        }
        objectInputStream.close();
        System.out.println(count);
    }


}
