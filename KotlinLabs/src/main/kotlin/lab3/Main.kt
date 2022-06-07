package lab3

import lab3.contacts.Email
import lab3.contacts.Link

fun main() {
    val contacts = Contacts()
    val person1 = Person("Alex","Borow")
    contacts.addPerson(person1)
    contacts.addEmail(person1, Email("myemail@gmail.com"))
    contacts.addLink(person1, Link("Vovan1986", "vovan4ik.ru"))
   // print(contacts.getPersonContacts(person1))
    val person2 = Person("Alex","Borow")
    contacts.removePerson(person2)
   // contacts.addEmail(person2, Email("mymail@gmail.com"))
   // contacts.addLink(person2, Link("Sanya1998", "sanya.ru"))
   // contacts.removeContact(person1, Email("email@gmail.com"))
   // contacts.removePersonContacts(person1)
    //contacts.removePersonLinks(person2)
    //print(contacts.getAllPersonsByFio("o"))

    contacts.getAllPersons().forEach{
        println(it)
    }
    println()

  /*  contacts.removePerson(person1)

    contacts.getAllPersons().forEach{
        println(it)
    }
    println()

    contacts.addPerson(Person("Nikolay", "Grigoriev"))

    contacts.getAllPersons().forEach{
        println(it)
    }
    println()*/
}