package com.offcn.dao;

import com.offcn.entity.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorDao {

    //��ѯȫ��ҽ���ķ���
    public List<Doctor> find(@Param("name") String name,@Param("department") int department);

    //���ҽ���ķ���
    @Insert("insert into doctor value(#{did},#{name},#{cardno},#{phone},#{sex},#{age},#{birthday},#{email},#{department},#{education},#{remark})")
    public int add(Doctor doctor);

    //����id��ѯ��Ϣ
    @Select("select * from doctor where did = #{did}")
    public Doctor findById(int did);

    //�޸�ҽ����Ϣ�ķ���
    @Update("update doctor set name=#{name},cardno=#{cardno},phone=#{phone}," +
            "sex=#{sex},age=#{age},birthday=#{birthday},email=#{email},department=#{department}," +
            "education=#{education},remark=#{remark} where did=#{did}")
    public int edit(Doctor doctor);

    //����ɾ������
    public int delBatch(List<Integer> ids);
}
