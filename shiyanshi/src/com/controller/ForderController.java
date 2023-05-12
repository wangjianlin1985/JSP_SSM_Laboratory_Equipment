// 
// 
// 

package com.controller;

import com.entity.TongJi;
import com.dto.SbbfDto;
import com.entity.Sbbf;
import com.entity.Syssb;
import org.springframework.beans.BeanUtils;
import com.entity.Sbbx;
import com.dto.SbbxDto;
import java.util.ArrayList;
import java.util.Iterator;
import com.entity.ShiYan;
import java.sql.Timestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Forder;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.util.PageBean;
import com.entity.Sysuser;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import java.io.IOException;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.dao.SbbfMapper;
import com.dao.SbbxMapper;
import com.server.SyssbServer;
import com.server.ShiYanServer;
import com.server.SysuserServier;
import javax.annotation.Resource;
import com.server.ForderServer;
import org.springframework.stereotype.Controller;

@Controller
public class ForderController
{
    @Resource
    private ForderServer orderService;
    @Resource
    private SysuserServier userService;
    @Resource
    private ShiYanServer shiYanService;
    @Resource
    private SyssbServer syssbServer;
    @Resource
    private SbbxMapper sbbxMapper;
    @Resource
    private SbbfMapper sbbfMapper;
    
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
    
