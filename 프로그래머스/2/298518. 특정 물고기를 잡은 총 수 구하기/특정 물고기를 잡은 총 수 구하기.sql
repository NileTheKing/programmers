-- 코드를 작성해주세요
select count(*) as fish_count
from fish_info
where fish_type in (
    select f.fish_type
    from fish_info as f
    join fish_name_info as n 
    on f.fish_type = n.fish_type
    where n.fish_name = 'snapper' or n.fish_name = 'bass'
);