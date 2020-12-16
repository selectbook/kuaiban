package com.iiot.business.api;


import com.iiot.business.domain.*;
import com.iiot.business.service.*;
import com.iiot.business.vo.*;
import com.iiot.common.annotation.IgnoreAuth;
import com.iiot.common.constant.Constants;
import com.iiot.common.constant.UserConstants;
import com.iiot.common.core.domain.ApiCode;
import com.iiot.common.core.domain.RestResp;
import com.iiot.common.utils.DateUtils;
import com.iiot.common.utils.MessageUtils;
import com.iiot.common.utils.StringUtils;
import com.iiot.common.utils.TokenUtils;
import com.iiot.framework.manager.AsyncManager;
import com.iiot.framework.manager.factory.AsyncFactory;
import com.iiot.framework.shiro.service.SysPasswordService;
import com.iiot.framework.shiro.service.SysRegisterService;
import com.iiot.framework.util.ShiroUtils;
import com.iiot.system.domain.*;
import com.iiot.system.service.ISysDeptService;
import com.iiot.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 账号授权
 */
@Api(tags = "01.账号授权")
@RestController
@RequestMapping("/api/auth")
public class AuthApiController extends BaseApiController {


    @Autowired
    private ISysUserService userService;


    @Autowired
    private IXtDeviceService xtDeviceService;


    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private IXtCarUpkeepService xtCarUpkeepService;


    @Autowired
    private IXtOilFillService oilFillService;

    @Autowired
    private IXtOilDrumService xtOilDrumService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private IXtRegistinfoService xtRegistinfoService;


    /**
     * 用户注册
     * @param regist
     * @return
     */
    @ApiOperation(value = "用户注册")
    @IgnoreAuth
    @PostMapping("regist")
    public RestResp regist(@RequestBody RegisteVO regist) {
        //IgnoreAuth不认证token
        if (StringUtils.isEmpty(regist.getUsername())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "姓名不能为空");
        }

        if (regist.getAge() == 0) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "年龄不能为0");
        }

        if (StringUtils.isEmpty(regist.getPhonenumber())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "手机号不能为空");
        }

        if (StringUtils.isEmpty(regist.getEmail())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "邮箱不能为空");
        }

        if (StringUtils.isEmpty(regist.getPassword())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "密码不能为空");
        }

        if (StringUtils.isEmpty(regist.getRepassword())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "确认密码不能为空");
        }

//        String sycpassword = new Md5Hash(regist.getUsername() + regist.getPassword() + us.getSalt()).toHex();

        if (regist.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || regist.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "密码长度必须在5到20个字符之间");
        }

        if (regist.getUsername().length() < UserConstants.USERNAME_MIN_LENGTH
                || regist.getUsername().length() > UserConstants.USERNAME_MAX_LENGTH) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "账户长度必须在2到20个字符之间");
        }

        if (!regist.getPassword().equals(regist.getRepassword())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "两次输入的密码不一致");
        }


        SysUser sysUser = new SysUser();
        sysUser.setLoginName(regist.getUsername());
        sysUser.setEmail(regist.getEmail());
        sysUser.setPhonenumber(regist.getPhonenumber());
        sysUser.setPassword(regist.getPassword());
        sysUser.setAge(regist.getAge());


        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(sysUser.getLoginName()))) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "失败，注册账号已存在");
        }

        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser))) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "失败，手机号码已存在");
        }

        if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(sysUser))) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "失败，邮箱账号已存在");
        }

//        user.setSalt(ShiroUtils.randomSalt());
//        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));

        boolean regFlag = userService.registerUser(sysUser);
        if (!regFlag)
        {
            return RestResp.error(ApiCode.DB_ERROR.getCode(), "注册失败,请联系系统管理人员");
        }
//        else
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
//        }
        //封装返回
        Map<String, Object> retMap = new HashMap<>(2);
        retMap.put("username", sysUser.getLoginName());
        return RestResp.success("注册成功！", retMap);
    }


    @ApiOperation(value = "用户登录")
    @IgnoreAuth
    @PostMapping("login")
    public RestResp login(@RequestBody LoginVo login) {
        //IgnoreAuth不认证token
        if (StringUtils.isEmpty(login.getPhone())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "手机号不能为空");
        }

        if (StringUtils.isEmpty(login.getPassword())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "密码不能为空");
        }

        SysUser user = userService.selectUserByPhoneNumber(login.getPhone());
        if (StringUtils.isNull(user)) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "账号不存在！");
        }

        //判断密码
//        String sycpassword = new Md5Hash(login.getUsername() + login.getPassword() + user.getSalt()).toHex();
        if (!user.getPassword().equals(login.getPassword())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "账号或密码错误！");
        }

        //更新登录
        user.setLoginIp(getClientIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUser(user);

        // 生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 更新缓存
        TokenUtils.setTokenCache(token, user.getLoginName());
        //封装返回
        Map<String, Object> retMap = new HashMap<>(2);
        retMap.put("token", token);
        retMap.put("username", user.getLoginName());
        retMap.put("userId", user.getUserId());
        retMap.put("head_img", "http://42.192.52.158/static/img/pay.addead2.jpg");
        return RestResp.success("登录成功！", retMap);
    }

    /**
     * 报名考试
     *
     * @param registInfoVo
     * @return
     * @IgnoreAuth
     */
    @ApiOperation(value = "报名考试")
    @IgnoreAuth
    @PostMapping("examRegist")
    public RestResp examRegist(@RequestBody RegistInfoVo registInfoVo) {
        XtRegistinfo registinfo;
        //IgnoreAuth不认证token
        if (StringUtils.isEmpty(registInfoVo.getUserName())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "姓名不能为空");
        }

        if (StringUtils.isEmpty(registInfoVo.getPhonenumber())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "手机号不能为空");
        }

