package ezenweb.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    // 1. 업로드 경로
        // 내장서버 경로
    String uploadPath = "C:\\Users\\MSI\\ezen2023B_web2\\build\\resources\\main\\static\\uploadimg\\";
        // AWS 경로

    // 2. multipartFile 존재하는 파일 업로드
    public String fileUpload(MultipartFile multipartFile ){
        // 1. 파일 이름을 식별 가능한 uuid와 조합
        String uuid = UUID.randomUUID().toString(); // UUID란?? 고유한 id 난수성으로 생성
        // 2. 조합 ( uuid와 파일이름의 구분선을 _ 이기때문에 파일명에 _ 존재할수도 있기때문에 _를 - 로 치환 )
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll( "_" ,"-");
        // 3.
        File file = new File( uploadPath + filename );
        // 4.
        try {   multipartFile.transferTo(file);}
        catch ( Exception e ){   System.out.println("e = " + e); return null;   }
        return filename;
    }
}











