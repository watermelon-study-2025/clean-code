# 15장. JUnit 들여다보기

> JUnit 프레임워크의 “문자열 비교 오류” 검사 코드 분석
⇒ 깨끗한 구조 보고 배우기, 더 깨끗하게 만들기

> ✅ 예제 코드를 보며 스터디

### ComparisonCompactor 모듈 분석

- 테스트 케이스를 작성하여 모듈 파악
    
    
    | 상황 | 동작 설명 | 커버 테스트 | 입력 예시  | 기대 결과 메시지 |
    | --- | --- | --- | --- | --- |
    | 단순 메시지 비교 | 메시지 출력 형식 확인 | `testMessage` | `(0, "b", "c")`, msg="a" | `a expected:<[b]> but was:<[c]>` |
    | 앞부분만 동일 | 문자열 앞부분만 일치하는 경우, 일치하지 않는 뒷부분만 [] 표시 | `testStartSame` | `(1, "ba", "bc")` | `expected:<b[a]> but was:<b[c]>` |
    | 뒷부분만 동일 | 문자열 뒷부분만 일치하는 경우, 일치하지 않는 앞부분만 [] 표시 | `testEndSame` | `(1, "ab", "cb")` | `expected:<[a]b> but was:<[c]b>` |
    | 완전히 동일 | 두 문자열이 같으면 차이 강조 없이 그대로 출력 | `testSame` | `(1, "ab", "ab")` | `expected:<ab> but was:<ab>` |
    | 시작과 끝이 같을 때 (컨텍스트 값 0) | 차이 앞뒤는 모두 `...`로 축약해서 표시 | `testNoContextStartAndEndSame` | `(0, "abc", "adc")` | `expected:<...[b]...> but was:<...[d]...>` |
    | 시작과 끝이 같을 때(컨텍스트 값 존재) | 차이 앞뒤를 지정한 컨텍스트 길이만큼 보여준다 | `testStartAndEndContext` | `(1, "abc", "adc")` | `expected:<a[b]c> but was:<a[d]c>` |
    | 긴 문자열에서 차이를 보여주면서 "..." 로 생략 처리 | 차이 좌우 문맥만 보여주고, 나머지는 `...`로 생략 | `testStartAndEndContextWithEllipses` | `(1, "abcde", "abfde")` | `expected:<...b[c]d...> but was:<...b[f]d...>` |
    | 뒤에만 글자가 추가됨 | expected는 짧고, actual이 뒤에 더 길 경우 → 끝부분 차이 강조 | `testComparisonErrorStartSameComplete` | `(2, "ab", "abc")` | `expected:<ab[]> but was:<ab[c]>` |
    | 앞에만 글자가 추가됨 | expected는 짧고, actual이 앞에 더 길 경우 → 앞부분 차이 강조 | `testComparisonErrorEndSameComplete` | `(2, "bc", "abc")` | `expected:<[]bc> but was:<a[bc]>` |
    | 중간에 글자가 삽입됨 (컨텍스트 값 0) | 차이가 나는 지점만 최소한으로 표시 | `testComparisonErrorOverlappingMatches` | `(0, "abc", "abbc")` | `expected:<...[]...> but was:<...b[]...>` |
    | 중간에 글자가 삽입됨 (컨텍스트 값 존재) | 문맥 길이만큼 앞뒤를 보여주면서 차이 강조 | `testComparisonErrorOverlappingMatchesContext` | `(2, "abc", "abbc")` | `expected:<ab[]> but was:<ab[b]>` |
    | 겹침 보정 (컨텍스트 값 0) | 겹치는 계산에서 잘못된 diff가 나오지 않도록 보정 | `testComparisonErrorOverlappingMatches2` | `(0, "abcde", "abcde")` | `expected:<...[]...> but was:<...[]...>` |
    | 겹침 보정 (컨텍스트 값 존재) | 같은 상황을 문맥 포함 버전으로 확인 | `testComparisonErrorOverlappingMatches2Context` | `(2, "abcde", "abcde")` | `expected:<...cd[e]> but was:<...cd[e]>` |
    | actual 이 null | 실제값이 null이면 `but was:<null>` 로 표시 | `testComparisonErrorWithActualNull` | `(0, "a", null)` | `expected:<a> but was:<null>` |
    | actual 이 null (컨텍스트 값 존재) | 위 상황을 문맥 버전에서도 동일하게 처리 | `testComparisonErrorWithActualNullContext` | `(2, "a", null)` | `expected:<a> but was:<null>` |
    | expected 가 null | 기대값이 null이면 `expected:<null>` 로 표시 | `testComparisonErrorWithExpectedNull` | `(0, null, "a")` | `expected:<null> but was:<a>` |
    | expected 가 null (컨텍스트 값 존재) | 위 상황을 문맥 버전에서도 동일하게 처리 | `testComparisonErrorWithExpectedNullContext` | `(2, null, "a")` | `expected:<null> but was:<a>` |
    | 버그 재현 테스트 | 특수문자·긴 문맥에서도 경계 오류 없이 정상 동작 | `testBug609972` | `(10, "S&P500", "0")` | `expected:<[S&P500]> but was:<[0]>` |

### ComparisonCompactor 모듈 개선하기

1. 접두어 제거
    
    멤버변수에 불필요하게 붙이는 접두어 `f` 제거
    
2. compact 함수 내 조건문 캡슐화
`shouldNotCompact()`로 캡슐화
3. compact 함수 내 변수명 및 함수 명 개선
    - 함수 내에서 멤버변수와 같은 이름의 변수를 사용하고 있지만, 실제로는 다른 의미이므로 변수명을 명확하게 부여
    - 긍정문이 이해하기 쉬우므로, `shouldNotCompact()` → `canBeCompacted()`로 이름 개선
4. compact 함수 분리 및 함수명 개선
    - compact 함수는 조건에 따라 압축을 수행하므로, 실제로는 압축하지 않는 경우도 있음
    - 실제 압축하는 함수를 `compactExpectedAndActual()` 로 분리
5. compactExpectedAndActual 내 함수 사용 방식 통일
    - `findCommonPrefix()` , `findCommonSuffix()`가 int 값을 반환하도록 개선하여, 함수 사용 방식 통일
    - 멤버변수 `prefix`와 `suffix`의 이름을 명확하게 개선
6. findCommonSuffix의 시간적 결함 개선
    - `findCommonSuffix()` 는 반드시 `findCommonPrefix()` 가 선행되어, 멤버변수에 값이 세팅 되어야 정상 작동한다는 시간적 결함이 존재
    - 매개변수로 선행 세팅되어야할 값을 넘겨받도록 하여, 시간적 결함을 노출
7. findCommonSuffix의 시간적 결함 추가 개선
    - 반드시 `findCommonPrefix()` 가 선행되어야 하는 이유가 명확하지 않으므로 매개변수 방식 대신, 내부에서 호출하는 방식으로 개선
8. findCommonSuffix 리팩토링
9. suffixIndex 이름 개선
    - 실제로는 index가 아닌 길이를 의미하므로, `suffixLength` 로 변경
10. 개선 완료
