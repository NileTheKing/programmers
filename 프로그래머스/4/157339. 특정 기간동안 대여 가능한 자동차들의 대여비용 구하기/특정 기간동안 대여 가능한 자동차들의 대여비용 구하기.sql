-- 코드를 입력하세요
SELECT c.car_id, c.car_type,
    floor(30 * c.daily_fee * (100 - p.discount_rate) / 100) as fee
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
on c.car_type = p.car_type and p.duration_type = '30일 이상'
where c.car_type in ('세단', 'SUV') and
    c.car_id not in (
        select car_id
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
        where start_date <= '2022-11-30' and end_date >= '2022-11-01'
    ) and
    30 * c.daily_fee * (100 - p.discount_rate) / 100 between 500000 and 2000000
order by fee desc, c.car_type, c.car_id desc;