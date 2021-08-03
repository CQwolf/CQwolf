package com.offcn.dao;

import com.offcn.entity.Medicine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface MedicineDao {

    //    ��ѯȫ��ҩƷ�ķ���
    public List<Medicine> find(@Param("name") String name, @Param("type") int type);

    //    ���ҩƷ�ķ���
    @Insert("insert into medicine value(#{mid},#{inPrice},#{salPrice},#{name},#{type},#{descs},#{qualityDate},#{description},#{produceFirm},#{readme},#{remark})")
    public int add(Medicine medicine);

    //    ����id��ѯ��Ϣ
    @Select("select * from medicine where mid = #{mid}")
    public Medicine findById(String mid);

    //    �޸�ҩƷ��Ϣ�ķ���
    @Update("update medicine set inPrice=#{inPrice},salPrice=#{salPrice},name=#{name}," +
            "type=#{type},descs=#{descs},qualityDate=#{qualityDate},description=#{description},produceFirm=#{produceFirm}," +
            "readme=#{readme},remark=#{remark} where mid=#{mid}")
    public int edit(Medicine medicine);

    //    ����ɾ������
    public int delBatch(List<String> ids);
}
