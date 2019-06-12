DELIMITER $$
	CREATE procedure proc_insertguestbook
	(in pname varchar(20),in ppassword varchar(20),in pcontents text,out presult integer)
	begin

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN        
	set presult =0;
		rollback;
		END;

	insert into guestbook(no,name,password,contents,reg_date)
	select null,pname,ppassword,pcontents,now() from dual where (select pcontents like '%<script>%')!=1 and (select ppassword like '%<script>%')!=1;
	set presult = 1;
	commit;    

	end $$


	CREATE procedure proc_selectguestbook
	()
	begin
		select no, name, contents, date_format(reg_date,'%Y-%m-%d %h:%i:%s') from guestbook order by reg_date desc;
	end $$
    
    CREATE procedure proc_selectBoard
    (in pstart integer, in pcountperpage integer, in pkwd varchar(20))
    begin
		select isexist,no,member_no as memberNo, title, hit,date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date,(select name from user where no = b.member_no) as memberName,contents,originfilename,savefilename,depth from board b where title like concat('%',pkwd,'%') order by group_no desc,order_no desc 
		limit pcountperpage offset pstart;
    end $$
   
 -- 게시판 인서트  
CREATE PROCEDURE loopInsert() 
BEGIN
	DECLARE i INT DEFAULT 1;

	WHILE i <= 10000 DO 

		 insert into
		board values(null,'제목',0,now(),'내용',(select ifnull(max(b1.group_no),0)+1 from board b1),(select ifnull(max(b2.order_no),0)+1 from board b2),0,null,null,'exist',1);
		SET i = i + 1;

	END WHILE;

END$$


    
    
DELIMITER ;