package lab3

import lab3.contacts.*

class Contacts : ContactsService {
    private val persons = mutableMapOf<Person, MutableList<Contact>>()

    override fun addContact(person: Person, contact: Contact) {
        persons[person]?.add(contact)
    }

    override fun removeContact(person: Person, contact: Contact) {
        persons[person]?.remove(contact)
    }

    override fun addPerson(person: Person) {
        if (persons.contains(person)) error("Cannot add a [person] with an existing name")
        persons[person] = mutableListOf()
    }

    override fun removePerson(person: Person) {
        persons.remove(person)
    }

    override fun addPhone(person: Person, phone: String, phoneType: PhoneTypes) {
        addContact(person, Phone(phoneType, phone))
    }

    override fun addEmail(person: Person, email: Email) {
        addContact(person, email)
    }

    override fun addLink(person: Person, link: Link) {
        addContact(person, link)
    }

    override fun addAddress(person: Person, address: Address) {
        addContact(person, address)
    }

    override fun getPersonContacts(person: Person): List<Contact> {
        val resList : MutableList<Contact> = mutableListOf()
        persons[person]?.forEach { contact ->
            resList.add(contact)
        }
        return resList
    }

    override fun getPersonPhones(person: Person): List<Phone> {
        val resList : MutableList<Phone> = mutableListOf()
        persons[person]?.forEach { contact ->
            if (contact is Phone)
                resList.add(contact)
        }

        return resList
    }

    override fun getPersonEmails(person: Person): List<Email> {
        val resList : MutableList<Email> = mutableListOf()
        persons[person]?.forEach { contact ->
            if (contact is Email)
                resList.add(contact)
        }
        return resList
    }

    override fun getPersonLinks(person: Person): List<Link> {
        val resList : MutableList<Link> = mutableListOf()
        persons[person]?.forEach { contact ->
            if (contact is Link)
                resList.add(contact)
        }
        return resList
    }

    override fun getAllPersons(): List<Person> {
        val resList : MutableList<Person> = mutableListOf()
        persons.forEach { map ->
            resList.add(map.key)
        }
        return resList
    }

    override fun getAllPersonsByFio(fioSubstring: String): List<Person> {
        val resList = mutableListOf<Person>()
        persons.forEach { map->
            if (map.key.toString().contains(fioSubstring, ignoreCase = true))
                resList.add(map.key)
        }
        return resList
    }

    override fun getAllContacts(): Map<Person, List<Contact>> {
        return persons
    }

    override fun removePersonContacts(person: Person) {
        persons[person] = mutableListOf()
    }

    override fun removePersonPhones(person: Person) {
        val toBeDeleted = mutableListOf<Phone>()
        persons[person]?.forEach { contact ->
            if (contact is Phone) toBeDeleted.add(contact)
        }
        toBeDeleted.forEach {
            persons[person]?.remove(it)
        }
    }

    override fun removePersonEmails(person: Person) {
        val toBeDeleted = mutableListOf<Email>()
        persons[person]?.forEach { contact ->
            if (contact is Email) toBeDeleted.add(contact)
        }
        toBeDeleted.forEach {
            persons[person]?.remove(it)
        }
    }

    override fun removePersonLinks(person: Person) {
        val toBeDeleted = mutableListOf<Link>()
        persons[person]?.forEach { contact ->
            if (contact is Link) toBeDeleted.add(contact)
        }
        toBeDeleted.forEach {
            persons[person]?.remove(it)
        }
    }
}