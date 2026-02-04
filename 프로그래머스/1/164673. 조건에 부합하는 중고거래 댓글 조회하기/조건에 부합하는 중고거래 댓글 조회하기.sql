-- 코드를 입력하세요
SELECT title, b.board_id, reply_id, r.writer_id, r.contents, date_format(r.created_date, '%Y-%m-%d') as create_date
from used_goods_board as b
join used_goods_reply as r
on b.board_id = r.board_id
where YEAR(b.created_date) = 2022 and month(b.created_date) = 10
order by r.created_date asc, b.title asc;