package com.zsc.test;

import jdk.internal.dynalink.beans.StaticClass;
import org.apache.commons.collections4.Predicate;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author:ShaochaoZhao
 * @Description:
 * @Date:Create in 13:39 2018/11/2
 * @Modified By:
 **/
public class LamdbaTest extends BaseTest {
    //predicate
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList <T>();
        for(T t : list){
            if(p.evaluate(t)){
                result.add(t);
            }
        }
        return result;
    }
    //Consumer
    public static <T> void consumerSimple(List<T> list, Consumer<T> c){

    }


    //function
    public static <T,R> List<R> map(List<T> list, Function<T,R> f){
        List<R> result = new  ArrayList();
        for(T t : list){
            result.add(f.apply(t));
        }
        return result;
    }
}