//        SysUser user = userService.selectUserByPhoneNumber(login.getPhone());
//        if (StringUtils.isNull(user)) {
//            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "账号不存在！");
//        }

        registinfo = new XtRegistinfo();
        registinfo.setAge(registInfoVo.getAge());
        registinfo.setDegree(registInfoVo.getDegree());
        registinfo.setEmail(registInfoVo.getEmail());
        registinfo.setIdcard(registInfoVo.getIdcard());
        registinfo.setPhonenumber(registInfoVo.getPhonenumber());
        registinfo.setRegistType(registInfoVo.getRegistType());
        registinfo.setUserName(registInfoVo.getUserName());
        registinfo.setSubject(registInfoVo.getSubject());
        registinfo.setWechat(registInfoVo.getWechat());
        registinfo.setSex(registInfoVo.getSex());

        int rows = xtRegistinfoService.insertXtRegistinfo(registinfo);


        if (rows <= 0)
        {
            return RestResp.error(ApiCode.DB_ERROR.getCode(), "报名失败,请联系系统管理人员");
        }

        //封装返回
        Map<String, Object> retMap = new HashMap<>(2);
        return RestResp.success("报名成功！", retMap);
    }


    /**
     *
     * 报名信息
     * @param registInfoVo
     * @return
     * @IgnoreAuth
     */
    @ApiOperation(value = "报名信息")
    @IgnoreAuth
    @PostMapping("examRegistInfo")
    public RestResp examRegistInfo(@RequestBody RegistInfoVo registInfoVo) {
        XtRegistinfo registinfo = new XtRegistinfo();
        //IgnoreAuth不认证token
        if (StringUtils.isEmpty(registInfoVo.getUserName())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "姓名不能为空");
        }

        registinfo = xtRegistinfoService.selectXtRegistinfoByName(registInfoVo.getUserName());


        //封装返回
        Map<String, Object> retMap = new HashMap<>(10);
//        retMap.put("userName", registinfo.getUserName());
//        retMap.put("phonenumber", registinfo.getPhonenumber());
//        retMap.put("registType", registinfo.getRegistType());
//        retMap.put("age", registinfo.getAge());
//        retMap.put("email", registinfo.getEmail());
//        retMap.put("degree", registinfo.getDegree());
//        retMap.put("wechat", registinfo.getWechat());
//        retMap.put("sex", registinfo.getSex());
        retMap.put("examRegistInfo", registinfo);
        return RestResp.success("报名信息返回成功！", retMap);
    }


    /**
     *
     * 用户信息
     * @param registInfoVo
     * @return
     * @IgnoreAuth
     */
    @ApiOperation(value = "用户信息")
    @IgnoreAuth
    @PostMapping("userInfo")
    public RestResp userInfo(@RequestBody RegistInfoVo registInfoVo) {
        SysUser sysUser;
        //IgnoreAuth不认证token
        if (StringUtils.isEmpty(registInfoVo.getUserName())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "姓名不能为空");
        }

       sysUser = userService.selectUserByLoginName(registInfoVo.getUserName());

        //封装返回
        Map<String, Object> retMap = new HashMap<>(6);
        if (sysUser == null) {
            return RestResp.error(40, "没有用户信息");
        }
        retMap.put("userName", sysUser.getLoginName());
        retMap.put("email", sysUser.getEmail());
        retMap.put("phonenumber", sysUser.getPhonenumber());
        retMap.put("sex", sysUser.getSex());
        retMap.put("age", sysUser.getAge());

        return RestResp.success("用户信息返回成功！", retMap);
    }


    /**
     *
     *修改密码
     * @param updatePassVo
     * @return
     * @IgnoreAuth
     */
    @ApiOperation(value = "修改密码")
    @IgnoreAuth
    @PostMapping("updatePass")
    public RestResp updatePass(@RequestBody UpdatePassVo updatePassVo) {
        SysUser sysUser = new SysUser();
        //IgnoreAuth不认证token
        if (StringUtils.isEmpty(updatePassVo.getPassword())) {
            return RestResp.error(ApiCode.PARAM_EXC.getCode(), "密码不能为空");
        }

        sysUser.setUserId(updatePassVo.getUserID());
        sysUser.setPassword(updatePassVo.getPassword());

        int flag = userService.updateUser(sysUser);
        if (flag <= 0) {
            return RestResp.error(ApiCode.DB_ERROR.getCode(), "重置密码,请联系系统管理人员");
        }

        //封装返回
        Map<String, Object> retMap = new HashMap<>(2);
        return RestResp.success("修改密码成功！", retMap);
    }




}
