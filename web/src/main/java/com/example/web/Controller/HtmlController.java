package com.example.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {
    @RequestMapping("/orderList")
    public String orderList() {
        return "orderList";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail() {
        return "orderDetail";
    }

    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    @RequestMapping("/recharge")
    public String recharge() {
        return "/recharge";
    }

    @RequestMapping("/pay")
    public String pay() {
        return "/pay";
    }

    @RequestMapping("/withdraw")
    public String withdraw() {
        return "/withdraw";
    }

    @RequestMapping("/chmsg")
    public String chmsg() {
        return "/chmsg";
    }

    @RequestMapping("/trans")
    public String trans() {
        return "/trans";
    }

    @RequestMapping("/register")
    public String register() {
        return "/register";
    }

    @RequestMapping("/mypage")
    public String mypage() {
        return "/mypage";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/404")
    public String error404() {
        return "/404";
    }

    @RequestMapping("/admin_flowers")
    public String admin_flowers() {
        return "admin_flowers";
    }

    @RequestMapping("/admin_gifts")
    public String admin_gifts() {
        return "admin_gifts";
    }

    @RequestMapping("/admin_login")
    public String admin_login() {
        return "admin_login";
    }

    @RequestMapping("/admin_manager")
    public String admin_manager() {
        return "admin_manager";
    }

    @RequestMapping("/admin_order")
    public String admin_order() {
        return "admin_order";
    }

    @RequestMapping("/admin_pay")
    public String admin_pay(){return "admin_pay";}

    @RequestMapping("/admin_user")
    public String admin_user(){return "admin_user";}

    @RequestMapping("/admin_virtual_flowers")
    public String admin_virtual_flowers(){return "admin_virtual_flowers";}

    @RequestMapping("/admin_withdrawal")
    public String admin_withdrawal(){return "admin_withdrawal";}

    @RequestMapping("otherHome")
    public String otherHome(){ return "otherHome"; }

    @RequestMapping("itemControl")
    public String itemControl(){ return "itemControl"; }

}
