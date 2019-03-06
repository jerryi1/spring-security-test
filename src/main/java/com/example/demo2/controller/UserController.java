package com.example.demo2.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.demo2.archtechture.request.RestRequest;
import com.example.demo2.archtechture.response.RestResponse;
import com.example.demo2.bean.TUserEntity;
import com.example.demo2.pojo.ExportMoreView;
import com.example.demo2.pojo.ExportView;
import com.example.demo2.responsity.UserRepository;
import com.example.demo2.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author lihuaqing
 * @create 2019-02-20 15:43
 **/
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 查找所有用户的时候，验证权限是否符合
     * 导出我们的学生excel表格
     * */

    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.GET,value = "/searchUsers")
    public void getUsers(HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("aaa.xls", "UTF-8"));
        List<TUserEntity> lists = userRepository.findAll();
        //给用户设置统一的头像
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("aaa","ccc"),TUserEntity.class,lists);
        try {
           ServletOutputStream outputStream = response.getOutputStream();
           workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现多sheet导出  （失败）
     * */
//    @GetMapping("/page")
//    public void getUsersExcelByPage(HttpServletResponse response) throws UnsupportedEncodingException {
//         List<Map<String,Object>> exportParamList = Lists.newArrayList();
//         //下面是获取的我们的数据
//        ExportView.Builder build = new ExportView.Builder();
//        List<TUserEntity> lists =  userRepository.findAll();
//        ExportView exportView = build.cls(TUserEntity.class).dataList(lists).exportParams(new ExportParams()).create();
//        ExportMoreView exportMoreView = new ExportMoreView();
//        List<ExportView> list = new ArrayList();
//        list.add(exportView);
//        exportMoreView.setMoreViewList(list);
//
//        //迭代导出对象，将对应的配置信息写入到实际的配置中
//        for(ExportView view:exportMoreView.getMoreViewList()){
//            Map<String, Object> valueMap= Maps.newHashMap();
//            valueMap.put(NormalExcelConstants.PARAMS,view.getExportParams());
//            valueMap.put(NormalExcelConstants.DATA_LIST,view.getDataList());
//            valueMap.put(NormalExcelConstants.CLASS,view.getCls());
//            exportParamList.add(valueMap);
//        }
//
//        //实现导出配置
//        ModelMap modelMap = new ModelMap();
//        modelMap.put(NormalExcelConstants.FILE_NAME,new Date());
//
//        //将转换完成的数据导出
//        modelMap.put(NormalExcelConstants.MAP_LIST,exportParamList);
//        return NormalExcelConstants.JEECG_EXCEL_VIEW;
//    }

    /**
     * 删除用户的时候，是否级联删除（会级联删除对应的用户)
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/delUserByName")
    public Integer delUserByName(String name) {
        return userService.deleteUserByname(name);
    }

    /**
     * 修改用户信息
     * 使用统一的返回类型 Restresponse
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/updateUserInfo")
    public RestResponse updateUserInfo(@RequestBody RestRequest<TUserEntity> restRequest) {
        TUserEntity tUserEntity = restRequest.getBody();
        System.out.println(tUserEntity+"修改的时候的用户信息");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String password = bCryptPasswordEncoder.encode(tUserEntity.getPassword());
        tUserEntity.setPassword(password);
        String updatemsg = userService.updateUserInfo(tUserEntity);
       return RestResponse.success().withMessage(updatemsg);
    }

    /**
     * 根据id 查找用户信息
     * */
    @PreAuthorize("hasRole('NORMAL')")
    @RequestMapping(method = RequestMethod.POST,value = "/finduserByid")
    public RestResponse finduserByid(String userId) {
        System.out.println("查找传入来的参数"+userId);
        return userService.findUserById(userId);
    }

    /**
     * Excel导入
     * */
    @RequestMapping("/uploadFile")
    public void uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        try {
           List<TUserEntity> list = ExcelImportUtil.importExcel(file.getInputStream(),TUserEntity.class,params);
           userRepository.save(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("上传文件成功");
    }


}

