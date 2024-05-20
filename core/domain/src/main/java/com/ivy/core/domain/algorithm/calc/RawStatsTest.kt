package com.ivy.core.domain.algorithm.calc

import com.ivy.core.persistence.algorithm.calc.CalcTrn
import com.ivy.data.transaction.TransactionType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant

class RawStatsTest {

    private lateinit var calcTrn1: CalcTrn
    private lateinit var calcTrn2: CalcTrn

    @BeforeEach
    fun setUp() {
        calcTrn1 = CalcTrn(
            100.0,
            "EUR",
            TransactionType.Expense,
            Instant.now().minusSeconds(3)
        )
        calcTrn2 = CalcTrn(
            300.0,
            "USD",
            TransactionType.Income,
            Instant.now().minusSeconds(10)
        )
    }

    @Test
    fun isRawStatsWorking() {
        val rawStats = rawStats(listOf(calcTrn1, calcTrn2))
        assertEquals(1, rawStats.expensesCount)
        assertEquals(rawStats.newestTrnTime, calcTrn1.time)
        assertEquals(1, rawStats.incomesCount)
        assertEquals(300.0, rawStats.incomes["USD"])
        assertEquals(100.0, rawStats.expenses["EUR"])
    }
}