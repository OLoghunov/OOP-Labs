import lab3.Contacts
import lab3.Person
import lab3.PhoneTypes
import lab3.contacts.Address
import lab3.contacts.Email
import lab3.contacts.Link
import lab3.contacts.Phone

internal class ContactsTest {

    @org.junit.jupiter.api.Test
    fun addPersonTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        var res = false
        if (con.getAllPersons().isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun removePersonTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.removePerson(Person("qwe","qwe"))
        var res = false
        if (con.getAllPersons().isEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun addContactTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addContact(Person("qwe","qwe"), Email("email"))
        var res = false
        if (con.getAllContacts()[Person("qwe","qwe")]!!.isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun removeContactTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addContact(Person("qwe","qwe"), Email("email"))
        con.removeContact(Person("qwe","qwe"), Email("email"))
        var res = false
        if (con.getAllContacts()[Person("qwe","qwe")]!!.isEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun addPhoneTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addPhone(Person("qwe","qwe"), "123", PhoneTypes.MOBILE)
        var res = false
        if (con.getAllContacts()[Person("qwe","qwe")]!!.isNotEmpty() &&
            con.getAllContacts()[Person("qwe","qwe")]?.first()?.javaClass == Phone::class.java)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun addEmailTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addEmail(Person("qwe","qwe"), Email("email"))
        var res = false
        if (con.getAllContacts()[Person("qwe","qwe")]!!.isNotEmpty() &&
            con.getAllContacts()[Person("qwe","qwe")]?.first()?.javaClass == Email::class.java)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun addLinkTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addLink(Person("qwe","qwe"), Link("123", "123"))
        var res = false
        if (con.getAllContacts()[Person("qwe","qwe")]!!.isNotEmpty() &&
            con.getAllContacts()[Person("qwe","qwe")]?.first()?.javaClass == Link::class.java)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun addAddressTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addAddress(Person("qwe","qwe"), Address("123","qwe", 1))
        var res = false
        if (con.getAllContacts()[Person("qwe","qwe")]!!.isNotEmpty() &&
            con.getAllContacts()[Person("qwe","qwe")]?.first()?.javaClass == Address::class.java)
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun getPersonContactsTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addAddress(Person("qwe","qwe"), Address("123","qwe", 1))
        var res = false
        if (con.getPersonContacts(Person("qwe","qwe")).isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun getPersonPhonesTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addPhone(Person("qwe","qwe"), "123", PhoneTypes.MOBILE)
        var res = false
        if (con.getPersonPhones(Person("qwe","qwe")).isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun getPersonEmailsTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addEmail(Person("qwe","qwe"), Email("email"))
        var res = false
        if (con.getPersonEmails(Person("qwe","qwe")).isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun getPersonLinksTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addLink(Person("qwe","qwe"), Link("qwe", "qwe"))
        var res = false
        if (con.getPersonLinks(Person("qwe","qwe")).isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun getAllPersonsTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        var res = false
        if (con.getAllPersons().isNotEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun getAllPersonsByFioTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        var res = false
        if (con.getAllPersonsByFio("q").isNotEmpty() &&
            con.getAllPersonsByFio("a").isEmpty() )
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun removePersonContactsTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addLink(Person("qwe","qwe"), Link("qwe", "qwe"))
        con.removePersonContacts(Person("qwe","qwe"))
        var res = false
        if (con.getPersonContacts(Person("qwe","qwe")).isEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun removePersonPhonesTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addPhone(Person("qwe","qwe"), "123", PhoneTypes.MOBILE)
        con.removePersonPhones(Person("qwe","qwe"))
        var res = false
        if (con.getPersonContacts(Person("qwe","qwe")).isEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun removePersonEmailsTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addEmail(Person("qwe","qwe"), Email("qwe"))
        con.removePersonEmails(Person("qwe","qwe"))
        var res = false
        if (con.getPersonContacts(Person("qwe","qwe")).isEmpty())
            res = true
        assert(res)
    }

    @org.junit.jupiter.api.Test
    fun removePersonLinksTest() {
        val con = Contacts()
        con.addPerson(Person("qwe","qwe"))
        con.addLink(Person("qwe","qwe"), Link("qwe", "qwe"))
        con.removePersonLinks(Person("qwe","qwe"))
        var res = false
        if (con.getPersonContacts(Person("qwe","qwe")).isEmpty())
            res = true
        assert(res)
    }
}