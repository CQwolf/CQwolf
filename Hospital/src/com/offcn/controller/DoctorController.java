package com.offcn.controller;

import com.offcn.dao.DoctorDao;
import com.offcn.entity.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    //����DoctorDao
    @Resource
    private DoctorDao doctorDao;

    //��ѯҽ������Ϣ
    @RequestMapping("find")
    public String find(@RequestParam(name="name",defaultValue = "") String name,@RequestParam(name="department",defaultValue = "0") int department, HttpSession session){
        name = name.trim();
        //��ѯҽ����Ϣ
        List<Doctor> doctorList = doctorDao.find(name,department);
        //��session���������
        session.setAttribute("doctorList",doctorList);
        session.removeAttribute("doctorName");
        session.removeAttribute("doctorDepartment");
        if(name != "" && name != null){
            session.removeAttribute("doctorName");
            session.setAttribute("doctorName",name);
        }
        if(department > 0){
            session.removeAttribute("doctorDepartment");
            session.setAttribute("doctorDepartment",department);
        }
        return "redirect:index.jsp";
    }

    //���ҽ������Ϣ
    @RequestMapping("add")
    public String add(Doctor doctor){
        //�����Ϣ
        doctorDao.add(doctor);
        return "redirect:find";
    }

    //�༭ҽ����Ϣ
    @RequestMapping("toEdit")
    public String toEdit(int did,HttpSession session){
        Doctor doctor = doctorDao.findById(did);
        session.setAttribute("doctor",doctor);
        return "redirect:edit.jsp";
    }

    //�༭ҽ����Ϣ���ύ�����޸�
    @RequestMapping("edit")
    public String edit(Doctor doctor){
        //�޸���Ϣ
        doctorDao.edit(doctor);
        return "redirect:find";
    }

    //����ɾ��ҽ����Ϣ
    @RequestMapping("delBatch")
    public String delBatch(@RequestParam("ids") List<Integer> ids){
        int i = doctorDao.delBatch(ids);
        System.out.println(i);
        return "redirect:find";
    }

    //ҽ������ҳ��Ĳ�ѯ
    @RequestMapping("toLook")
    public String toLook(int did,HttpSession session){
        Doctor doctor = doctorDao.findById(did);
        session.setAttribute("doctor",doctor);
        return "redirect:look.jsp";
    }
}
