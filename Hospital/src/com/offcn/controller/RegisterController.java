package com.offcn.controller;

import com.offcn.dao.DoctorDao;
import com.offcn.dao.RegisterDao;
import com.offcn.entity.Doctor;
import com.offcn.entity.Medicine;
import com.offcn.entity.Register;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("register")
public class RegisterController {

    //����RegisterDao
    @Resource
    private RegisterDao registerDao;
    @Resource
    private DoctorDao doctorDao;

    //��ѯ�Һŵ���Ϣ
    @RequestMapping("find")
    public String find(@RequestParam(name="rid",defaultValue = "") String rid,@RequestParam(name="name",defaultValue = "") String name, @RequestParam(name="department",defaultValue = "0") int department, HttpSession session){
        rid = rid.trim();
        name = name.trim();
        //��ѯ�Һ���Ϣ
        List<Register> registerList = registerDao.find(rid,name,department);
        //��ѯҽ����Ϣ
        List<Doctor> doctorList = doctorDao.find("",0);
        //��session���������
        session.setAttribute("registerList",registerList);
        session.setAttribute("doctorList",doctorList);
        if(rid != "" && rid != null){
            session.removeAttribute("registerRid");
            session.setAttribute("registerRid",rid);
        }
        if(name != "" && name != null){
            session.removeAttribute("registerName");
            session.setAttribute("registerName",name);
        }
        if(department > 0){
            session.removeAttribute("registerDepartment");
            session.setAttribute("registerDepartment",department);
        }
        return "redirect:index.jsp";
    }

    //��ӹҺŵ���Ϣ
    @RequestMapping("add")
    public void add(Register register, HttpServletResponse response) throws IOException {
        //�����Ӧ�����
        response.setContentType("text/html;charset=utf-8");
        //��ѯҩƷ����Ƿ�ʹ��
        Register registerRid = registerDao.findById(register.getRid());
        if(registerRid == null){
            //�鿴��������
            //�����Ϣ
            //���ùҺ�ʱ��
            register.setRegisterDate(new Date());
            registerDao.add(register);
            response.getWriter().print("<script>location.href='find'</script>");
        } else {
            response.getWriter().print("<script>alert('�Һ�ʧ��,�˲������ѱ����');location.href='add.jsp'</script>");
        }
    }


    //    ����Rid���
    @RequestMapping("checkRid")
    public void checkUser(String rid, HttpServletResponse response) throws IOException {
//        �ж��Ƿ�����û���������
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Register registerRid = registerDao.findById(rid);
        if(registerRid != null){
//            ������ܾ��Ұ����ݷ��͵�POST��data����
            writer.print("�������Ѵ���");
        }
        else {//            �����������
            writer.print("�����ſ���");
        }
    }


    //�༭�Һ���Ϣ
    @RequestMapping("toEdit")
    public String toEdit(String rid,HttpSession session){
        Register register = registerDao.findById(rid);
        session.setAttribute("register",register);
        return "redirect:edit.jsp";
    }

    //�༭ҩƷ��Ϣ���ύ�����޸�
    @RequestMapping("edit")
    public String edit(Register register){
        //�޸���Ϣ
        registerDao.edit(register);
        return "redirect:find";
    }

    //����ɾ��ҩƷ��Ϣ
    @RequestMapping("delBatch")
    public String delBatch(@RequestParam("ids") List<String> ids){
        int i = registerDao.delBatch(ids);
        System.out.println(i);
        return "redirect:find";
    }

    //ҩƷ����ҳ��Ĳ�ѯ
    @RequestMapping("toLook")
    public String toLook(String rid,HttpSession session){
        Register register = registerDao.findById(rid);
        session.setAttribute("register",register);
        return "redirect:look.jsp";
    }

}
