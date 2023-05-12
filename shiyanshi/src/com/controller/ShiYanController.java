// 
// 
// 

package com.controller;

import java.util.Iterator;
import com.entity.Forder;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.util.PageBean;
import com.entity.Sysuser;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.Timestamp;
import com.entity.ShiYan;
import org.springframework.ui.ModelMap;
import java.io.IOException;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.server.SysuserServier;
import com.server.ForderServer;
import javax.annotation.Resource;
import com.server.ShiYanServer;
import org.springframework.stereotype.Controller;

@Controller
public class ShiYanController
{
    @Resource
    private ShiYanServer ShiYanService;
    @Resource
    private ForderServer orderService;
    @Resource
    private SysuserServier userService;
    
    public String fileUpload(@RequestParam(value = "file", required = false) final MultipartFile file, final HttpServletRequest request, String img) {
        final String path = request.getSession().getServletContext().getRealPath("upload");
        final String fileName = file.getOriginalFilename();
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
            img = "zanwu.jpg";
        }
        return img;
    }
    
    @RequestMapping({ "admin/addShiYan.do" })
    public String addShiYan(final ModelMap map, final ShiYan shiYan, final HttpServletRequest request) {
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        shiYan.setFtype("\u5b9e\u9a8c\u5ba4");
        shiYan.setMstatus("\u7a7a\u95f2\u4e2d");
        shiYan.setIsdel("1");
        shiYan.setPubtime(time.toString().substring(0, 19));
        this.ShiYanService.add(shiYan);
        return "redirect:ShiYanList.do";
    }
    
    @RequestMapping({ "admin/doUpdateShiYan.do" })
    public String doUpdateShiYan(final ModelMap map, final int id) {
        map.put("sy", this.ShiYanService.getById(id));
        return "admin/update_ShiYan";
    }
    
    @RequestMapping({ "admin/updateShiYan.do" })
    public String updateShiYan(final HttpServletRequest request, final ShiYan ShiYan) {
        this.ShiYanService.update(ShiYan);
        return "redirect:ShiYanList.do";
    }
    
    @RequestMapping({ "admin/ShiYanList.do" })
    public String shiYanList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        session.removeAttribute("forders");
        if (u == null) {
            return "admin/login";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map<String, Object> cmap = new HashMap<String, Object>();
        pmap.put("name", null);
        cmap.put("name", null);
        cmap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        pmap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        final int total = this.ShiYanService.getCount(cmap);
        pageBean.setTotal(total);
        final List<ShiYan> list = this.ShiYanService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        session.setAttribute("p", (Object)1);
        if (u.getUtype().equals("\u8001\u5e08")) {
            final Map<String, Object> uidMap = new HashMap<String, Object>();
            uidMap.put("uid", u.getUid());
            uidMap.put("isdel", 1);
            final List<Forder> list2 = this.orderService.getAll(uidMap);
            System.out.println(list2);
            if (list2.size() != 0) {
                session.setAttribute("forders", (Object)list2);
            }
        }
        return "admin/list_ShiYan";
    }
    
    @RequestMapping({ "admin/vagueShiYanList.do" })
    public String vagueShiYanList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session, final ShiYan cd) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map<String, Object> cmap = new HashMap<String, Object>();
        if (cd.getName() != null && !cd.getName().equals("")) {
            cmap.put("name", cd.getName());
            pmap.put("name", cd.getName());
        }
        cmap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        pmap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        final int total = this.ShiYanService.getCount(cmap);
        pageBean.setTotal(total);
        final List<ShiYan> list = this.ShiYanService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        session.setAttribute("p", (Object)2);
        return "admin/list_ShiYan";
    }
    
    @RequestMapping({ "admin/deleteShiYan.do" })
    public String deleteShiYan(final int id) {
        this.ShiYanService.delete(id);
        return "redirect:ShiYanList.do";
    }
    
    @RequestMapping({ "admin/addSheBei.do" })
    public String addSheBei(final ModelMap map, final ShiYan shiYan, final HttpServletRequest request) {
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        shiYan.setFtype("\u8bbe\u5907");
        shiYan.setMstatus("\u5145\u8db3");
        shiYan.setIsdel("1");
        shiYan.setPubtime(time.toString().substring(0, 19));
        this.ShiYanService.add(shiYan);
        return "redirect:SheBeiList.do";
    }
    
    @RequestMapping({ "admin/doUpdateSheBei.do" })
    public String doUpdateSheBei(final ModelMap map, final int id) {
        map.put("sy", this.ShiYanService.getById(id));
        return "admin/update_SheBei";
    }
    
    @RequestMapping({ "admin/updateSheBei.do" })
    public String updateSheBei(final ShiYan shiYan) {
        this.ShiYanService.update(shiYan);
        return "success";
    }
    
    @RequestMapping({ "admin/doAddForderSheBei.do" })
    public String doAddForderSheBei(final ModelMap map, final int id) {
        final Map<String, Object> ftypeMap = new HashMap<String, Object>();
        ftypeMap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        ftypeMap.put("isdel", 1);
        final List<ShiYan> list = this.ShiYanService.getAll(ftypeMap);
        System.out.println(list);
        map.put("sy", this.ShiYanService.getById(id));
        map.put("ftypes", list);
        return "admin/add_order_sheBei";
    }
    
    @RequestMapping({ "admin/SheBeiList.do" })
    public String sheBeiList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map<String, Object> cmap = new HashMap<String, Object>();
        cmap.put("ftype", "\u8bbe\u5907");
        pmap.put("ftype", "\u8bbe\u5907");
        final int total = this.ShiYanService.getCount(cmap);
        pageBean.setTotal(total);
        final List<ShiYan> list = this.ShiYanService.getByPage(pmap);
        for (final ShiYan shebei : list) {
            if (shebei.getSnum() == 0) {
                shebei.setMstatus("\u6682\u65e0\u5e93\u5b58");
                this.ShiYanService.update(shebei);
            }
        }
        final List<ShiYan> list2 = this.ShiYanService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list2);
        session.setAttribute("p", 1);
        return "admin/list_SheBei";
    }
    
    @RequestMapping({ "admin/vagueSheBeiList.do" })
    public String vagueSheBeiList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session, final ShiYan cd) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map<String, Object> cmap = new HashMap<String, Object>();
        if (cd.getName() != null && !cd.getName().equals("")) {
            cmap.put("name", cd.getName());
            pmap.put("name", cd.getName());
        }
        cmap.put("ftype", "\u8bbe\u5907");
        pmap.put("ftype", "\u8bbe\u5907");
        final int total = this.ShiYanService.getCount(cmap);
        pageBean.setTotal(total);
        final List<ShiYan> list = this.ShiYanService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        session.setAttribute("p", 2);
        return "admin/list_SheBei";
    }
    
    @RequestMapping({ "admin/deleteSheBei.do" })
    public String deleteSheBei(final int id) {
        this.ShiYanService.delete(id);
        return "redirect:SheBeiList.do";
    }
    
    @RequestMapping({ "admin/addHaoCai.do" })
    public String addHaoCai(final ModelMap map, final ShiYan shiYan, final HttpServletRequest request) {
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        shiYan.setFtype("\u8017\u6750");
        shiYan.setMstatus("\u5145\u8db3");
        shiYan.setIsdel("1");
        shiYan.setPubtime(time.toString().substring(0, 19));
        this.ShiYanService.add(shiYan);
        return "redirect:HaoCaiList.do";
    }
    
    @RequestMapping({ "admin/doUpdateHaoCai.do" })
    public String doUpdateHaoCai(final ModelMap map, final int id) {
        map.put("sy", this.ShiYanService.getById(id));
        return "admin/update_HaoCai";
    }
    
    @RequestMapping({ "admin/updateHaoCai.do" })
    public String updateHaoCai(final HttpServletRequest request, final ShiYan ShiYan) {
        this.ShiYanService.update(ShiYan);
        return "redirect:HaoCaiList.do";
    }
    
    @RequestMapping({ "admin/HaoCaiList.do" })
    public String haoCaiList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map<String, Object> cmap = new HashMap<String, Object>();
        pmap.put("ftype", null);
        cmap.put("name", null);
        cmap.put("ftype", "\u8017\u6750");
        pmap.put("ftype", "\u8017\u6750");
        final int total = this.ShiYanService.getCount(cmap);
        pageBean.setTotal(total);
        final List<ShiYan> list = this.ShiYanService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        session.setAttribute("p", 1);
        return "admin/list_HaoCai";
    }
    
    @RequestMapping({ "admin/vagueHaoCaiList.do" })
    public String vagueHaoCaiList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session, final ShiYan cd) {
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        final Map<String, Object> cmap = new HashMap<String, Object>();
        if (cd.getName() != null && !cd.getName().equals("")) {
            cmap.put("name", cd.getName());
            pmap.put("name", cd.getName());
        }
        cmap.put("ftype", "\u8017\u6750");
        pmap.put("ftype", "\u8017\u6750");
        final int total = this.ShiYanService.getCount(cmap);
        pageBean.setTotal(total);
        final List<ShiYan> list = this.ShiYanService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        session.setAttribute("p", (Object)2);
        return "admin/list_HaoCai";
    }
    
    @RequestMapping({ "admin/deleteHaoCai.do" })
    public String deleteHaoCai(final int id) {
        this.ShiYanService.delete(id);
        return "redirect:HaoCaiList.do";
    }
}
