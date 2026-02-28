-- 코드를 입력하세요
SELECT c.car_id
from car_rental_company_car c
join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
where c.car_type = '세단' and year(h.start_date) = 2022 and month(h.start_Date) = 10
group by c.car_id
order by c.car_id desc;