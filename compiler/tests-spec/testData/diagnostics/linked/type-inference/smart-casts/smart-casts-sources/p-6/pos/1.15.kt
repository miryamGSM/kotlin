// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-draft
 * PLACE: type-inference, smart-casts, smart-casts-sources -> paragraph 6 -> sentence 1
 * NUMBER: 15
 * DESCRIPTION: Nullability condition, if, generic types
 */

// TESTCASE NUMBER: 1
fun <T> case_1(x: T) {
    if (x != null) {
        <!DEBUG_INFO_EXPRESSION_TYPE("T!! & T")!>x<!>
    }
}

// TESTCASE NUMBER: 2
fun <T> case_2(x: T?) {
    if (x != null) {
        <!DEBUG_INFO_EXPRESSION_TYPE("T!! & T?")!>x<!>
    }
}

/*
 * TESTCASE NUMBER: 3
 * NOTE: lazy smartcasts
 */
fun <T> T.case_3() {
    if (this != null) {
        <!DEBUG_INFO_EXPRESSION_TYPE("T!! & T")!>this<!>
    }
}

/*
 * TESTCASE NUMBER: 4
 * NOTE: lazy smartcasts
 */
fun <T> T?.case_4() {
    if (this != null) {
        <!DEBUG_INFO_EXPRESSION_TYPE("T!! & T?")!>this<!>
        <!DEBUG_INFO_EXPRESSION_TYPE("T!!"), DEBUG_INFO_SMARTCAST!>this<!>.hashCode()
    }
}

/*
 * TESTCASE NUMBER: 5
 * NOTE: lazy smartcasts
 */
interface A5 { fun test() }

fun <T> T?.case_5() {
    if (this is A5) {
        if (<!SENSELESS_COMPARISON!>this != null<!>) {
            <!DEBUG_INFO_EXPRESSION_TYPE("A5 & T!! & T?")!>this<!>
            <!DEBUG_INFO_EXPRESSION_TYPE("A5"), DEBUG_INFO_SMARTCAST!>this<!>.hashCode()
            <!DEBUG_INFO_EXPRESSION_TYPE("A5"), DEBUG_INFO_SMARTCAST!>this<!>.test()
        }
    }
}

/*
 * TESTCASE NUMBER: 6
 * NOTE: lazy smartcasts
 */
interface A6 { fun test() }

fun <T> T?.case_6() {
    if (this is A6?) {
        if (this != null) {
            <!DEBUG_INFO_EXPRESSION_TYPE("A6 & T!! & T?")!>this<!>
            <!DEBUG_INFO_EXPRESSION_TYPE("A6"), DEBUG_INFO_SMARTCAST!>this<!>.hashCode()
            <!DEBUG_INFO_EXPRESSION_TYPE("A6"), DEBUG_INFO_SMARTCAST!>this<!>.test()
        }
    }
}

/*
 * TESTCASE NUMBER: 7
 * NOTE: lazy smartcasts
 */
interface A7 { fun test() }

fun <T> T?.case_7() {
    val x = this
    if (x is A7?) {
        if (x != null) {
            <!DEBUG_INFO_EXPRESSION_TYPE("A7 & T!! & T?")!>x<!>
            <!DEBUG_INFO_EXPRESSION_TYPE("T? & A7"), DEBUG_INFO_SMARTCAST!>x<!>.hashCode()
            <!DEBUG_INFO_EXPRESSION_TYPE("T? & A7"), DEBUG_INFO_SMARTCAST!>x<!>.test()
        }
    }
}

/*
 * TESTCASE NUMBER: 8
 * NOTE: lazy smartcasts
 */
interface A8 { fun test() }

fun Number?.case_8() {
    if (this is Int?) {
        if (this != null) {
            <!DEBUG_INFO_EXPRESSION_TYPE("kotlin.Int"), DEBUG_INFO_SMARTCAST!>this<!>.toByte()
        }
    }
}
