-- 코드를 입력하세요
SELECT year(sales_date) as year,
    month(sales_date) as month,
    count(distinct s.user_id) as purchased_users,
    round(
        count(distinct s.user_id) /
          (select count(*) from user_info where year(joined) = 2021), 
         1) as purchased_ratio
from user_info i
join online_sale s on i.user_id = s.user_id
where year(joined) = 2021
group by year(sales_date), month(sales_date)
order by year, month;