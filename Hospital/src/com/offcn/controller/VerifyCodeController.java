package com.offcn.controller;

import com.offcn.util.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class VerifyCodeController {

//    ��֤�������
    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //��������ִ���ͼƬ
        String verifyCode = VerifyCodeUtils.outputVerifyImage(100, 30, response.getOutputStream(),4);
        //����Ựsession
        HttpSession session = request.getSession(true);
        //ɾ����ǰ��
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
    }
}
