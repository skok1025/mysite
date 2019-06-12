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
DELIMITER ;