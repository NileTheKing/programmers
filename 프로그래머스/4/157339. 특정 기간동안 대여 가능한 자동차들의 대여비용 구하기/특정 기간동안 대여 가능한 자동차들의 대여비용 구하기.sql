-- 코드를 입력하세요
SELECT c.car_id, c.car_type, floor(c.daily_fee * 30 * (100-p.discount_rate) / 100) as fee
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type  = p.car_type and
    p.duration_type = '30일 이상'
where c.car_Type in ('세단', 'SUV') and
    c.car_id not in (
        select c.car_id
        from CAR_RENTAL_COMPANY_CAR c
        join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
        where  (h.end_date >= '2022-11-01' and h.start_date <= '2022-11-30')
    ) and
    c.daily_fee * 30 * (100 - p.discount_rate) / 100 between 500000 and 2000000
order by fee desc, car_type asc, car_id desc;