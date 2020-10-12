package com.sxd.swapping.func;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 适配器模式 实现方式
 */
public class FunctionTest {


    public void Test1(){

        Map<String, Object> invoke1 = new Invoker(new Invoker.RealInvoker() {
            @Override
            public Object doInvoke() {
                //do One
                return "One";
            }
        }).invoke();


    }


    public void Test2(){

        Map<String, Object> invoke2 = new Invoker(new Invoker.RealInvoker() {
            @Override
            public Object doInvoke() {
                //do Two
                return "Two";
            }
        }).invoke();

    }







    /**
     * 1.适配器对象
     */
    static class Invoker {

        /**
         * 2.适配者对象
         * 定义接口
         */
        public interface RealInvoker {
            /**
             * 多态实现该方法
             * @return
             */
            Object doInvoke();
        }


        /**
         * 适配者对象作为适配器的属性
         * 接口作为属性
         */
        private RealInvoker realInvoker;


        /**
         *  接口的子类实现，作为入参，赋值属性
         * @param o
         */
        Invoker(RealInvoker o) {
            realInvoker = o;
        }


        /**
         * 统一调用方法
         * 该方法中，除了 多态实现之外，可以做很多重复要做的相同的事情
         *
         * 例如：该方法中，就实现了 对多态实现方法的  耗时计算。
         * 不用在每一个调用处，进行单独的耗时计算。
         *
         * @return
         */
        public Map<String, Object> invoke() {

            Long start = System.currentTimeMillis();
            Object object = realInvoker.doInvoke(); //多态实现方法
            Long time = System.currentTimeMillis() - start;
            Map<String, Object> data = Maps.newHashMap();

            String json = JSON.toJSONString(object);
            data.put("time", time);
            data.put("data", json);
            return data;
        }


    }


}
