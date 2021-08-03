package com.offcn.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//����springmvc  ��Ҫ���ַ���ת��ΪDate
public class DateCovert implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        //���ַ���ת��ΪDate����
        //name = zhangsan
        //hiredate = 2020-01-02
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}