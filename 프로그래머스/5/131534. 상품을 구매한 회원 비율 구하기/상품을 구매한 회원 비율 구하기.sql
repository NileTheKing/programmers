-- 코드를 입력하세요
SELECT year(s.sales_date) as year, month(s.sales_date) as month,
    count(distinct s.user_id) as purchased_users,
    round(count(distinct s.user_id)
    /
    (
        select count(*)
        from user_info
        where year(joined) = 2021
    ), 1) as PUCHASED_RATIO
from user_info i
join online_sale s on i.user_id = s.user_id
where year(i.joined) = 2021
group by year(s.sales_date), month(s.sales_date)