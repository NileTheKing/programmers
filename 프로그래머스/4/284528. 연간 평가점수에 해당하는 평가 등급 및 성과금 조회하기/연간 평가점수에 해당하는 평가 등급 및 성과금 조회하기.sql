-- 코드를 작성해주세요
select e.emp_no, e.emp_name, t.grade,
    case    
        when t.grade = 'S' then e.sal * 0.2
        when t.grade = 'A' then e.sal * 0.15
        when t.grade = 'B' then e.sal * 0.1
        when t.grade = 'C' then 0
        else 0
    end as bonus
from HR_EMPLOYEES e
join (
    select e.emp_no, 
        case 
            when avg(g.score) >= 96 then 'S'
            when avg(g.score) >= 90 then 'A'
            when avg(g.score) >= 80 then 'B'
            else 'C'
        end as grade
    from hr_employees e
    join hr_grade g on e.emp_no = g.emp_no
    group by emp_no
) as t on e.emp_no = t.emp_no
order by e.emp_no;