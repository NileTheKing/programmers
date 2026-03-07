-- 코드를 입력하세요
SELECT p.member_name, r.review_text, date_format(r.review_date, '%Y-%m-%d') as review_date
from rest_review r
join member_profile p
on r.member_id=p.member_id
where p.member_id = (
    select member_id
    from rest_review
    group by member_id
    order by count(*) desc limit 1
)
order by review_date asc, review_text asc;