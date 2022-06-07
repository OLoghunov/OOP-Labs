package lab3.contacts

import lab3.Person
import lab3.PhoneTypes

interface ContactsService {
    fun addContact(person: Person, contact: Contact)
    fun removeContact(person: Person, contact: Contact)
    fun addPerson(person: Person)
    fun removePerson(person: Person)

    fun addPhone(person: Person, phone: String, phoneType: PhoneTypes)
    fun addEmail(person: Person, email: Email)
    fun addLink(person: Person, link : Link)
    fun addAddress(person: Person, address: Address)

    fun getPersonContacts(person: Person) : List<Contact>
    fun getPersonPhones(person: Person) : List<Phone>
    fun getPersonEmails(person: Person): List<Email>
    fun getPersonLinks(person: Person): List<Link>

    fun getAllPersons(): List<Person>
    fun getAllPersonsByFio(fioSubstring: String): List<Person>
    fun getAllContacts(): Map<Person, List<Contact>>

    fun removePersonContacts(person: Person)
    fun removePersonPhones(person: Person)
    fun removePersonEmails(person: Person)
    fun removePersonLinks(person: Person)

}