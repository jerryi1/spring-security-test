package com.example.demo2.controller;

import com.example.demo2.pojo.Person1;
import com.example.demo2.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author lihuaqing
 * @create 2019-03-05 15:32
 **/
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    ExcelUtils excelUtils;

    @PostMapping
    @ResponseBody
    public List importexcel(@RequestParam("file") MultipartFile file){
        List lists = null;
        try {
            Person1 person1 = new Person1();
            lists = excelUtils.importDataFromExcel(person1,file.getInputStream(),"aaa.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }
}