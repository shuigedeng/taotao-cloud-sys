# taotao-cloud-sys

| Version | Update Time | Status | Author | Description |
|---------|-------------|--------|--------|-------------|
|1.0|2019-10-12|use|author|desc|



## 平台管理端-字典API
### queryForUpdate
**URL:** http://localhost:8080/manager/sys/dict/query/for-update

**Type:** GET

**Author:** shuigedeng

**Content-Type:** application/x-www-form-urlencoded

**Description:** 

**Request-headers:**

| Header | Type | Required | Description | Since | Example |
|--------|------|----------|-------------|-------|---------|
|token|string|false|desc|-||


**Request-example:**
```bash
curl -X GET -H "token" -i 'http://localhost:8080/manager/sys/dict/query/for-update'
```

**Response-example:**
```json
{}
```

## 平台管理端-用户API
### assignRoles
**URL:** http://localhost:8080/manager/sys/user/assign-roles

**Type:** POST

**Author:** shuigedeng

**Content-Type:** application/json

**Description:** 

**Request-headers:**

| Header | Type | Required | Description | Since | Example |
|--------|------|----------|-------------|-------|---------|
|token|string|false|desc|-||


**Request-example:**
```bash
curl -X POST -H "Content-Type: application/json" -H "token" -i 'http://localhost:8080/manager/sys/user/assign-roles' --data '{}'
```

**Response-example:**
```json
{}
```

## 平台管理端-部门API
### tree
**URL:** http://localhost:8080/manager/sys/dept/query/tree

**Type:** GET

**Author:** shuigedeng

**Content-Type:** application/x-www-form-urlencoded

**Description:** 

**Request-headers:**

| Header | Type | Required | Description | Since | Example |
|--------|------|----------|-------------|-------|---------|
|token|string|false|desc|-||


**Request-example:**
```bash
curl -X GET -H "token" -i 'http://localhost:8080/manager/sys/dept/query/tree'
```

**Response-example:**
```json
{}
```

## TestRedisController
### 什么是缓存穿透 缓存穿透指的是一个缓存系统无法缓存某个查询的数据，从而导致这个查询每一次都要访问数据库。<br><br>常见的Redis缓存穿透场景包括：<br><br>查询一个不存在的数据：攻击者可能会发送一些无效的查询来触发缓存穿透。<br>查询一些非常热门的数据：如果一个数据被访问的非常频繁，那么可能会导致缓存系统无法处理这些请求，从而造成缓存穿透。<br>查询一些异常数据：这种情况通常发生在数据服务出现故障或异常时，从而造成缓存系统无法访问相关数据，从而导致缓存穿透。
**URL:** http://localhost:8080/user/{id}

**Type:** GET

**Author:** shuigedeng

**Content-Type:** application/x-www-form-urlencoded

**Description:** 什么是缓存穿透 缓存穿透指的是一个缓存系统无法缓存某个查询的数据，从而导致这个查询每一次都要访问数据库。

<p>常见的Redis缓存穿透场景包括：

<p>查询一个不存在的数据：攻击者可能会发送一些无效的查询来触发缓存穿透。
查询一些非常热门的数据：如果一个数据被访问的非常频繁，那么可能会导致缓存系统无法处理这些请求，从而造成缓存穿透。
查询一些异常数据：这种情况通常发生在数据服务出现故障或异常时，从而造成缓存系统无法访问相关数据，从而导致缓存穿透。

**Request-headers:**

| Header | Type | Required | Description | Since | Example |
|--------|------|----------|-------------|-------|---------|
|token|string|false|desc|-||


**Path-parameters:**

| Parameter | Type | Required | Description | Since | Example |
|-----------|------|----------|-------------|-------|---------|
|id|int64|true|No comments found.|-|0|

**Request-example:**
```bash
curl -X GET -H "token" -i 'http://localhost:8080/user/{id}'
```

**Response-example:**
```json
{}
```

### 缓存击穿 什么是缓存击穿 缓存击穿指的是在一些高并发访问下，一个热点数据从缓存中不存在，每次请求都要直接查询数据库，从而导致数据库压力过大，并且系统性能下降的现象。<br><br>缓存击穿的原因通常有以下几种：<br><br>缓存中不存在所需的热点数据：当系统中某个热点数据需要被频繁访问时，如果这个热点数据最开始没有被缓存，那么就会导致系统每次请求都需要直接查询数据库，造成数据库负担。<br>缓存的热点数据过期：当一个热点数据过期并需要重新缓存时，如果此时有大量请求，那么就会导致所有请求都要直接查询数据库。<br><br>在遇到缓存击穿问题时，我们可以在查询数据库之前，先判断一下缓存中是否已有数据，如果没有数据则使用Redis的单线程特性，先查询数据库然后将数据写入缓存中。
**URL:** http://localhost:8080/getUserById1/{id}

