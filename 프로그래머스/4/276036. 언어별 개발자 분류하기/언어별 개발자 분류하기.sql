-- 코드를 작성해주세요
select 
    case
        when skill_code & (select sum(code) from skillcodes where category = 'Front End') and skill_code & (select code from skillcodes where name = 'Python') then 'A'
        when skill_code & (select code from skillcodes where name = 'C#') then 'B'
        when skill_code & (select sum(code) from skillcodes where category = 'Front End') then 'C'
    end as grade
    , id, email
from developers
where (id) in (
    select id
    from developers
    where skill_code & (select sum(code) from skillcodes where category = 'Front End') and skill_code & (select code from skillcodes where name = 'Python') or skill_code & (select code from skillcodes where name = 'C#') or skill_code & (select sum(code) from skillcodes where category = 'Front End')
)
group by grade, id
order by grade, id;