# 일정 관리 
---
## API 
| 기능       | Method | URL                          | request                           | response | 상태코드                          |
|----------|--------|------------------------------|-----------------------------------|----------|-------------------------------|
| 일정 생성    | POST   | body : todo,writer, password | body : todo,writer, password      | 등록 정보    | 201 : 정상 생성 <br/>404 : 생성 실패  |
| 전체 일정 조회 | GET    | param : writer, update_date  | param : writer, update_date       | 다건 정보    | 200 : 정상 조회 <br/> 404 : 조회 실패 |
| 선택 일정 조회 | GET    | /schedules/{id}              | param : id                        | 단건 정보    | 200 : 정상 조회 <br/> 404 : 조회 실패 |
| 일정 수정 | PATCH  | /schedules/{id}                 | param : id <br/> body : password  | 단건 정보    | 200 : 정상 수정 <br/> 404 : 수정 실패 |
| 일정 삭제 | DELETE | /schedules/{id}                | param : id <br/> body : password  | -        | 200 : 정상 삭제 <br/>404 : 삭제 실패  |
--- 
<details>
  <summary>일정 생성</summary>

| key          | TYPE         | COMMENT | NULL 여부 |
|--------------|--------------|---------|---------|
| id           | BIGINT       | 일정 id   | N       |
| todo         | VARCHAR(200) | 일정      | N       |
| writer       | VARCHAR(20)  | 작성자명    | N       |
| password     | VARCHAR(20)  | 비밀번호    | N       |
| create_date  | TIMESTAMP    | 생성일     | N       |
| update_date | TIMESTAMP     | 수정일     | N       |

RequestBody
```
url : 'http://localhost:8080/schedules' 
```

```sql
{
    "todo" : "할일", 
    "writer" : "작성자명", 
    "password" : "비밀번호"
}
```
ResopnseBody 

성공 시 : 201 CREATED
```sql
{
    "id" : 1, 
    "todo" : "할일", 
    "writer" : "작성자명", 
    "password" : "비밀번호",
    "create_date" : "YYYY-DD-MM HH-MM-SS", 
    "update_date" : "YYYY-DD-MM HH-MM-SS"
}
```

실패 시 : 404 BAD REQUEST 

</details>
<details>
  <summary>전체 일정 조회</summary>

| key          | TYPE         | COMMENT | NULL 여부 |
|--------------|--------------|---------|---------|
| id           | BIGINT       | 일정 id   | N       |
| todo         | VARCHAR(200) | 일정      | N       |
| writer       | VARCHAR(20)  | 작성자명    | N       |
| create_date  | TIMESTAMP    | 생성일     | N       |
| update_date | TIMESTAMP     | 수정일     | N       |

RequestBody

```
url : 'http://localhost:8080/schedules?writer = {writer}&update_date = {update_date}' 
```

```sql
{
}
```
ResopnseBody

성공 시 : 200 OK
```sql
{
    "id" : 1, 
    "todo" : "할일", 
    "writer" : "작성자명", 
    "create_date" : "YYYY-DD-MM", 
    "update_date" : "YYYY-DD-MM"
}
```

실패 시 : 404 NOT FOUND

</details>


<details>
  <summary>선택 일정 조회</summary>

| key          | TYPE         | COMMENT | NULL 여부 |
|--------------|--------------|---------|---------|
| id           | BIGINT       | 일정 id   | N       |
| todo         | VARCHAR(200) | 일정      | N       |
| writer       | VARCHAR(20)  | 작성자명    | N       |
| create_date  | TIMESTAMP    | 생성일     | N       |
| update_date | TIMESTAMP     | 수정일     | N       |

RequestBody

```
url : 'http://localhost:8080/schedules/{id}' 
```

```sql
{
}
```
ResopnseBody

성공 시 : 200 OK
```sql
{
    "id" : 1, 
    "todo" : "할일", 
    "writer" : "작성자명", 
    "create_date" : "YYYY-DD-MM", 
    "update_date" : "YYYY-DD-MM"
}
```

실패 시 : 404 NOT FOUND

</details>

<details>
  <summary>선택 일정 수정</summary>

| key          | TYPE         | COMMENT | NULL 여부 |
|--------------|--------------|---------|---------|
| id           | BIGINT       | 일정 id   | N       |
| password     | VARCHAR(20)  | 비밀번호    | N       |
| todo         | VARCHAR(200) | 일정      | N       |
| writer       | VARCHAR(20)  | 작성자명    | N       |
| create_date  | TIMESTAMP    | 생성일     | N       |
| update_date | TIMESTAMP     | 수정일     | N       |

RequestBody

```
url : 'http://localhost:8080/schedules/{id}' 
```

```sql
{
    "todo" : "할일", 
    "writer" : "작성자명"
    "password" : "비밀번호"
}
```
ResopnseBody

성공 시 : 200 OK
```sql
{
    "id" : 1, 
    "todo" : "할일", 
    "writer" : "작성자명", 
    "create_date" : "YYYY-DD-MM", 
    "update_date" : "YYYY-DD-MM"
}
```

실패 시 : 404 NOT FOUND

</details>

<details>
  <summary>일정 삭제</summary>

RequestBody

```
url : 'http://localhost:8080/schedules/{id}' 
```

```sql
{
    "password" : "비밀번호"
}
```
ResopnseBody

성공 시 : 200 OK
```sql
{
}
```

실패 시 : 404 NOT FOUND

</details>