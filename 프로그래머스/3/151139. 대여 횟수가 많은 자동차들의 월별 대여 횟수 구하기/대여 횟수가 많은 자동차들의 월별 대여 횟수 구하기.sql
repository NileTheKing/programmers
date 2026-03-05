-- 코드를 입력하세요
SELECT month(start_date) as month, car_id, count(*) as records
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where car_id in (
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where year(START_DATE) = 2022 and (month(START_DATE) >= 8 and month(START_DATE) <= 10)
    group by car_id
    having count(*) >= 5
) and year(START_DATE) = 2022 and (month(START_DATE) >= 8 and month(START_DATE) <= 10)
group by month(start_date), car_id
order by month asc, car_id desc;