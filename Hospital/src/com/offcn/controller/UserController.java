package com.offcn.controller;

import com.offcn.dao.UserDao;
import com.offcn.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserDao userdao;
    //�����û��ĵ�¼����
    @RequestMapping("login")
    public void userLogin(String username, String password, String verify,
                          HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        //�õ����ɵ���֤��
        String verCode = (String) session.getAttribute("verCode");
        if(verCode.equalsIgnoreCase(verify)){
            //�������ݿ⣬�鿴�Ƿ��¼�ɹ�
            User user = userdao.checkUser(username,password);
            if(user != null){
                session.setAttribute("user",user);
                response.getWriter().print("<script>alert('��ϲ����¼�ɹ�');location.href='../index.jsp'</script>");
            } else {
                response.getWriter().print("<script>alert('�û������������');location.href='../login.jsp'</script>");
            }
        }else {
            response.getWriter().print("<script>alert('��֤�����');location.href='../login.jsp'</script>");
        }
    }

    //�û�ע������
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:../login.jsp";
    }

    //�û�ע�����
    @RequestMapping("register")
    public void register(User user,HttpServletResponse response) throws IOException {
        //�����Ӧ�����
        response.setContentType("text/html;charset=utf-8");
        //��ѯ�û����Ƿ�ʹ��
        User userCh = userdao.findByUserName(user.getUsername());
        if(userCh == null){
            //����״̬
            user.setStatus(1);
            //����ע��ʱ��
            user.setModifytime(new Date());
            //�鿴��������
            int row = userdao.addUser(user);
            if(row > 0) {
                response.getWriter().print("<script>alert('ע��ɹ�');location.href='../login.jsp'</script>");
            }else {
                response.getWriter().print("<script>alert('ע��ʧ��');location.href='../regist.jsp'</script>");
            }
        } else {
            response.getWriter().print("<script>alert('ע��ʧ��,�û����ѱ�ע��');location.href='../regist.jsp'</script>");
        }
    }
}
