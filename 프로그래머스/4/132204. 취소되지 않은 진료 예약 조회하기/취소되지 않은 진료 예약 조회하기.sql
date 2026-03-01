-- 코드를 입력하세요
SELECT a.apnt_no, p.pt_name, p.pt_no, d.mcdp_cd, d.dr_name, a.apnt_ymd
from appointment a
join patient p on a.pt_no = p.pt_no
join doctor d on a.MDDR_ID = d.DR_ID
where d.mcdp_cd = 'CS' and apnt_cncl_yn = 'N' and
date(a.apnt_ymd) = '2022-04-13'
order by a.apnt_ymd asc;