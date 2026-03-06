-- 코드를 입력하세요
SELECT year(s.SALES_DATE) as year, month(s.SALES_DATE) as month, i.gender,
    count(distinct i.user_id) as users
from user_info i
join online_sale s on i.user_id = s.user_id
where i.gender is not null
group by year(s.SALES_DATE), month(s.SALES_DATE), gender
order by year, month, gender asc;