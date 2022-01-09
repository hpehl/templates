package org.acme.sample

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.property.Arb
import io.kotest.property.arbitrary.positiveInts
import io.kotest.property.checkAll

class Tests : StringSpec({

    "Positive ints are positive" {
        checkAll(Arb.positiveInts(100)) { value ->
            value shouldBeGreaterThan 0
        }
    }
})