    @RequestMapping({ "admin/shiYanShiForderList.do" })
    public String shiYanShiList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        final Map<String, Object> cmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        if (u.getUtype().equals("\u5b9e\u9a8c\u5ba4\u7ba1\u7406\u5458")) {
            pmap.put("uid", null);
            cmap.put("uid", null);
        }
        else {
            pmap.put("uid", u.getUid());
            cmap.put("uid", u.getUid());
        }
        pmap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        cmap.put("ftype", "\u5b9e\u9a8c\u5ba4");
        final int total = this.orderService.getCount(cmap);
        pageBean.setTotal(total);
        final List<Forder> list = this.orderService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        map.put("ulist", this.userService.getAll(null));
        map.put("slist", this.shiYanService.getAll(null));
        session.setAttribute("p", 1);
        return "admin/list_order_shiYanShi";
    }
    
    @RequestMapping({ "admin/guiHuan.do" })
    public String guiHuan(final HttpServletRequest request, final Forder yp, final ModelMap map, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final Forder syso = this.orderService.getById(id);
        syso.setEtime(time.toString().substring(0, 19));
        syso.setIsdel("0");
        syso.setKid(id);
        this.orderService.update(syso);
        final ShiYan sy = this.shiYanService.getById(syso.getFid());
        sy.setMstatus("\u7a7a\u95f2\u4e2d");
        sy.setId(syso.getFid());
        this.shiYanService.update(sy);
        return "success";
    }
    
    @RequestMapping({ "admin/tongGuoSYS.do" })
    public String tongGuoSYS(final HttpServletRequest request, final Forder yp, final ModelMap map, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        final Forder syso = this.orderService.getById(id);
        System.out.println(syso);
        syso.setStatus("\u5ba1\u6838\u901a\u8fc7");
        syso.setKid(id);
        this.orderService.update(syso);
        final ShiYan sy = this.shiYanService.getById(syso.getFid());
        sy.setMstatus("\u5df2\u9884\u8ba2");
        sy.setId(syso.getFid());
        this.shiYanService.update(sy);
        map.put("ftype", "\u5b9e\u9a8c\u5ba4");
        map.put("fid", syso.getFid());
        final List<Forder> list = this.orderService.getByPage((Map<String, Object>)map);
        for (final Forder o : list) {
            if (!o.getKid().equals(id)) {
                final Forder fo = this.orderService.getById(o.getKid());
                fo.setStatus("\u5ba1\u6838\u5931\u8d25");
                fo.setKid(id);
                this.orderService.update(fo);
            }
        }
        return "success";
    }
    
    @RequestMapping({ "admin/buTongGuoSYS.do" })
    public String buTongGuoSYS(final HttpServletRequest request, final Forder yp, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        final Forder f = this.orderService.getById(id);
        f.setStatus("\u5ba1\u6838\u5931\u8d25");
        f.setKid(id);
        this.orderService.update(f);
        return "success";
    }
    
    @RequestMapping({ "admin/shenQin.do" })
    public String addForder(final HttpServletRequest request, final Forder yp, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        yp.setUid(u.getUid());
        yp.setStatus("\u5f85\u5ba1\u6838");
        yp.setIsdel("1");
        yp.setFid(id);
        yp.setFtype("\u5b9e\u9a8c\u5ba4");
        yp.setPubtime(time.toString().substring(0, 19));
        yp.setStime(time.toString().substring(0, 19));
        this.orderService.add(yp);
        return "success";
    }
    
    @RequestMapping({ "admin/sheBeiForderList.do" })
    public String sheBeiForderList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        final Map<String, Object> cmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        if (u.getUtype().equals("\u5b9e\u9a8c\u8bbe\u5907\u7ba1\u7406\u5458")) {
            pmap.put("uid", null);
            cmap.put("uid", null);
        }
        else {
            pmap.put("uid", u.getUid());
            cmap.put("uid", u.getUid());
        }
        pmap.put("ftype", "\u8bbe\u5907");
        cmap.put("ftype", "\u8bbe\u5907");
        final int total = this.orderService.getCount(cmap);
        pageBean.setTotal(total);
        final List<Forder> list = this.orderService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        map.put("ulist", this.userService.getAll(null));
        map.put("slist", this.shiYanService.getAll(null));
        session.setAttribute("p", 1);
        return "admin/list_order_sheBei";
    }
    
    @RequestMapping({ "admin/sbbxList.do" })
    public String sbbxList(final ModelMap map, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final List<Sbbx> sbbxList = this.sbbxMapper.getAll();
        final ArrayList<SbbxDto> sbbxDtolist = new ArrayList<SbbxDto>();
        for (final Sbbx s : sbbxList) {
            final SbbxDto sbbxDto = new SbbxDto();
            BeanUtils.copyProperties((Object)s, (Object)sbbxDto);
            sbbxDto.setSbname(this.shiYanService.getById(s.getSbid()).getName());
            sbbxDto.setSysname(this.shiYanService.getById(s.getSysid()).getName());
            sbbxDto.setUname(this.userService.getById(s.getUid()).getUname());
            if (s.getCluid() != null) {
                sbbxDto.setCluname(this.userService.getById(s.getCluid()).getUname());
            }
            sbbxDtolist.add(sbbxDto);
        }
        map.put("sbbxdtolist", sbbxDtolist);
        return "admin/list_sbbx";
    }
    
    @RequestMapping({ "admin/sbwcwx.do" })
    public String sbwcwx(final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final Sbbx sbbx = this.sbbxMapper.selectByPrimaryKey(id);
        sbbx.setBstatus("\u5b8c\u6210\u7ef4\u4fee");
        sbbx.setCluid(u.getUid());
        sbbx.setCltime(time.toString().substring(0, 19));
        this.sbbxMapper.updateByPrimaryKeySelective(sbbx);
        final HashMap<String, Object> syssb = new HashMap<String, Object>();
        syssb.put("sysid", sbbx.getSysid());
        syssb.put("sbid", sbbx.getSbid());
        final List<Syssb> syssbs = this.syssbServer.getByPage(syssb);
        Syssb syssb2 = null;
        if (syssbs.size() > 0) {
            syssb2 = syssbs.get(0);
        }
        syssb2.setSnum(syssb2.getSnum() + sbbx.getBxnum());
        this.syssbServer.update(syssb2);
        return "success";
    }
    
    @RequestMapping({ "admin/baofei.do" })
    public String baofei(final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final Sbbx sbbx = this.sbbxMapper.selectByPrimaryKey(id);
        sbbx.setBstatus("\u76f4\u63a5\u62a5\u5e9f");
        sbbx.setCluid(u.getUid());
        sbbx.setCltime(time.toString().substring(0, 19));
        this.sbbxMapper.updateByPrimaryKeySelective(sbbx);
        final Sbbf sbbf = new Sbbf();
        sbbf.setStatus("\u76f4\u63a5\u62a5\u5e9f");
        sbbf.setBftime(time.toString().substring(0, 19));
        sbbf.setUid(u.getUid());
        sbbf.setBfsnum(sbbx.getBxnum());
        sbbf.setBfyy(sbbx.getBxyy());
        sbbf.setSysid(sbbx.getSysid());
        sbbf.setSbid(sbbx.getSbid());
        sbbf.setCltime(time.toString().substring(0, 19));
        sbbf.setCluid(u.getUid());
        this.sbbfMapper.insertSelective(sbbf);
        return "success";
    }
    
    @RequestMapping({ "admin/sbbfList.do" })
    public String sbbfList(final ModelMap map, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        final List<Sbbf> sbbfList = this.sbbfMapper.getAll();
        final ArrayList<SbbfDto> sbbfDtolist = new ArrayList<SbbfDto>();
        for (final Sbbf s : sbbfList) {
            final SbbfDto sbbfDto = new SbbfDto();
            BeanUtils.copyProperties((Object)s, (Object)sbbfDto);
            sbbfDto.setSbname(this.shiYanService.getById(s.getSbid()).getName());
            sbbfDto.setSysname(this.shiYanService.getById(s.getSysid()).getName());
            sbbfDto.setUname(this.userService.getById(s.getUid()).getUname());
            if (s.getCluid() != null) {
                sbbfDto.setCluname(this.userService.getById(s.getCluid()).getUname());
            }
            sbbfDtolist.add(sbbfDto);
        }
        map.put("sbbfdtolist", (Object)sbbfDtolist);
        return "admin/list_sbbf";
    }
    
    @RequestMapping({ "admin/qrbaofei.do" })
    public String qrbaofei(final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final Sbbf sbbf = this.sbbfMapper.selectByPrimaryKey(id);
        sbbf.setCluid(u.getUid());
        sbbf.setCltime(time.toString().substring(0, 19));
        sbbf.setStatus("\u786e\u8ba4\u62a5\u5e9f");
        this.sbbfMapper.updateByPrimaryKeySelective(sbbf);
        return "success";
    }
    
    @RequestMapping({ "admin/addForderSheBei.do" })
    public String addForderSheBei(final HttpServletRequest request, final Forder yp, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final ShiYan syy = this.shiYanService.getById(yp.getFid());
        if (syy.getSnum() < yp.getSnum()) {
            return "admin/error";
        }
        syy.setId(yp.getFid());
        this.shiYanService.update(syy);
        final String sid = request.getParameter("sid");
        System.out.println(sid);
        yp.setUid(u.getUid());
        yp.setSid(Integer.parseInt(sid));
        yp.setStatus("\u5f85\u5ba1\u6838");
        yp.setIsdel("1");
        yp.setFtype("\u8bbe\u5907");
        yp.setPubtime(time.toString().substring(0, 19));
        yp.setStime(time.toString().substring(0, 19));
        this.orderService.add(yp);
        return "success";
    }
    
    @RequestMapping({ "admin/buTongGuoShenBei.do" })
    public String buTongGuoShenBei(final HttpServletRequest request, final Forder yp, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        final Forder f = this.orderService.getById(id);
        f.setStatus("\u5ba1\u6838\u5931\u8d25");
        f.setKid(id);
        this.orderService.update(f);
        return "success";
    }
    
    @RequestMapping({ "admin/tongGuoShenBei.do" })
    public String tongGuoShenBei(final HttpServletRequest request, final Forder yp, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final Forder f = this.orderService.getById(id);
        final ShiYan syy = this.shiYanService.getById(f.getFid());
        syy.setSnum(syy.getSnum() - f.getSnum());
        this.shiYanService.update(syy);
        f.setStatus("\u5ba1\u6838\u6210\u529f");
        f.setKid(id);
        this.orderService.update(f);
        final Syssb syssb = new Syssb();
        syssb.setSid(f.getSid());
        syssb.setSbid(f.getFid());
        syssb.setTime(time.toString().substring(0, 19));
        final Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("sid", f.getSid());
        hashMap.put("sbid", f.getFid());
        final List<Syssb> syssbList = this.syssbServer.getAll(hashMap);
        if (syssbList.size() > 0) {
            syssb.setSnum(syssbList.get(0).getSnum() + f.getSnum());
            syssb.setId(syssbList.get(0).getId());
            this.syssbServer.update(syssb);
        }
        else {
            syssb.setSnum(f.getSnum());
            this.syssbServer.add(syssb);
        }
        return "success";
    }
    
    @RequestMapping({ "admin/haoCaiForderList.do" })
    public String haoCaiForderList(@RequestParam(value = "page", required = false) String page, final ModelMap map, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        if (page == null || page.equals("")) {
            page = "1";
        }
        final PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
        final Map<String, Object> pmap = new HashMap<String, Object>();
        final Map<String, Object> cmap = new HashMap<String, Object>();
        pmap.put("pageno", pageBean.getStart());
        pmap.put("pageSize", pageBean.getPageSize());
        if (u.getUtype().equals("\u5b9e\u9a8c\u8bbe\u5907\u7ba1\u7406\u5458")) {
            pmap.put("uid", null);
            cmap.put("uid", null);
        }
        else {
            pmap.put("uid", u.getUid());
            cmap.put("uid", u.getUid());
        }
        pmap.put("ftype", "\u8017\u6750");
        cmap.put("ftype", "\u8017\u6750");
        final int total = this.orderService.getCount(cmap);
        pageBean.setTotal(total);
        final List<Forder> list = this.orderService.getByPage(pmap);
        map.put("page", pageBean);
        map.put("list", list);
        map.put("ulist", this.userService.getAll(null));
        map.put("slist", this.shiYanService.getAll(null));
        session.setAttribute("p", 1);
        return "admin/list_order_haoCai";
    }
    
    @RequestMapping({ "admin/addForderHaoCai.do" })
    public String addForderHaoCai(final HttpServletRequest request, final Forder yp, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final ShiYan syy = this.shiYanService.getById(yp.getFid());
        if (syy.getSnum() < yp.getSnum()) {
            return "admin/error";
        }
        syy.setId(yp.getFid());
        this.shiYanService.update(syy);
        yp.setUid(u.getUid());
        yp.setStatus("\u5f85\u5ba1\u6838");
        yp.setIsdel("1");
        yp.setFtype("\u8017\u6750");
        yp.setPubtime(time.toString().substring(0, 19));
        this.orderService.add(yp);
        return "success";
    }
    
    @RequestMapping({ "admin/buTongGuoHaoCai.do" })
    public String buTongGuoHaoCai(final HttpServletRequest request, final Forder yp, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        final Forder f = this.orderService.getById(id);
        f.setStatus("\u5ba1\u6838\u5931\u8d25");
        f.setKid(id);
        this.orderService.update(f);
        return "success";
    }
    
    @RequestMapping({ "admin/tongGuoHaoCai.do" })
    public String tongGuoHaoCai(final HttpServletRequest request, final Forder yp, final HttpSession session, final int id) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        if (u == null) {
            return "admin/login";
        }
        final Forder f = this.orderService.getById(id);
        final ShiYan syy = this.shiYanService.getById(f.getFid());
        syy.setSnum(syy.getSnum() - f.getSnum());
        this.shiYanService.update(syy);
        f.setStatus("\u5ba1\u6838\u6210\u529f");
        f.setKid(id);
        this.orderService.update(f);
        return "success";
    }
    
    @RequestMapping({ "admin/forderTongJiList.do" })
    public String forderTongJiList(final ModelMap map, final HttpSession session, final Forder order, final String etime1, final String stime1) {
        System.out.println("stime1===" + stime1);
        System.out.println("etime1===" + etime1);
        System.out.println("etime===" + order.getStime());
        final Map<String, Object> pmap = new HashMap<String, Object>();
        if (order.getStime() != null && !order.getStime().equals("")) {
            pmap.put("etime", order.getStime());
        }
        pmap.put("stime1", stime1);
        pmap.put("etime1", etime1);
        final List<TongJi> list = this.orderService.getTongJi(pmap);
        System.out.println("list====" + list);
        for (final TongJi tj : list) {
            System.out.println("name===" + tj.getName() + "amount===" + tj.getAmount());
        }
        map.put("list", list);
        map.put("etime1", etime1);
        map.put("stime1", stime1);
        session.setAttribute("p", (Object)1);
        return "admin/list_order_tongji";
    }
    
    @RequestMapping({ "admin/deleteForder.do" })
    public String deleteForder(final int id) {
        this.orderService.delete(id);
        return "success";
    }
}
