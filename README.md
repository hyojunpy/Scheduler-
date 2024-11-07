# 일정 관리 
---
## API 
| 기능       | Method | URL                          | request                           | response |상태코드|
|----------|--------|------------------------------|-----------------------------------|----------|---|
| 일정 생성    | POST   | body : todo,writer, password | body : todo,writer, password      | 등록 정보    | 201 : 정상생성 <br/>400 : 생성 실패   |
| 전체 일정 조회 | GET    | param : writer, update_date  | param : writer, update_date       | 다건 정보    | 200 : 정상 조회 <br/> 404 : 조회 실패 |
| 선택 일정 조회 | GET    | /schedules/{id}              | param : id                        | 단건 정보    | 200 : 정상 조회 <br/> 404 : 조회 실패 |
| 일정 수정 | PATCH  | /schedules/{id}                 | param : id <br/> body : password  | 단건 정보    | 200 : 정상 수정 <br/> 404 : 수정 실패 |
| 일정 삭제 | DELETE | /schedules/{id}                | param : id <br/> body : password  | -        | 200 : 정상 삭제 <br/>404 : 삭제 실패  |
