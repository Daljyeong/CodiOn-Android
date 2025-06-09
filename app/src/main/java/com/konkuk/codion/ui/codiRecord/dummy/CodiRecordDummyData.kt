package com.konkuk.codion.ui.codiRecord.dummy

import com.konkuk.codion.ui.codiRecord.EmotionType
import java.time.LocalDate

data class CodiRecordDummyData(
    val date: LocalDate,
    val emotion: EmotionType,
    val content: String
) {
    companion object {
        val dummyData = listOf(
            CodiRecordDummyData(
                date = LocalDate.now(),
                emotion = EmotionType.HAPPY,
                content = "앱이 추천해준대로 입고 나갔는데 날씨에 적절한 옷이었다. 오늘같이 더운 날씨에 반팔과 치마를 입은건 좋은 선택이었다."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 17),
                emotion = EmotionType.SAD,
                content = "아침에 급하게 나가느라 코디를 신경 못 썼다. 바람막이 하나 걸쳤는데 전체적으로 어울리진 않았다."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 25),
                emotion = EmotionType.NEUTRAL,
                content = "평범한 코디였지만 무난하게 하루를 보냈다. 눈에 띄진 않았지만 불편함도 없었다."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 5),
                emotion = EmotionType.HAPPY,
                content = "날씨가 좋아서 가벼운 셔츠에 청바지를 입었는데 산뜻한 기분이었다."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 6),
                emotion = EmotionType.NEUTRAL,
                content = "옷차림은 나쁘지 않았지만 신발이 조금 불편했다. 무난한 하루."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 7),
                emotion = EmotionType.SAD,
                content = "비가 와서 우산과 어울리는 코디를 못 해서 다소 어색했다."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 10),
                emotion = EmotionType.HAPPY,
                content = "깔끔한 블레이저와 슬랙스로 단정한 인상을 줄 수 있었던 날."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 11),
                emotion = EmotionType.SAD,
                content = "패턴이 너무 튀어서 주변 시선을 많이 받았다. 다음엔 더 심플하게."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 13),
                emotion = EmotionType.NEUTRAL,
                content = "편하게 입고 나갔지만 거울 속 모습은 조금 아쉬웠다."
            ),
            CodiRecordDummyData(
                date = LocalDate.of(2025, 3, 22),
                emotion = EmotionType.HAPPY,
                content = "친구들과의 모임에 어울리는 옷차림으로 기분 좋은 하루!"
            )
        )
    }
}
