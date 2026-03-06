-- 코드를 입력하세요
SELECT p.member_name, r.review_text, date_format(r.review_date, '%Y-%m-%d') as REVIEW_DATE
from rest_review r 
join member_profile p on p.member_id = r.member_id
where p.member_id = (
    select member_id
    from rest_review
    group by member_id
    order by count(member_id) desc limit 1
)
order by review_date asc, review_text asc;
# 제일 많이 쓴 사람 이름이 필요하다