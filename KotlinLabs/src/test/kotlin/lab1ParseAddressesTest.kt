import kotlin.test.assertFailsWith
internal class ParseAddressesTest {

    @org.junit.jupiter.api.Test
    fun `parser should return list of addresses`() {
        val add: List<Address> = parseAddresses(arrayListOf("123,qqq,qqq,22", "222,www,www,33"))
        var res = false
        if (add[0].toString() == "123,qqq,qqq,22" && add[1].toString() == "222,www,www,33")
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun `parser throw an exception when the string is incorrect`() {

        assertFailsWith(IllegalArgumentException::class) {
            parseAddresses(arrayListOf(""))
        }
    }
}