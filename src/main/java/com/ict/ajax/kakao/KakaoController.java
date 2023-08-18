package com.ict.ajax.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KakaoController {
	
	// 인증코드를 받기 위해서 redirect_uri를 사용
	@RequestMapping("/kakaologin.do")
	public ModelAndView KakaoLogin(String code) {
		ModelAndView mv = new ModelAndView("result3");
		// code 받기 확인
		// System.out.println(code);
		
		// 2.토큰 받기
		// POST https://kauth.kakao.com/oauth/token
		String reqURL ="https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			// POST 요청에 필요
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// 헤더 요청 
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			// 본문에  필요한 요구사항 4개
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=dc60adaaec3ec707e7223f6606814e1f");
			sb.append("&redirect_uri=http://localhost:8090/kakaologin.do");
			sb.append("&code=" +code);
			bw.write(sb.toString());
			bw.flush();
			
			// 성공하면 200 
			int responseCode = conn.getResponseCode();
			//System.out.println("responseCode : " + responseCode);
			if(responseCode == 200) {
				BufferedReader br =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer result = new StringBuffer();
				String line = null;
				while((line= br.readLine()) != null) {
					result.append(line);
				}
				//System.out.println(result.toString());
				
				// JSON 파싱 처리 "access_token"과 "refresh_token"을 잡아내어
				// 카카오 API 요청을 한 후
				// ModelAndView에 저장하여 result3.jsp에서 결과를 표현한다.
				JSONParser pars = new JSONParser();
				Object obj = pars.parse(result.toString());
				JSONObject json = (JSONObject)obj;
				
				String access_token = (String)json.get("access_token");
				String refresh_token= (String)json.get("refresh_token");
				
				// 마지막 3번쨰 사용자 정보 요청
				// GET/POST 
				String apiURL = "https://kapi.kakao.com/v2/user/me";
						
				// 헤더부분
				//Authorization Authorization: Bearer ${ACCESS_TOKEN}
				// "Content-type", "application/x-www-form-urlencoded;charset=utf-8"
				String header = "Bearer "+ access_token;
				
				URL url2 = new URL(apiURL);
				HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();
				// POST 방식
				conn2.setRequestMethod("POST");
				conn2.setDoOutput(true);
				
				//헤더 요청 사항
				conn2.setRequestProperty("Authorization", header);
				//conn2.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
				
				int res_code = conn2.getResponseCode();
				// HttpURLConnection.HTTP_OK =>성공시 200
				if(res_code == HttpURLConnection.HTTP_OK) {
					// 카카오 서버쪽에서 사용자의 정보를 보내다.
					// 이것을 읽어와서 필요한 정보들을 선별해야 한다.
					BufferedReader brdm = 
							new BufferedReader(new InputStreamReader(conn2.getInputStream()));
					
					String str = null;
					StringBuffer res = new StringBuffer();
					while((str = brdm.readLine()) != null) {
						res.append(str);
					}
					//사용자 정보
					 //System.out.println(res.toString());
					
					json =(JSONObject)pars.parse(res.toString());
					
					JSONObject props =(JSONObject)json.get("properties");
					String nickName = (String)props.get("nickname"); 
					String profile_image =  (String)props.get("profile_image"); 
					
					JSONObject kakao_account =(JSONObject)json.get("kakao_account");
					String email = (String)props.get("email"); 
					
					mv.addObject("nickName", nickName);
					mv.addObject("email", email);
					mv.addObject("profile_image", profile_image);
				 
				}
			}
				return mv;
		} catch (Exception e) {
			System.out.println(3);
			return null;
		}
	}
	
	@RequestMapping("/kakaomap01.do")
	public ModelAndView KaKaoMap01() {
		return new ModelAndView("kakao_map01");
	}
	@RequestMapping("/kakaomap02.do")
	public ModelAndView KaKaoMap02() {
		return new ModelAndView("kakao_map02");
	}
	@RequestMapping("/kakaomap03.do")
	public ModelAndView KaKaoMap03() {
		return new ModelAndView("kakao_map03");
	}
	@RequestMapping("/kakaomap04.do")
	public ModelAndView KaKaoMap04() {
		return new ModelAndView("kakao_map04");
	}
	@RequestMapping("/kakaoaddr.do")
	public ModelAndView KaKaoAddr() {
		return new ModelAndView("kakao_addr");
	}
	@RequestMapping("/kakao_addr_ok.do")
    public ModelAndView KakaoAddrOk(AddrVO addrVO) {
        ModelAndView mv = new ModelAndView("redirect:/");
        System.out.println(addrVO.getPostcode());    
        System.out.println(addrVO.getAddress());
        if(addrVO.getDetailaddress() == null || addrVO.getDetailaddress() == ""){
            System.out.println("상세 없음");
        }else {
            System.out.println(addrVO.getDetailaddress());            
        }
        System.out.println(addrVO.getClass());    
        // DB처리, 기타 등
        
        return mv;
        
    }
}

