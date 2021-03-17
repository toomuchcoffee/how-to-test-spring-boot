# How To Test Some Spring Boot Cases

### Testing Transactions
There are a few cases when the `@Transactional` annotation is not working. E.g. it can be used only when Transaction Management is enabled, when the annotation is used on a public method, and when the annotated method is being called from outside that class.

To test if a `@Transactional` annotation is doing its job, we can check whether the `TransactionManager` calls `rollback()` or `commit()`. All we need is to mock the transaction manager (_Note: the mock needs to be deeply stubbed!_) and verify which method is being called.

### Testing Bidirectional Relationships
For one-to-many relationships, the DB entity model requires a foreign key on the owning side of the relationship. The reverse side of the relationship is not explicit in the DB entity model.

JPA helps in allowing to model the reverse side via the `@OneToMany` annotation. However in order to have JPA know about any change on the reverse side of the relationship, a DB roundtrip is necessary. 

In order to test, if both sides of the relationship are set up correctly, we need to simulate the DB roundtrip. For that we can autowire a `TestEntityManager` into the test and call `refresh()` before we check, whether the reverse side of the relationship has been updated by any manipulation on its owning side. 

Note: any additions to or removals from the relationship have to be done on the owning side only. 

There once was an ORM framework (EOF: Entity Object Framework), which abstracted away from any DB artifacts and handled bi-directional relationships entirely on the ORM layer--with no DB roundtrip required. It used such beautiful method names like `addObjectToBothSidesOfRelationshipWithKey()`, but these times are long gone. For now you as a Java developer need to be bothered at all times about the underlying entity model of the database. 

### To Be Continued...
