package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.BoardImgEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.BoardImgEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BoardService {
    // * 리포지토리 객체
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private MemberService memberService;
    @Autowired private FileService fileService;
    @Autowired private BoardImgEntityRepository boardImgEntityRepository;

    // 1. C
    @Transactional
    public boolean postBoard( BoardDto boardDto){ //  ======= 테스트 ==========
        MemberDto loginDto =  memberService.doLoginInfo();
        if( loginDto == null ) return false;
        // 1. 로그인된 회원 엔티티 찾기
        Optional< MemberEntity > optionalMemberEntity = memberEntityRepository.findById( loginDto.getMno() );
        // 2. 찾은 엔티티가 존재하지 않으면 실패;
        if( !optionalMemberEntity.isPresent() ) return false;
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();
           // - 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save( boardDto.toEntity() ) ;
            // - FK 대입
        if( saveBoard.getBno() >= 1){ // 글쓰기를 성공했으면
            saveBoard.setMemberEntity( memberEntity );

            // 1. 하나씩 업로드 서비스에 업로드 요청
            boardDto.getUploadList().forEach( (file)->{
                // 2. 하나씩 업로드 된 파일명 반환 받기
                String filename = fileService.fileUpload( file );
                if( filename != null ){
                    // 3. 하나씩 업로드된 파일명으로 게시물파일엔티티 생성
                    BoardImgEntity boardImgEntity = BoardImgEntity.builder()
                            .bimg( filename ) // 방금 위에서 업로드된 파일명을 엔티티에 대입
                            .boardEntity( saveBoard ) // FK : 게시물FK
                            .build();
                    // 4. 엔티티 영속성( 영속성이 필요한 이유 :  DB 저장할려고 => 서버가 종료되면 사라지니까 => 서버가 영구저장 )
                    boardImgEntityRepository.save( boardImgEntity );
                }
            });
            return true;
        }
        return false;
    } // f end

    // 2. R
    @Transactional
    public PageDto getBoard( int page , int view){
        // 1. pageable 인터페이스 이용한 페이징처리
            // pageable은 page 순서 0부터 시작하기 때문에 page가 1페이지일때 0페이지 변환하기 위해 -1
        Pageable pageable = PageRequest.of( page-1  , view  );
            // 1.페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage = boardEntityRepository.findAll( pageable );
            // -- List 아닌 Page 타입일때 List 동일한 메소드 사용하고 추가 기능
                // 1. 전체 페이지수
            System.out.println("boardEntityPage.getTotalPages()  = " + boardEntityPage.getTotalPages() );
        int count = boardEntityPage.getTotalPages();
                // 2. 전페 게시물수
            System.out.println("boardEntityPage.getTotalElements()  = " + boardEntityPage.getTotalElements() );
            // 2. 엔티티를 Dto 변환
        List< Object > data = boardEntityPage.stream().map( (boardEntity)->{
                return boardEntity.toDto();
            }).collect(Collectors.toList());
        // 2. 페이지DTO 반환 값 구성
        PageDto pageDto = PageDto.builder()
                .data( data )// 페이징 처리된 레코드들을 대입
                .page( page )// 현재 페이지 수
                .count( count  ) // 전체 페이지 수
                .build();
        return pageDto;
        // ======================= ===== ============================ //
    }
    // 3. U
    @Transactional
    public boolean putBoard( BoardDto boardDto ){
        BoardEntity boardEntity = boardEntityRepository.findById( boardDto.getBno() ).get();
        boardEntity.setBcontent( boardDto.getBcontent() );

        List<String> oldList = boardEntity.getBoardImgEntityList().stream().map((img) -> { return img.getBimg();}).collect(Collectors.toList());

        List<String> aaa = oldList.stream().filter(o -> boardDto.getBimgList().stream()
                .noneMatch(Predicate.isEqual(o))).collect(Collectors.toList());
        System.out.println("aaa = " + aaa);

        aaa.forEach( a ->{
            BoardImgEntity boardImgEntity2 = boardImgEntityRepository.findById(a).get();
            boardImgEntityRepository.delete(  boardImgEntity2 );
        });

        return true;
    }
    // 4. D
    @Transactional
    public boolean deleteBoard( int bno ){
        // 1. 회원번호 = 회원서비스에 회원된 정보
        MemberDto memberDto = memberService.doLoginInfo();
        if( memberDto == null ) return false; // 비로그인
        // 2. 내 게시물 확인
        Optional<BoardEntity> optionalBoardEntity = boardEntityRepository.findById( bno );
        if( optionalBoardEntity.isPresent() ){
            if(  optionalBoardEntity.get().getMemberEntity().getMno()  == memberDto.getMno() ){
                boardEntityRepository.deleteById( bno ); return true;
            }
        }
        return false;
    }

    public BoardDto getViewBoard(int bno  ){
        return boardEntityRepository.findById( bno ).get().toDto();
    }

}


// ======================= 1 ============================ //
        /*
        // 1. 리포지토리를 이용한 모든 엔티티( 테이블에 매핑 하기전 엔티티 )를 호출
        List<BoardEntity> result = boardEntityRepository.findAll( );
        // 2. Entity ---> Dto 변환한다
        List<BoardDto> boardDtoList = new ArrayList<>();
            // 1. 꺼내온 entity 을 순회한다
        for( int i = 0 ; i < result.size() ; i++ ){
                // 2. 하나씩 enriry 꺼낸다
            BoardEntity boardEntity = result.get(i);
                // 3. 해당 엔티티를 dto로 변환한다.
            BoardDto boardDto = boardEntity.toDto();
                    // ---------- 게시물안에 게시물사진
                    List<String> bimgList = new ArrayList<>();
                    for( int j = 0 ; j < boardEntity.getBoardImgEntityList().size() ; j++ ){
                        BoardImgEntity boardImgEntity = boardEntity.getBoardImgEntityList().get(j);
                        String bimg = boardImgEntity.getBimg();
                        bimgList.add( bimg );
                    }
                    boardDto.setBimgList( bimgList );
                // 4. 변환된 dto를 리스트에 담는다.
            boardDtoList.add( boardDto );
        }
        return boardDtoList;
        */
// ======================= ===== ============================ //
