-- 코드를 입력하세요
SELECT 
    c.car_id, 
    c.car_type, 
    floor(c.daily_fee * 30 * (1 - p.discount_rate/100)) as fee
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type
where p.DURATION_TYPE = '30일 이상' and c.car_type in ('세단','SUV')
group by c.car_id, c.car_type, c.daily_fee, p.discount_rate
having 
    sum(case when h.end_date >= '2022-11-01' and h.start_date <= '2022-11-30' then 1 else 0 end) = 0
    and fee >= 500000 and fee <= 2000000
order by fee desc, car_type asc , car_id desc;
