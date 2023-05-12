// 
// 
// 

package com.controller;

import java.util.List;
import com.util.PageBean;
import java.sql.Timestamp;
import java.io.PrintWriter;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.entity.Sysuser;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import com.server.SysuserServier;
import org.springframework.stereotype.Controller;

@Controller
public class SysuserController
{
    @Resource
    private SysuserServier userService;
    
    @RequestMapping({ "flogin.do" })
    public String login() {
        return "login";
    }
    
    @RequestMapping({ "admin/showUserInfo.do" })
    public String showUserInfo(final ModelMap map, final HttpSession session) {
        if (session.getAttribute("auser") == null) {
            return "login";
        }
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        map.put("user", this.userService.getById(u.getUid()));
        return "admin/update_user_persion";
    }
    
    @RequestMapping({ "admin/updatePersionUser.do" })
    public String updateUserInfo(final ModelMap map, final HttpSession session, final Sysuser user) {
        this.userService.update(user);
        map.put("user", this.userService.getById(user.getUid()));
        session.setAttribute("suc", (Object)"cc");
        return "redirect:showUserInfo.do";
    }
    
    @RequestMapping({ "admin/login.do" })
    public String aLogin() {
        return "admin/login";
    }
    
    @RequestMapping({ "showInfo.do" })
    public String showInfo(final HttpSession session, final ModelMap map) {
        final Sysuser u = (Sysuser)session.getAttribute("user");
        if (u == null) {
            return "fore_login";
        }
        map.put("user", this.userService.getById(u.getUid()));
        return "showUserinfo";
    }
    
    @RequestMapping({ "addShowInfo.do" })
    public String addShowInfo(final HttpSession session, final ModelMap map, final Sysuser user) {
        this.userService.update(user);
        return "success";
    }
    
    public String fileUpload(@RequestParam(value = "file", required = false) final MultipartFile file, final HttpServletRequest request, String img) {
        final String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("path===" + path);
        System.out.println("file===" + file);
        final String fileName = file.getOriginalFilename();
        System.out.println("fileName===" + fileName);
        final File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        final String pa = String.valueOf(request.getContextPath()) + "/upload/" + fileName;
        System.out.println("path===" + pa);
        if (fileName != null && !fileName.equals("")) {
            img = fileName;
        }
        else {
            img = null;
        }
        return img;
    }
    
    @RequestMapping({ "admin/alogin.do" })
    public String checkLogin(final ModelMap map, Sysuser user, final HttpSession session) {
        final Map<String, Object> u = new HashMap<String, Object>();
        u.put("uname", user.getUname());
        u.put("utype", user.getUtype());
        u.put("pwd", user.getPwd());
        user = this.userService.adminLogin(u);
        if (user != null) {
            session.setAttribute("auser", (Object)user);
            System.out.println("auser=" + user);
            return "admin/index";
        }
        map.put("errorInfo", "\u7528\u6237\u540d\u6216\u8005\u5bc6\u7801\u9519\u8bef\uff01");
        return "admin/login";
    }
    
    @RequestMapping({ "admin/loginout.do" })
    public String adminLoginOut(final HttpSession session) {
        session.removeAttribute("auser");
        return "redirect:login.do";
    }
    
    @RequestMapping({ "loginout.do" })
    public String loginOut(final HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("p");
        return "fore_login";
    }
    
