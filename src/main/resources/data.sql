insert   into member( memail , mpassword , mname)   values ( 'qwe1' , '1234' , '유재석');
insert   into member( memail , mpassword , mname)   values ( 'qwe2' , '1234' , '강호동');
insert   into member( memail , mpassword , mname)   values ( 'qwe3' , '1234' , '신동엽');
insert   into member( memail , mpassword , mname)   values ( 'qwe4' , '1234' , '하하');
insert   into member( memail , mpassword , mname)   values ( 'qwe5' , '1234' , '서장훈');

insert   into board( bcontent , mno_fk )   values ( '안녕하세요1' , 1 );
insert   into board( bcontent , mno_fk )   values ( '안녕하세요2' , 2 );
insert   into board( bcontent , mno_fk )   values ( '안녕하세요3' , 3 );
insert   into board( bcontent , mno_fk )   values ( '안녕하세요4' , 4 );
insert   into board( bcontent , mno_fk )   values ( '안녕하세요5' , 5 );
insert   into board( bcontent , mno_fk )   values ( '안녕하세요6' , 1 );
insert   into board( bcontent , mno_fk )   values ( '안녕하세요7' , 1 );

insert   into reply( rcontent , bno_fk , mno_fk )   values ( '댓글1' , 1  , 1);
insert   into reply( rcontent , bno_fk , mno_fk )   values ( '댓글2' , 1  , 2);
insert   into reply( rcontent , bno_fk , mno_fk )   values ( '댓글3' , 1  , 3);
insert   into reply( rcontent , bno_fk , mno_fk )   values ( '댓글4' , 1  , 1);
insert   into reply( rcontent , bno_fk , mno_fk )   values ( '댓글5' , 6  , 4);
insert   into reply( rcontent , bno_fk , mno_fk )   values ( '댓글6' , 7  , 5);