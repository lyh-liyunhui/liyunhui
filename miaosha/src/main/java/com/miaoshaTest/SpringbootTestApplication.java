package com.miaoshaTest;

import com.miaoshaTest.dao.UserDOMapper;
import com.miaoshaTest.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.miaoshaTest"})
@MapperScan("com.miaoshaTest.dao")
@RestController
public class SpringbootTestApplication {

	@Autowired
	private UserDOMapper userDOMapper;


	@RequestMapping("/hello")
	public String home(){
		UserDO userDO=userDOMapper.selectByPrimaryKey(1);
		if(userDO==null){
			return "用户对象不存在";
		}else{
			return userDO.getName();
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestApplication.class, args);
	}

}
