### Task 1-4
The user in the console should be able to Create, Read, Update and Delete data (CRUD).

You must implement a console CRUD application that has the following entities:
* Developer
* Skill
* Account
* AccountStatus (enum ACTIVE, BANNED, DELETED)

Developer -> Set<Skill> skills + Account account
Account -> AccountStatus

As a database, you must use text files:
* developers.txt 
* skills.txt
* accounts.txt

Layers:
* model - POJO classes (as an example Developer)
* repository - classes that access text files (as an example DeveloperRepository)
* controller - processing requests from the user (as an example DeveloperController)
* view - all data required for working with the console (as an example DeveloperView)

For the repository layer, it is desirable to use the basic interface:

* interface GenericRepository<T,ID>
* interface DeveloperRepository extends GenericRepository<Developer, Long>
* class JavaIODeveloperRepositoryImpl implements DeveloperRepository

### Instructions for running the application in IDE
* To run the application you need to run the main() method in the Main class.
