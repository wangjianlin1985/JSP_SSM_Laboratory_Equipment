// 
// 
// 

package com.controller;

import com.entity.Sbbf;
import java.sql.Timestamp;
import com.entity.Sysuser;
import javax.servlet.http.HttpSession;
import com.entity.Sbbx;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Iterator;
import java.util.List;
import com.entity.Syssb;
import com.entity.ShiYan;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.springframework.ui.ModelMap;
import com.dao.SbbfMapper;
import com.dao.SbbxMapper;
import com.server.ShiYanServer;
import javax.annotation.Resource;
import com.server.SyssbServer;
import org.springframework.stereotype.Controller;

@Controller
public class SyssbController
{
    @Resource
    private SyssbServer syssbServer;
    @Resource
    private ShiYanServer shiYanServer;
    @Resource
    private SbbxMapper sbbxMapper;
    @Resource
    private SbbfMapper sbbfMapper;
    
    @RequestMapping({ "admin/doFindSyssb.do" })
    public String doFindSyssb(final ModelMap map, final int id) {
        final HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("sid", id);
        final List<Syssb> syssbList = this.syssbServer.getAll(objectHashMap);
        final ArrayList<ShiYan> shiYans = new ArrayList<ShiYan>();
        if (syssbList.size() > 0) {
            for (final Syssb s : syssbList) {
                final ShiYan shiYan = this.shiYanServer.getById(s.getSbid());
                shiYans.add(shiYan);
            }
        }
        System.out.println(syssbList);
        map.put("shebeis", shiYans);
        map.put("syssbs", syssbList);
        map.put("sysid", id);
        return "admin/list_Syssb";
    }
    
    @RequestMapping({ "admin/SheBeiwx.do" })
    public String sbbxsq(final ModelMap map, final int sysid, final int sbid) {
        final HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("sid", sysid);
        objectHashMap.put("sbid", sbid);
        final List<Syssb> syssbList = this.syssbServer.getAll(objectHashMap);
        ShiYan sb = null;
        ShiYan sys = null;
        int number = 0;
        if (syssbList.size() > 0) {
            for (final Syssb s : syssbList) {
                sb = this.shiYanServer.getById(s.getSbid());
                sys = this.shiYanServer.getById(s.getSid());
                number = s.getSnum();
            }
        }
        map.put("sys", sys);
        map.put("sb", sb);
        map.put("number", number);
        return "admin/add_sbbx";
    }
    
    @RequestMapping({ "admin/SheBeibf.do" })
    public String sbbfsq(final ModelMap map, final int sysid, final int sbid) {
        final HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("sid", sysid);
        objectHashMap.put("sbid", sbid);
        final List<Syssb> syssbList = this.syssbServer.getAll(objectHashMap);
        ShiYan sb = null;
        ShiYan sys = null;
        int number = 0;
        if (syssbList.size() > 0) {
            for (final Syssb s : syssbList) {
                sb = this.shiYanServer.getById(s.getSbid());
                sys = this.shiYanServer.getById(s.getSid());
                number = s.getSnum();
            }
        }
        map.put("sys", sys);
        map.put("sb", sb);
        map.put("number", number);
        return "admin/add_sbbf";
    }
    
    @RequestMapping({ "admin/addSbbx.do" })
    public String addSbbx(final HttpServletRequest request, final Sbbx sbbx, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("sid", sbbx.getSysid());
        objectHashMap.put("sbid", sbbx.getSbid());
        final List<Syssb> syssbList = this.syssbServer.getAll(objectHashMap);
        if (syssbList.get(0).getSnum() < sbbx.getBxnum()) {
            return "admin/error";
        }
        sbbx.setUid(u.getUid());
        sbbx.setBxtime(time.toString().substring(0, 19));
        sbbx.setBstatus("\u5f85\u5904\u7406");
        this.sbbxMapper.insertSelective(sbbx);
        final Syssb syssb = syssbList.get(0);
        syssb.setSnum(syssb.getSnum() - sbbx.getBxnum());
        this.syssbServer.update(syssb);
        return "success";
    }
    
    @RequestMapping({ "admin/addSbbf.do" })
    public String addSbbf(final HttpServletRequest request, final Sbbf sbbf, final HttpSession session) {
        final Sysuser u = (Sysuser)session.getAttribute("auser");
        final Timestamp time = new Timestamp(System.currentTimeMillis());
        if (u == null) {
            return "admin/login";
        }
        final HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        objectHashMap.put("sid", sbbf.getSysid());
        objectHashMap.put("sbid", sbbf.getSbid());
        final List<Syssb> syssbList = this.syssbServer.getAll(objectHashMap);
        if (syssbList.get(0).getSnum() < sbbf.getBfsnum()) {
            return "admin/error";
        }
        sbbf.setUid(u.getUid());
        sbbf.setBftime(time.toString().substring(0, 19));
        sbbf.setStatus("\u5f85\u5904\u7406");
        this.sbbfMapper.insertSelective(sbbf);
        final Syssb syssb = syssbList.get(0);
        syssb.setSnum(syssb.getSnum() - sbbf.getBfsnum());
        this.syssbServer.update(syssb);
        return "success";
    }
}
