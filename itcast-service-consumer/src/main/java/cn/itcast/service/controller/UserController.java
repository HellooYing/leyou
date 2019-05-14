package cn.itcast.service.controller;

import cn.itcast.service.client.UserClient;
import cn.itcast.service.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("consumer/user")
public class UserController {

    @Autowired
    private UserClient userClient;

    @GetMapping
    @ResponseBody
    public String queryUserById(@RequestParam("id") Long id){
        return this.userClient.queryUserById(id);
    }

}
//@Controller
//@RequestMapping("consumer/user")
//@DefaultProperties(defaultFallback = "fallBackMethod")
//public class UserController {
//    //@Autowired
//    //private RestTemplate restTemplate;
//
//    @Autowired
//    private UserClient userClient;
//
//    @GetMapping
//    @ResponseBody
//    //@HystrixCommand//(fallbackMethod = "queryUserByIdFallBack")
//    public User queryUserById(@RequestParam("id") Long id){
//        return this.userClient.queryById(id);
//        //String baseUrl = "http://service-provider/user/" + id;
//        //return this.restTemplate.getForObject(baseUrl, String.class);
//    }
//    public String fallBackMethod(){
//        return "请求繁忙，请重试！";
//    }
//}
