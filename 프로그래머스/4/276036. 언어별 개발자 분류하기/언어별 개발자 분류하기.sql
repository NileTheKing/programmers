-- 코드를 작성해주세요
select 
    case
        when skill_code & (select sum(code) from skillcodes where category = 'front end') and skill_code & (select code from skillcodes where name = 'python') then 'A'
        when skill_code & (select code from skillcodes where name = 'C#') then 'B'
        when skill_code & (select sum(code) from skillcodes where category = 'front end') then 'C'
    end as grade,
    ID, email
from developers
where skill_code & skill_code & (select sum(code) from skillcodes where category = 'front end') and skill_code & (select code from skillcodes where name = 'python') or 
skill_code & (select code from skillcodes where name = 'C#') or 
skill_code & (select sum(code) from skillcodes where category = 'front end')
order by grade, id asc;