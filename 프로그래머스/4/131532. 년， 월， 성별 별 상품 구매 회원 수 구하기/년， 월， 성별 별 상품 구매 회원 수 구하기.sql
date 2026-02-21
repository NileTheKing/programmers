-- 코드를 입력하세요
SELECT year(s.sales_date), month(s.sales_date), gender, count(distinct s.user_id)
from user_info i
join online_sale s
on i.user_id = s.user_id
where gender is not null
group by year(s.sales_date), month(s.sales_date), i.gender;
