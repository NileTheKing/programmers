-- 코드를 작성해주세요
select id, email, first_name, last_name
from developers
where skill_code & (select sum(code) from skillcodes where category = "Front End")
order by id;
#developers랑 code 이어가지고 ..X 안됨. 서브쿼리