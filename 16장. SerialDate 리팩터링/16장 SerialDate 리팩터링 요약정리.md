# SerialDate.java
날짜를 표현하는 자바 클래스    

<br/>

## SerialDate 클래스가 필요한 이유
java는 이미 java.util.Date, java.util.Calendar 등과 같은 클래스를 제공한다.   
그렇다면 왜 SerialDate 클래스가 필요한가?    

java.util.Date는 때론 너무 정밀하다. - 1000분의 1초의 정밀도로 시각을 표현하며 시간대에 따라 날짜가 달라진다.    
하루 중 시각, 시간대에 무관하게 종종 특정 날짜만 표현하기 원할 경우 SerialDate를 사용한다.     
즉, SerialDate는 Date 같이 시간 기반 날짜 클래스가 아닌, 순수 날짜 클래스    

<br/>
<br/>

# 첫째, 돌려보자 
SerialDateTest.java 단위 테스트 클래스를 보면 모든 경우를 점검하고 있지 않다.    
(ex. MonthCodeToQuarter 코드는 전혀 실행하지 않음)    

코드 커버리지가 대략 50% 정도에 불과한 단위 테스트 클래스 -> 새로 단위 테스트 케이스를 작성    

### 설계상으로는 통과하지 않아야 맞지만 통과해야 맞다고 판단되는 것들
ex) stringToWeekdayCode() 메소드의 경우 일단 문자열로 받는 걸로 만들었다면 대소문자 구분 없이 통과되어야 한다고 저자는 판단.    
=> 테스트케이스를 작성한 후에 실제 로직을 equalsIgnoreCase로 바꾸면 된다.    

<br/>
<br/>

# 둘째. 고쳐보자
코드를 고칠 때마다 JCommon 단위 테스트와 저자가 짠 단위 테스트를 실행하면서 진행한다.   

**Javadoc**
Javadoc 에서 한줄을 띄우면 자동으로 `<p>`를 붙여준다.   
하지만 개행을 한 모양새 그대로 되진 않기 때문에 만약 개행, 띄어쓰기 등을 그대로 보고 싶다면 `<pre>`로 감싸는게 베스트다.   

<br/>

## SerialDate 클래스의 이름은 적합한가
1. Serial이라는 이름이 붙은 이유는 일련번호를 사용해 클래스를 구현했기 때문   
   1899년 12월 30일을 기준으로 경과한 날짜 수를 사용한다.   
   그러나 Serial(일련번호)라는 뜻보다는 Ordinal(서수)의 의미를 갖고 있는게 더 적합하다   

2. SerialDate는 추상 클래스인데 해당 이름은 구현을 암시하고 있다.    
   구현은 암시할 필요가 없고 숨기는 편이 좋다.    
   따라서 그냥 Date 라는 이름이 좋다.   

하지만 Date 라는 이름은 너무 많다. Day도 마찬가지  => 따라서 일단은 DayDate 라는 용어로 변경   

<br/>

## serialVersionUID
>직렬화 제어 변수 

변수 값을 변경하면 이전 소프트웨어에서 직렬화한 DayDate를 더 이상 인식하지 못한다.    
DayDate 클래스를 복원하려 시도하면 InvalidClassException이 발생    

직렬화 변수를 따로 선언하지 않으면 컴파일러가 자동으로 생성한다.    
=> 모듈을 변경할 때마다 변수 값이 달라진다.    

보통은 직접 선언하라고 권장하지만 저자는 자동 제어를 더 안전하게 여기고 있다.    
만약 직렬화 변수를 변경하지 않아 발생하는 오류를 디버깅하는 것 보단 자동으로 하는게 낫다고 판단했다.   

<br/>

## 마이크로소프트 엑셀 고의적인 버그
엑셀에는 실제로 윤년이 아니지만 1900년을 윤년으로 인식하는 고의적인 버그가 존재한다.   

https://learn.microsoft.com/ko-kr/office/troubleshoot/excel/wrongly-assumes-1900-is-leap-year

<br/>

## 추상 팩토리 패턴 활용
> 기반 클래스(부모 클래스)는 파생 클래스(자식 클래스)를 몰라야 바람직하다.   
    
RelativeDayOfWeekRule.java 의 getDate() 메소드    
=> DayDate 인스턴스를 넘겨받지도 않았는데 DayDate를 반환    
=> 즉, 어디선가 DayDate 인스턴스를 생성하는 것     
=> getPreviousDayOfWeek, getNearestDayOfWeek, getFollowingDayOfWeek    
=> addDays()    
=> createInstance()    
=> SpreadsheetDate 인스턴스를 만든다....!! (부모는 자식을 몰라야 하는데!!!)   


