// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_EXPRESSION
// !WITH_CLASSES_WITH_PROJECTIONS
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-draft
 * PLACE: type-inference, smart-casts, smart-casts-sources -> paragraph 6 -> sentence 1
 * NUMBER: 10
 * DESCRIPTION: Nullability condition, if, receivers
 */

fun <T> T?.case_1() {
    if (this != null) {
        <!DEBUG_INFO_EXPRESSION_TYPE("T?")!>this<!>
        <!DEBUG_INFO_EXPRESSION_TYPE("T!!"), DEBUG_INFO_SMARTCAST!>this<!>.hashCode()
    }
}
