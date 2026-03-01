-- 코드를 입력하세요
SELECT 
    concat('/home/grep/src/',
          f.board_id,
          '/',
          f.file_id,
          f.file_name,
          f.file_ext) as FILE_PATH
from used_goods_board b
join used_goods_file f on b.board_id = f.board_id
where b.board_id = (select board_id from used_goods_board order by views desc limit 1)
order by f.file_id desc;
#where로 안자르고 그냥 order by limt으로 가자
#/home/grep/src/ + board_id/ file_id+file_name.file_ext