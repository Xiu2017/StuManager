package com.stu.web;

import com.stu.entity.Stu;
import com.stu.service.StuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created by xiu on 2017/12/20.
 */
@Controller
@RequestMapping("/stuAction")
public class StuAction {

    @Resource(name = "stuService")
    private StuService stuService;

    @RequestMapping("/test")
    public void testMsg(){
        System.out.println("测试通过");
    }

    //增

    //删

    /**
     * 通过学生id获取学生
     * @param sno int
     * @param modelMap ModelMap
     * @return String
     */
    @RequestMapping("/getStuById")
    public String getStuById(int sno, ModelMap modelMap, @ModelAttribute(name = "updateMessage") String updateMsg){
        if(sno != 0) {
            modelMap.addAttribute("stu", stuService.getStuById(sno));
            modelMap.addAttribute("updateMsg", updateMsg);
        }
        return "/pages/updateStu";
    }

    /**
     * 修改学生
     * @param stu Stu
     * @param attr RedirectAttributes
     * @return String
     */
    @RequestMapping("/updateStu")
    public String updateStu(Stu stu, RedirectAttributes attr){
        if (stuService.updateStu(stu)) {
            attr.addFlashAttribute("updateMessage", "1");
            return "redirect:/stuController/findStuList";
        }
        attr.addFlashAttribute("updateMessage", "-1");
        return "redirect:/stuAction/getStuById?sno=" + stu.getSno();
    }

    /**
     * 获取学生集合
     * @param modelMap ModelMap
     * @param updateMsg String
     * @return String
     */
    @RequestMapping("/findStuList")
    public String findStuList(ModelMap modelMap, @ModelAttribute(name = "updateMessage") String updateMsg) {
        modelMap.addAttribute("stuList", stuService.findStuList());
        modelMap.addAttribute("updateMsg", updateMsg);
        return "pages/showStu";
    }


    public void setStuService(StuService stuService) {
        this.stuService = stuService;
    }
}
