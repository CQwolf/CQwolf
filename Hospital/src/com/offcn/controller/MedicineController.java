package com.offcn.controller;

import com.offcn.dao.MedicineDao;
import com.offcn.entity.Medicine;
import com.offcn.entity.User;
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
@RequestMapping("medicine")
public class MedicineController {
    //����MedicineDao
    @Resource
    private MedicineDao medicineDao;

    //��ѯҩƷ����Ϣ
    @RequestMapping("find")
    public String find(@RequestParam(name="name",defaultValue = "") String name, @RequestParam(name="type",defaultValue = "0") int type, HttpSession session){
        name = name.trim();
        //��ѯҩƷ��Ϣ
        List<Medicine> medicineList = medicineDao.find(name,type);
        //��session���������
        session.setAttribute("medicineList",medicineList);
        if(name != "" && name != null){
            session.removeAttribute("medicineName");
            session.setAttribute("medicineName",name);
        }
        if(type > 0){
            session.removeAttribute("medicineType");
            session.setAttribute("medicineType",type);
        }
        return "redirect:index.jsp";
    }

    //���ҩƷ����Ϣ
    @RequestMapping("add")
    public void add(Medicine medicine, HttpServletResponse response) throws IOException {
        //�����Ӧ�����
        response.setContentType("text/html;charset=utf-8");
        //��ѯҩƷ����Ƿ�ʹ��
        Medicine medicineMid = medicineDao.findById(medicine.getMid());
        if(medicineMid == null){
            //�鿴��������
            //�����Ϣ
            medicineDao.add(medicine);
            response.getWriter().print("<script>location.href='find'</script>");
        } else {
            response.getWriter().print("<script>alert('ҩƷ���ʧ��,ҩƷ����ѱ�ע��');location.href='add.jsp'</script>");
        }
    }

    //    ����Mid���
    @RequestMapping("checkMid")
    public void checkUser(String mid, HttpServletResponse response) throws IOException {
//        �ж��Ƿ�����û���������
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Medicine medicineMid = medicineDao.findById(mid);
        if(medicineMid != null){
//            ������ܾ��Ұ����ݷ��͵�POST��data����
            writer.print("ҩƷ����Ѵ���");
        }
        else {
//            �����������
            writer.print("ҩƷ��ſ���");
        }
    }

    //�༭ҩƷ��Ϣ
    @RequestMapping("toEdit")
    public String toEdit(String mid,HttpSession session){
        Medicine medicine = medicineDao.findById(mid);
        session.setAttribute("medicine",medicine);
        return "redirect:edit.jsp";
    }

    //�༭ҩƷ��Ϣ���ύ�����޸�
    @RequestMapping("edit")
    public String edit(Medicine medicine){
        //�޸���Ϣ
        System.out.println(medicine.getName());
        medicineDao.edit(medicine);
        return "redirect:find";
    }

    //����ɾ��ҩƷ��Ϣ
    @RequestMapping("delBatch")
    public String delBatch(@RequestParam("ids") List<String> ids){
        int i = medicineDao.delBatch(ids);
        System.out.println(i);
        return "redirect:find";
    }

    //ҩƷ����ҳ��Ĳ�ѯ
    @RequestMapping("toLook")
    public String toLook(String mid,HttpSession session){
        Medicine medicine = medicineDao.findById(mid);
        session.setAttribute("medicine",medicine);
        return "redirect:look.jsp";
    }
}
