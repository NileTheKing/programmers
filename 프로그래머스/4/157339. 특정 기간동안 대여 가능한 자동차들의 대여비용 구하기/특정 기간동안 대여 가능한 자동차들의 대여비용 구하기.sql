-- 코드를 입력하세요
SELECT c.car_id, c.car_type,
    round(c.daily_fee * 30 * (100 - p.discount_rate) / 100, 0) as fee
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type
where c.car_type in ('세단', 'SUV') and 
    p.duration_type = '30일 이상' and
    c.car_id not in (
        select car_id
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where START_DATE <= '2022-11-30' and END_DATE >= '2022-11-01'
        )
    
group by car_id
having fee between 500000 and 2000000
order by fee desc, car_type asc, c.car_id desc;