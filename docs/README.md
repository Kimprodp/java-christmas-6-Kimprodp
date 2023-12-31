# 크리스마스 프로모션

> 우테코 식당의 12월 크리스마스 프로모션을 위한 주문 예약 및 이벤트를 적용하기 위한 기능을 구현하는 프로젝트 입니다.

## Spec

> Java 17

## 프로젝트 개요

- 12월 우테코 식당의 방문 예정일과 주문할 메뉴를 설정을 통해 예약을 진행합니다.
- 주문한 메뉴와 주문 금액을 확인할 수 있습니다.
- 방문 예정일과 주문 메뉴로 크리스마스 프로모션에서 받을 수 있는 이벤트 혜택을 확인할 수 있습니다.
- 크리스마스 프로모션으로 받게되는 총 혜택 금액을 확인할 수 있습니다.
- 혜택이 적용되어 실제로 결제할 금액을 확인할 수 있습니다.
- 총 혜택 받은 금액으로 2024 새해 이벤트에서 활용될 배지를 받을 수 있습니다.

## 구현 로직

1. 12월 우테코 식당의 예약을 진행한다.
```
[Input] 
- 방문 날짜 입력
- 주문 메뉴 입력
```

```
[Logic]
- 입력한 날짜 검증
- 입력한 주문 메뉴 검증
- 에약 정보 등록
```

```
[Output]
- 주문한 내역 출력
```

2. 주문 내용으로 적용 가능한 이벤트를 확인한다.

```
[Logic]
- 할인 이벤트 적용 가능 확인 및 할인 금액 적용 (크리스마스 디데이 할인, 평일 할인, 주말 할인, 특별 할인)
- 증정 이벤트 적용 가능 확인 및 증정품 적용 (증정 메뉴 지급 및 증정 메뉴의 가격만큼 할인 적용)
- 이벤트로 헤택받은 총 금액 계산
- 증정품을 제외한 메뉴의 주문 금액에서 할인 금액을 차감한 결제 예정 금액을 계산
```

3. 적용되는 이벤트 배지를 확인한다.

```
[Logic]
- 총혜택 금액 조건에 해당되는 배지를 적용
```

4. 크리스마스 프로모션으로 받을 이벤트 혜택을 안내한다.

```
[Output]
- 증정 메뉴 출력
- 혜택 내역 출력
- 할인 후 예상 결제 금액 출력
- 이벤트 배지 출력
```


## 기능 목록

### 식당 예약
> 식당 예약을 받는 기능
- 방문 날짜의 예외 사항 확인
  - [x] 입력된 값이 없을 경우
  - [x] 입력된 값에 공백이 있을 경우
  - [x] 입력된 값이 숫자가 아닐 경우
  - [x] 날짜가 1~31 범위에 없을 경우
- 주문할 메뉴와 수량의 예외 사항 확인
  - [x] 입력된 값이 없을 경우
  - [x] 입력된 값에 공백이 있을 경우
  - [x] 입력된 값이 하이픈으로 구분되지 않을 경우
  - [x] 입력된 값 중 메뉴의 개수가 숫자가 아닐 경우
  - [x] 입력된 값 중 중복된 메뉴명이 있을 경우
  - [x] 주문 메뉴가 메뉴판에 없을 경우
  - [x] 음료만 주문할 경우
  - [x] 주문 수량이 1개 미만인 주문이 있을 경우
  - [x] 주문 메뉴의 총 수량이 20개 초과할 경우
- 예약 정보를 기록
  - [x] 예약 일자 등록
  - [x] 주문 메뉴 등록
<br><br>

### 크리스마스 프로모션 적용
> 크리스마스 프로모션 적용 가능 여부를 확인하는 기능
- [x] 총 주문 금액이 1만원 이상인지 확인

### 증정 이벤트 적용
> 주문할 메뉴의 금액에 따라서 증정 가능 여부를 확인하고 적용하는 기능
- 이벤트 적용 가능 확인
  - [x] 이벤트 기간에 해당 되는지 확인
  - [x] 총 주문 금액 12만원 이상인지 확인
  - [x] 증정 메뉴가 샴페인인지 확인
- [x] 증정품 적용
<br><br>

### 할인 이벤트 적용
> 에약 날짜에 적용 가능한 할인 이벤트를 확인하고 적용하는 기능
- 이벤트 적용 가능 확인
  - [x] 이벤트 기간에 해당 되는지 확인
  - [x] 평일 할인의 디저트 메뉴 주문 확인
  - [x] 주말 할인의 메인 메뉴 주문 확인
- [x] 크리스마스 디데이 할인 이벤트 적용 (1,000원 부터 날마다 100원씩 할인금액 증가)
- [x] 평일 할인 이벤트 적용 (디저트 메뉴 1개당 2,023원 할인)
- [x] 주말 하인 이벤트 적용 (메인 메뉴 1개당 2,023원 할인)
  <br><br>

### 이벤트 혜택 확인
> 적용된 할인 이벤트와 증정 이벤트로 혜택을 받은 금액을 확인하는 기능
- [x] 적용된 할인 이벤트로 혜택 받은 금액 확인
- [x] 적용된 증정 이벤트로 혜택 받은 금액 확인
- [x] 총혜택 금액 확인
<br><br>

### 결제 금액 확인
> 이벤트 혜택을 적용하여 고객이 결제할 금액을 확인하는 기능
- [x] 총주문 금액에서 할인 이벤트 적용된 할인 후 예상 결제 금액 확인
- [x] 증정 이벤트 금액 제외 확인
<br><br>

### 이벤트 배지 부여
> 혜택받은 금액으로 이벤트 배지를 부여하는 기능
- 이벤트 적용 가능 확인
  - [x] 이벤트 기간에 해당 되는지 확인
- [x] 총혜택 금액으로 부여 가능한 이벤트 배지 확인



