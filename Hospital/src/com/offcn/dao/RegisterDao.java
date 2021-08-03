package com.offcn.dao;

import com.offcn.entity.Medicine;
import com.offcn.entity.Register;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RegisterDao {

    //    ��ѯȫ��ҩƷ�ķ���
    public List<Register> find(@Param("rid") String rid,@Param("name") String name, @Param("department") int department);

    //    ���ҩƷ�ķ���
    @Insert("insert into register value(#{rid},#{name},#{idCard},#{siNumber},#{registerMoney}" +
            ",#{phone},#{isPay},#{sex},#{age},#{consultation},#{department},#{did},#{status},#{registerDate},#{remark})")
    public int add(Register register);

    //    ����id��ѯ��Ϣ
    @Select("select * from register where rid = #{rid}")
    public Register findById(String rid);

    //    �޸�ҩƷ��Ϣ�ķ���
    @Update("update register set name=#{name},idCard=#{idCard},siNumber=#{siNumber}," +
            "registerMoney=#{registerMoney},phone=#{phone},isPay=#{isPay},sex=#{sex},age=#{age}," +
            "consultation=#{consultation},department=#{department},did=#{did}," +
            "status=#{status},remark=#{remark} where rid=#{rid}")
    public int edit(Register register);

    //    ����ɾ������
    public int delBatch(List<String> ids);
}
