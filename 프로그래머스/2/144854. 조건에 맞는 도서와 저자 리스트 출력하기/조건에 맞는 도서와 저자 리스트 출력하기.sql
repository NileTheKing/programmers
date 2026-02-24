-- 코드를 입력하세요
SELECT b.BOOK_ID, a.AUTHOR_NAME, date_format(b.PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from book b
join author a
on b.author_id = a.author_id
where category = '경제'
order by published_date asc;