    @RequestMapping({ "admin/checkUname.do" })
    public void checkUname(final Sysuser user, final HttpServletResponse response) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("uname", user.getUname());
        System.out.println("uname===" + user.getUname());
        System.out.println("uname222===" + this.userService.checkUname(map));
        final JSONObject obj = new JSONObject();
        if (this.userService.checkUname(map) != null) {
            obj.put((Object)"info", (Object)"ok");
        }
        else {
            obj.put((Object)"info", (Object)"\u7528\u6237\u540d\u53ef\u4ee5\u7528\uff01");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(obj);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            out.close();
        }
        out.close();
    }
    
    @RequestMapping({ "checkmibao.do" })
    public void checkMB(final Sysuser user, final HttpServletResponse response, final HttpSession session) {
        final JSONObject obj = new JSONObject();
        final Sysuser u = this.userService.getById(user.getUid());
        final String q = u.getQuestion();
        if (u == null || u.equals("")) {
            obj.put((Object)"info", (Object)"ng");
        }
        else if (q.equals(user.getQuestion())) {
            obj.put((Object)"info", (Object)u.getPwd());
        }
        else {
            obj.put((Object)"info", (Object)"ng");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(obj);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            out.close();
        }
        out.close();
    }
    
    @RequestMapping({ "checkUname.do" })
    public void checkReg(final Sysuser user, final HttpServletResponse response) {
        final Map map = new HashMap();
        map.put("uname", user.getUname());
        System.out.println("uname===" + user.getUname());
        System.out.println("uname222===" + this.userService.checkUname(map));
        final JSONObject obj = new JSONObject();
        if (this.userService.checkUname(map) != null) {
            System.out.println("uname233333333333===");
            obj.put((Object)"info", (Object)"ng");
        }
        else {
            System.out.println("uname255555555555555===");
            obj.put((Object)"info", (Object)"\u7528\u6237\u540d\u53ef\u4ee5\u7528\uff01");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(obj);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            out.close();
        }
        out.close();
    }
    
    @RequestMapping({ "checkPass.do" })
    public void checkPass(final Sysuser user, final HttpServletResponse response) {
        final Map map = new HashMap();
        map.put("uname", user.getUname());
        System.out.println("uname===" + user.getUname());
        System.out.println("uname222===" + this.userService.checkUname(map));
        final JSONObject obj = new JSONObject();
        if (this.userService.checkUname(map) != null) {
            obj.put((Object)"info", (Object)this.userService.checkUname(map).getPwd());
        }
        else {
            obj.put((Object)"info", (Object)"ng");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(obj);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            out.close();
        }
        out.close();
    }
    
    @RequestMapping({ "reg.do" })
    public String addReg(final Sysuser user, final HttpSession session) {
        user.setIsdel("1");
        user.setUtype("\u4f1a\u5458");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        user.setPubtime(time.toString());
        this.userService.add(user);
        return "fore_login";
    }
    
    @RequestMapping({ "admin/addUser.do" })
    public String addUser(final Sysuser user, final HttpSession session) {
        user.setIsdel("1");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        user.setPubtime(time.toString());
        this.userService.add(user);
        return "redirect:userList.do";
    }
    
    @RequestMapping({ "admin/addUser2.do" })
    public String addUser2(final Sysuser user, final HttpSession session) {
        user.setIsdel("1");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        user.setPubtime(time.toString());
        this.userService.add(user);
        return "admin/login";
    }
    
    @RequestMapping({ "admin/doUpdateUser.do" })
    public String doUpdateUser(final ModelMap map, final int id) {
        System.out.println("id==" + id);
        map.put("user", this.userService.getById(id));
        return "admin/update_user";
    }
    
    @RequestMapping({ "admin/updateUser.do" })
    public String updateUser(final Sysuser user) {
        this.userService.update(user);
        return "redirect:userList.do";
    }
    
    @RequestMapping({ "admin/userList.do" })
    public String userList(@RequestParam(value = "page", required = false) String page, final HttpSession session, final ModelMap map) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map cmap = new HashMap();
        final int total = this.userService.getCount(cmap);
        pageBean.setTotal(total);
        final List<Sysuser> list = this.userService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        session.setAttribute("p", 1);
        return "admin/user_list";
    }
    
    @RequestMapping({ "admin/userListQuery.do" })
    public String useListQuery(@RequestParam(value = "page", required = false) String page, final HttpSession session, final ModelMap map, final Sysuser user) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        pmap.put("uname", user.getUname());
        final Map cmap = new HashMap();
        final int total = this.userService.getCount(pmap);
        pageBean.setTotal(total);
        final List<Sysuser> list = this.userService.getByPage(pmap);
        map.put("page", (Object)pageBean);
        map.put("list", (Object)list);
        session.setAttribute("p", 2);
        return "admin/user_list";
    }
    
    @RequestMapping({ "admin/deleteUser.do" })
    public String deleteUser(final int id) {
        this.userService.delete(id);
        return "redirect:userList.do";
    }
}
