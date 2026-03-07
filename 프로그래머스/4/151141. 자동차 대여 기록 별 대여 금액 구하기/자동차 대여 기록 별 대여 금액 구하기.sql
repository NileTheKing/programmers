-- 코드를 입력하세요
SELECT h.history_id,
   floor((datediff(h.end_date , h.start_date) + 1) * c.daily_fee *
    (100-ifnull(p.discount_rate, 0)) / 100)
   as fee
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
left join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type  and
    p.duration_type = (
        case
            when datediff(h.end_date, h.start_date) + 1 >= 90 then '90일 이상'
            when datediff(h.end_date, h.start_date) + 1 >= 30 then '30일 이상'
            when datediff(h.end_date, h.start_date) + 1 >= 7 then '7일 이상'
            else null
        end
    )
where c.car_type = '트럭'
order by fee desc, h.history_id desc;