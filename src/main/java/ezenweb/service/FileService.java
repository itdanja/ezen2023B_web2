package ezenweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨터이너(저장소)에 빈(객체) 등록
public class FileService {
    // Controller : 중계자 역할 ( HTTP매핑 , HTTP요청/응답 , 데이터 유효성검사 )등등
    // Service : Controller <--  Service(비지니로직) --> Dao ,  Controller <--> Service(비지니로직)

    // 어디에(PATH) 누구를(파일객체 MultipartFile )
    String uploadPath = "C:\\Users\\MSI\\ezen2023B_web2\\build\\resources\\main\\static\\uploadFile\\";

    // 1. 업로드 서비스 메소드
    public String fileUpload(MultipartFile multipartFile) {
        // * 파일 이름 조합하기 : 새로운 식별이름과 실제 파일 이름
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + "_" + multipartFile.getOriginalFilename().replaceAll("_", "-");
        // 1. [어디에] 첨부파일을 저장할 경로
        // File 클래스 : 파일 관련된 메소드 제공.
        File file = new File(uploadPath + filename);
        // 2. [무엇을] 첨부파일 객체
        // .transferTo( 경로 )
        try {
            multipartFile.transferTo(file);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return null;
        }
        return filename; // 반환 : 어떤 이름으로 업로드 했는지 식별명 반환해서
    }
}