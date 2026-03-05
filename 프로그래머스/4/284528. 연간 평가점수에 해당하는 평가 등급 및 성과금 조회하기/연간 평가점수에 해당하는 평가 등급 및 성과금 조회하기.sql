-- 코드를 작성해주세요
select e.emp_no, e.emp_name, 
    case
        when avg(g.SCORE) >= 96 then 'S'
        when avg(g.SCORE) >= 90 then 'A'
        when avg(g.SCORE) >= 80 then 'B'
        else 'C'
    end as grade,
    case
        when avg(g.SCORE) >= 96 then e.sal * (0.2)
        when avg(g.SCORE) >= 90 then e.sal * (0.15)
        when avg(g.SCORE) >= 80 then e.sal * (0.1)
        else 0
    end as bonus
from HR_EMPLOYEES e
join hr_grade g on e.emp_no = g.emp_no
group by e.emp_no
order by e.emp_no asc;