package baseball

import camp.nextstep.edu.missionutils.Randoms
import camp.nextstep.edu.missionutils.Console

// 스트라이크와 볼 판정 함수
fun checkNums(comNums: List<Int>, userNums: List<Int>): Boolean {
    var sVal = 0; // 스트라이크 개수 초기화
    var bVal = 0; // 볼 개수 초기화
    // 사용자 숫자와 컴퓨터가 제시한 숫자를 인덱스 별로 비교
    for (i in 0..2) {
        if (userNums[i] == comNums[i]) { // 숫자와 인덱스 모두 같은 경우
            sVal++ // 스트라이크 개수 + 1
        } else if (comNums.contains(userNums[i])) { // 숫자는 같지만 인덱스 다른 경우
            bVal++ // 볼 개수 + 1
        }
    }
    // 볼과 스트라이크 되는 경우
    if (bVal > 0 && sVal > 0)  // 같은 숫자가 존재하고 같은 인덱스에 있는 경우
        println("$bVal 볼 $sVal 스트라이크")
    else if (sVal > 0) // 같은 숫자가 같은 인덱스에 있는 경우
        println("$sVal 스트라이크")
    else if (bVal > 0) // 인덱스는 다르지만 같은 숫자만 있는 경우
        println("$bVal 볼")
    else // 모두 다른 숫자인 경우
        println("낫싱")
    // 스트라이크 3개면 게임 종료
    if (sVal == 3) {
        println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
        return true
    }
    return false;
}

fun main() {
    println("숫자 야구 게임을 시작합니다.")
    var start = true // 게임 시작

    var computerInput = mutableListOf<Int>() // 컴퓨터 입력 변수
    val nums = mutableListOf<Int>() // 무작위 숫자 저장할 변수 선언
    while (nums.size < 3) {
        // 숫자 무작위로 1부터 9까지 선택
        val randomNum = Randoms.pickNumberInRange(0, 9) + 1
        if (!nums.contains(randomNum)) { // 숫자가 nums 리스트에 없다면
            nums.add(randomNum) // 해당 숫자를 nums 리스트에 추가 -> 중복 X
        }
    }
    computerInput = nums; // 저장된 nums 리스트를 computerInput 리스트에 저장

    while (start) {
        // 사용자 입력 부분
        var Input = false // 입력이 안된 경우, 거짓(null)
        while (!Input) { // Input이 참이면 계속 반복
            print("숫자를 입력해주세요 : ")
            val userInput = Console.readLine()
            // 사용자가 입력한 문자열이 3인지 확인
            // toIntOrNull()을 이용해서
            // 입력한 문자열이 정수 변환이 되는지와 숫자로만 이루어져 있는지 확인
            if (userInput.length == 3 && userInput.toIntOrNull() != null)
                Input = checkNums(computerInput, userInput.map { it.toString().toInt() })
        }
        // 프로그램 종료 부분
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        when (Console.readLine()) {
            "1" -> start = true // 1 입력하면 게임 새로 시작
            "2" -> start = false // 2 입력하면 게임 종료
        }

    }
}

