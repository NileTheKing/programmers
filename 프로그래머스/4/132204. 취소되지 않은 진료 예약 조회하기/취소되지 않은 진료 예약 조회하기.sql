-- 코드를 입력하세요
SELECT a.APNT_NO, p.PT_NAME, p.PT_NO, a.MCDP_CD, d.DR_NAME, APNT_YMD
from PATIENT p
join APPOINTMENT a on a.pt_no = p.pt_no
join DOCTOR d on d.DR_ID = a.MDDR_ID
where a.MCDP_CD = 'CS' and year(a.APNT_YMD) = 2022 and month(a.APNT_YMD) = 4 and day(a.APNT_YMD) = 13 and a.APNT_CNCL_YN = 'N'
order by a.APNT_YMD asc;