**Type:** GET

**Author:** shuigedeng

**Content-Type:** application/x-www-form-urlencoded

**Description:** 缓存击穿 什么是缓存击穿 缓存击穿指的是在一些高并发访问下，一个热点数据从缓存中不存在，每次请求都要直接查询数据库，从而导致数据库压力过大，并且系统性能下降的现象。

<p>缓存击穿的原因通常有以下几种：

<p>缓存中不存在所需的热点数据：当系统中某个热点数据需要被频繁访问时，如果这个热点数据最开始没有被缓存，那么就会导致系统每次请求都需要直接查询数据库，造成数据库负担。
缓存的热点数据过期：当一个热点数据过期并需要重新缓存时，如果此时有大量请求，那么就会导致所有请求都要直接查询数据库。

<p>在遇到缓存击穿问题时，我们可以在查询数据库之前，先判断一下缓存中是否已有数据，如果没有数据则使用Redis的单线程特性，先查询数据库然后将数据写入缓存中。

**Request-headers:**

| Header | Type | Required | Description | Since | Example |
|--------|------|----------|-------------|-------|---------|
|token|string|false|desc|-||


**Path-parameters:**

| Parameter | Type | Required | Description | Since | Example |
|-----------|------|----------|-------------|-------|---------|
|id|int64|true|No comments found.|-|0|

**Request-example:**
```bash
curl -X GET -H "token" -i 'http://localhost:8080/getUserById1/{id}'
```

**Response-example:**
```json
{}
```

### 什么是缓存雪崩 指缓存中大量数据的失效时间集中在某一个时间段，导致在这个时间段内缓存失效并额外请求数据库查询数据的请求大量增加，从而对数据库造成极大的压力和负荷。<br><br>常见的Redis缓存雪崩场景包括：<br><br>缓存服务器宕机：当缓存服务器宕机或重启时，大量的访问请求将直接命中数据库，并在同一时间段内导致大量的数据库查询请求，从而将数据库压力大幅提高。<br>缓存数据同时失效：在某个特定时间点，缓存中大量数据的失效时间集中在一起，这些数据会在同一时间段失效，并且这些数据被高频访问，将导致大量的访问请求去查询数据库。<br>缓存中数据过期时间设计不合理：当缓存中的数据有效时间过短，且数据集中在同一时期失效时，就容易导致大量的请求直接查询数据库，加剧数据库压力。<br>波动式的访问过程：当数据的访问存在波动式特征时，例如输出某些活动物品或促销商品时，将会带来高频的查询请求访问，导致缓存大量失效并产生缓存雪崩<br><br>在遇到缓存雪崩时，我们可以使用两种方法：一种是将缓存过期时间分散开，即为不同的数据设置不同的过期时间；另一种是使用Redis的多级缓存架构，通过增加一层代理层来解决
**URL:** http://localhost:8080/getUserById2/{id}

**Type:** GET

**Author:** shuigedeng

**Content-Type:** application/x-www-form-urlencoded

**Description:** 什么是缓存雪崩 指缓存中大量数据的失效时间集中在某一个时间段，导致在这个时间段内缓存失效并额外请求数据库查询数据的请求大量增加，从而对数据库造成极大的压力和负荷。

<p>常见的Redis缓存雪崩场景包括：

<p>缓存服务器宕机：当缓存服务器宕机或重启时，大量的访问请求将直接命中数据库，并在同一时间段内导致大量的数据库查询请求，从而将数据库压力大幅提高。
缓存数据同时失效：在某个特定时间点，缓存中大量数据的失效时间集中在一起，这些数据会在同一时间段失效，并且这些数据被高频访问，将导致大量的访问请求去查询数据库。
缓存中数据过期时间设计不合理：当缓存中的数据有效时间过短，且数据集中在同一时期失效时，就容易导致大量的请求直接查询数据库，加剧数据库压力。
波动式的访问过程：当数据的访问存在波动式特征时，例如输出某些活动物品或促销商品时，将会带来高频的查询请求访问，导致缓存大量失效并产生缓存雪崩

<p>在遇到缓存雪崩时，我们可以使用两种方法：一种是将缓存过期时间分散开，即为不同的数据设置不同的过期时间；另一种是使用Redis的多级缓存架构，通过增加一层代理层来解决

**Request-headers:**

| Header | Type | Required | Description | Since | Example |
|--------|------|----------|-------------|-------|---------|
|token|string|false|desc|-||


**Path-parameters:**

| Parameter | Type | Required | Description | Since | Example |
|-----------|------|----------|-------------|-------|---------|
|id|int64|true|No comments found.|-|0|

**Request-example:**
```bash
curl -X GET -H "token" -i 'http://localhost:8080/getUserById2/{id}'
```

**Response-example:**
```json
{}
```

## 错误码列表

| Error code | Description |
|------------|-------------|
|200|desc|

