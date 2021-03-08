package com.kh.appTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// spring-test 사용
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestDBConnect {
   private static final Logger logger = LoggerFactory.getLogger(TestDBConnect.class);
   // Logger : 로그 출력을 위한 log4j 객체
   
   @Autowired
   private ApplicationContext wac;
   
   @Before
   public void beforeClass() {
      logger.info("-----테스트 시작-----");      
   }
   
   @After
   public void afterClass() {
      logger.info("-----테스트 종료-----");
   }

   @Test
   public void dbTest() {

      // 컨테이너에서 getBean()
      SqlSessionTemplate session
      = wac.getBean("sqlSessionTemplate",SqlSessionTemplate.class);
      logger.info("session : " + session.toString());
   }   
}