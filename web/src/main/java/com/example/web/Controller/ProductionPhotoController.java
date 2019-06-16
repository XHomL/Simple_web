package com.example.web.Controller;

import com.example.web.Bean.Production;
import com.example.web.Service.ProductionPhotoService;
import com.example.web.Service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@Controller
public class ProductionPhotoController {
    @Autowired
    ProductionPhotoService productionPhotoService;
    @Autowired
    ProductionService productionService;

    @RequestMapping(value = "/uploadProductionPhoto",method = RequestMethod.POST)
    @ResponseBody
    public Boolean uploadProductionPhoto(HttpServletRequest request)
    {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("productionPhoto");
        String productionName=params.getParameter("productionName");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        String path="D:\\JavaeeWorkSpace\\web\\src\\main\\resources\\static\\uploadProduction\\";

        //String path = "C:\\Users\\James Zhang\\Desktop\\数据库课程\\web\\src\\main\\resources\\static\\upload";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            System.out.println(file);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(path+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
        String productionPhoto="\\uploadProduction\\"+file.getOriginalFilename();
        Production production=productionService.getProductionByName(productionName);
        return productionPhotoService.uploadProductionPhoto(production.getProductionId(),productionPhoto,productionName);
    }

    @RequestMapping("/getProductionPhoto")
    @ResponseBody
    public List<String> getProductionPhoto(@RequestParam Map<String,Object> map){
        String productionName=map.get("productionName").toString();
        return productionPhotoService.getProductionPhoto(productionName);
    }
}
