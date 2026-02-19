-- 코드를 입력하세요
SELECT b.author_id, a.author_name, b.category, sum(s.sales * b.price) as total_sales
from book b
join author a
join book_sales s
on b.author_id = a.author_id and  b.book_id = s.book_id
where year(s.sales_date) = 2022 and month(s.sales_date) = 1
group by b.author_id, b.category
order by a.author_id asc, b.category desc;