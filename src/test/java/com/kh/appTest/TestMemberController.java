package com.kh.appTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//SpringJUnit4ClassRunner는 스프링 테스트를 위한 Runner 클래스 지정
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration   // Controller및 web환경에 사용되는 bean들을 자동으로 생성하여 등록
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) //자동으로 만들어줄 애플리케이션 컨텍스트의 설정 파일 경로를 지정
public class TestMemberController {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(TestMemberController.class);
   // Logger : 로그 출력을 위한 log4j 객체
   
   // 테스트용 Class에서 사용될 필드 선언
   @Autowired
   private WebApplicationContext wac; // 현재 실행중인 애플리케이션의 구성을 제공하는 인터페이스
   private MockMvc mockMvc;   // client 요청 내용을 controller에서 받아 처리하는 것과 같은 테스트를 진행할 수 있는 클래스.

   
   @Before // JUnit 테스트 진행 전 먼저 실행하는 것을 지정하는 어노테이션
   public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
      LOGGER.info("setup() 메소드 호출");
   }


   @Test // 테스트용 메소드임을 명시하는 어노테이션
   public void testmemberRegister() {
      
      LOGGER.info("테스트 시작");
      try{
         // 테스트 메소드 내부에 mockMvc를 이용하여 매핑될 url과 필요한 데이터가 담긴 가상의 요청을 작성.  (perfrom() 메소드)

         mockMvc.perform(post("/member/memberEnroll.do")
         .param("userid","test01")
         .param("passwd","test01*")
         .param("email","test01@gmail.com")
         .param("tel","01023456789")  )
         .andDo(print())  //처리되어진 내용을 출력 
         .andExpect(status().isOk()); // 응답 상태값이 에러가 없는 정상적인 상태(status 가 200)가 되도록 검증 
         
         LOGGER.info("테스트 시작");
         
      } catch(Exception e){
         e.printStackTrace();
      }
   }
}