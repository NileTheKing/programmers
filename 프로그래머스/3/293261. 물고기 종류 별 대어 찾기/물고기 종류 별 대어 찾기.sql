-- 코드를 작성해주세요
select i.id, n.fish_name, i.length
from fish_info i
join fish_name_info n on i.fish_type = n.fish_type
where (i.length, i.fish_type) in (
    select max(length) as length, fish_type
    from fish_info
    group by fish_type
)